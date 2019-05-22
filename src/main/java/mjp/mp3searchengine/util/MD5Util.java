package mjp.mp3searchengine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author BadCode
 * @date 2019-04-25 13:41
 **/
public class MD5Util {

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 对字节数组进行信息摘要
     *
     * @param data 数据
     * @return 十六进制表示的信息摘要结果
     */
    public static String md5(byte[] data) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] result = messageDigest.digest(data);
            return new BigInteger(1, result).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5 error: ", e);
            throw e;
        }
    }
}
