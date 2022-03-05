import com.hello.cigarettes.service.CigarettesService;
import com.hello.cigarettes.service.impl.CigarettesServiceImpl;

import java.io.File;
import java.io.IOException;

/**
 * @author XiaoQuanbin
 * @date 2022/3/3
 */
class test {
    public static void main(String[] args) throws IOException {
        CigarettesService c = new CigarettesServiceImpl();
        c.saveFile(new File(""));

    }

}
