import com.hello.cigarettes.service.CigarettesService;
import com.hello.cigarettes.service.impl.CigarettesServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */

class test {
    @Test
    public static void main(String[] args) throws IOException {
        CigarettesService c = new CigarettesServiceImpl();
        c.saveFile();

    }

}
