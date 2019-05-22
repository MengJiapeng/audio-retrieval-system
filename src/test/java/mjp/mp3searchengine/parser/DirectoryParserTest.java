package mjp.mp3searchengine.parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-11 13:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectoryParserTest {

    @Autowired
    private DirectoryParser directoryParser;

    @Test
    public void testParse() {
        List<File> fileList = directoryParser.parse("D:\\测试文件夹");
        if (fileList != null) {
            for (File file : fileList) {
                System.out.println(file.getName());
            }
        }
    }
}
