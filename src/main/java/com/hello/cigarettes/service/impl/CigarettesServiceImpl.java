package com.hello.cigarettes.service.impl;


import com.hello.cigarettes.config.Constants;
import com.hello.cigarettes.dao.CigaretteDao;
import com.hello.cigarettes.domain.Cigarette;
import com.hello.cigarettes.domain.CigaretteType;
import com.hello.cigarettes.service.CigarettesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author QuanbinXiao
 */
@Slf4j
@Service
public class CigarettesServiceImpl implements CigarettesService {

    /**
     * Excel file path
     */
    public static final String PATH = Constants.PATH;
    /**
     * 表格列数
     */
    public static final int COLUMN_NUMS = 6;
    /**
     * 第几个sheet
     */
    public static final int SHEET_NUM = 2;
    public static final int MAX_DUPLICATE = 3;

    private CigaretteDao cigaretteDao;
    /**
     *存储的文件路径
     */
    public static final String STORAGE_PATH = "";

    @Override
    public Boolean saveFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String filePath = PATH.substring(0,PATH.lastIndexOf('/') + 1);
        assert fileName != null;
        File loadFile = new File(filePath , fileName);
        try{
            file.transferTo(loadFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        cigaretteDao.deleteData();

        List<Cigarette> cigarettes = excelToObjectModel();
        cigaretteDao.insertCigarettes(cigarettes);
        return true;
    }

    @Override
    public String[][] getArrangement(String id, int row, int col) {
        List<Cigarette> normalList = new CopyOnWriteArrayList<>();
        List<Cigarette> thinList = new CopyOnWriteArrayList<>();
        List<Cigarette> middleList = new CopyOnWriteArrayList<>();

        if(row <= 1 || col <= 1){
            log.error("cow or row wrong!");
        }
        String[][] arrangement = new String[row][col];

        List<Cigarette> list = cigaretteDao.getCigarettesBySellerId(id);

        // 已有香烟无法摆满展柜
        int totalSize = row * col;
        int cigaretteSize = list.size();
        if(totalSize > MAX_DUPLICATE * cigaretteSize){
            log.error("展柜数量过大，请重新输入行号和列号");
            arrangement[0][0] = "展柜数量过大，请重新输入行号和列号";
            return arrangement;
        }

        // 完成三种香烟类型的分类
        for(Cigarette c: list){
            String type = c.getCigaretteType();
            if(type.equals(CigaretteType.MIDDLE.getType())){
                middleList.add(c);
            }else if(type.equals(CigaretteType.THIN.getType())){
                thinList.add(c);
            }else if(type.equals(CigaretteType.NORMAL.getType())){
                normalList.add(c);
            }else{
                log.error("some unknown cigarette type!");
            }
        }

        sortCigaretteList(normalList, thinList, middleList);

        //从左上到右下： middle由低到高 thin中间高 normal从高到低
//        printAllList(normalList, thinList, middleList);


        // 各香烟类型数量
        int middleListSize = middleList.size();
        int thinListSize = thinList.size();
        int normalListSize = normalList.size();

        // 按比例计算各类型香烟需要填充的格子数
        int middleArraySize = Math.round((float)middleListSize/cigaretteSize * totalSize);
        int thinArraySize = Math.round((float)thinListSize/cigaretteSize * totalSize);
        int normalArraySize = totalSize - thinArraySize -middleArraySize;
        if(normalArraySize < 0){
            arrangement[0][0] = "展柜尺寸不够";
            return arrangement;
        }

        // 填充list到需要的标准
        int m = 0;

        while(middleList.size() != middleArraySize){
            Cigarette c = middleList.get(m);
            middleList.add(m++ % middleListSize, c);
        }
        m = 0;
        while(thinList.size() != thinArraySize){
            Cigarette c = thinList.get(m);
            thinList.add(m++ % thinListSize, c);
        }
        m = 0;
        while(normalList.size() != normalArraySize){
            Cigarette c = normalList.get(m);
            normalList.add(m++ % normalListSize, c);
        }
//        printAllList(normalList, thinList, middleList);
        sortCigaretteList(normalList, thinList, middleList);

        middleList.addAll(thinList);
        middleList.addAll(normalList);
        int count = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                arrangement[i][j] = middleList.get(count++).getCigaretteName();
            }
        }
        return arrangement;
    }

    public void printAllList(List<Cigarette> normalList, List<Cigarette> thinList, List<Cigarette> middleList){
        log.info("normal");
        for (Cigarette c: normalList
        ) {
            System.out.println(c.getCigaretteName() + ":" + c.getPrice());
        }
        log.info("thinList");

        for (Cigarette c: thinList
        ) {
            System.out.println(c.getCigaretteName() + ":" + c.getPrice());
        }
        log.info("middle");
        for (Cigarette c: middleList
        ) {
            System.out.println(c.getCigaretteName() + ":" + c.getPrice());
        }
    }

    public static List<Cigarette> excelToObjectModel(){
        List<Map<String,String>> list = getAllCell();
        List<Cigarette> itemList = new ArrayList<>();
        for (Map<String,String> map : list) {
            String sellerId = null;
            String sellerName = null;
            String cigaretteName = null;
            String priceString = null;
            String orderNumString = null;
            String type = null;

            for (Map.Entry<String, String> entry : map.entrySet()) {
                switch (entry.getKey()){
                    case "1" : sellerId = entry.getValue();
                    case "2" : sellerName = entry.getValue();
                    case "3" : cigaretteName = entry.getValue();
                    case "4" : orderNumString  = entry.getValue();
                    case "5" : priceString = entry.getValue();
                    case "6" : type = entry.getValue();
                    default: break;
                }
            }
            assert priceString !=null;
            int price = (int)Float.parseFloat(priceString);
            assert orderNumString !=null;
            int orderNum = (int)Float.parseFloat(orderNumString);
            if(orderNum == 0){
                continue;
            }
            if("".equals(type)){
                continue;
            }
            itemList.add(new Cigarette(sellerId, sellerName, cigaretteName,price
                    ,orderNum,type));
        }
        return itemList;

    }

    public static List<Map<String,String>> getAllCell(){
        Workbook wb;
        Sheet sheet;
        Row row;
        List<Map<String,String>> list = null;
        String cellData;
        String[] columns = new String[COLUMN_NUMS];
        for (int i = 0; i < COLUMN_NUMS; i++) {
            columns[i] = i+1+"";
        }
        wb = readExcel();
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(SHEET_NUM - 1 );
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    public static Workbook readExcel(){
        Workbook wb = null;
        String extString = PATH.substring(PATH.lastIndexOf("."));
        InputStream is;
        try {
            is = new FileInputStream(PATH);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    public void sortCigaretteList(List<Cigarette> normalList, List<Cigarette> thinList, List<Cigarette> middleList){
            /**
             * 中支烟由低到高
             */
            middleList.sort(Comparator.comparingInt(Cigarette::getPrice));

            /**
             * 细烟由低到高再到低，这里先按照高到低
             */
            thinList.sort((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));

            /**
             * 常规烟由高到低
             */
            normalList.sort((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));

    }

    @Autowired
    private void setCigaretteDao(CigaretteDao cigaretteDao){
        this.cigaretteDao = cigaretteDao;
    }
}
