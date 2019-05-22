package mjp.mp3searchengine.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BadCode
 * @date 2019-04-12 20:34
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {

    @Test
    public void testRandomFilename() {
        System.out.println(Utils.randomFilename("mp3"));
    }

    @Test
    public void testFormatString() {
        System.out.println(String.format("%1$06d", 1));
    }

    @Test
    public void testEncrypt() {
    }
}
