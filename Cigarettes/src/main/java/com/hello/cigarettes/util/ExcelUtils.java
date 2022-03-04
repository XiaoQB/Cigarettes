package com.hello.cigarettes.util;

import com.hello.cigarettes.config.Constants;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFSheet  ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //设置Excel文件路径，方便读取到文件
    public static void setExcelFile(String Path) throws IOException {
        try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);

        }catch(Exception e) {
        }
    }

    //读取Excel文件单元格数据
    public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);
            return Cell.getStringCellValue();
        } catch (NullPointerException ne) {
            return "";
        } catch (Exception e){
            return "";
        }
    }

    //得到一共多少行数据
    public static int getRowCount(String SheetName){
        int number = 0;
        try {
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            number = ExcelWSheet.getLastRowNum()+1;
        } catch(Exception e) {
        }

        return number;
    }

    //得到测试用例的行号
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
        int i = 0;
        try {
            int rowCount = ExcelUtils.getRowCount(SheetName);
            for (; i<rowCount; i++){
                if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
                    break;
                }
            }
        }catch(Exception e) {
        }
        return i;
    }


    //计算一个测试用例有多少个步骤
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
        try {
            for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
                if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
                    int number = i;
                    return number;
                }
            }
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int number=ExcelWSheet.getLastRowNum()+1;
            return number;

        }catch(Exception e) {
            return 0;
        }
    }

    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {
        try{
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            Row  = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum);

            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(Constants.PATH_TEST_DATA);
            ExcelWBook.write(fileOut);
            fileOut.close();
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.PATH_TEST_DATA));

        } catch(Exception e) {
        }
    }

}
