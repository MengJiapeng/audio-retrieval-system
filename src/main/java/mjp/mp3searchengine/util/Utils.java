package mjp.mp3searchengine.util;

import java.util.*;

/**
 * @author BadCode
 * @date 2019-04-10 11:02
 **/
public class Utils {

    /**
     * 获取文件后缀名
     *
     * @param name 文件名称
     * @return 文件后缀(小写)
     */
    public static String getExtension(String name) {
        int i = name.lastIndexOf(".");
        if (i == -1) {
            return "";
        }
        return name.substring(i + 1).toLowerCase();
    }

    /**
     * 生成随机文件名
     *
     * @param ext 文件后缀
     * @return 随机文件名
     */
    public static String randomFilename(String ext) {
        Calendar calendar = Calendar.getInstance();
        Random random = new Random();
        return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%2$06d.", calendar, random.nextInt(999999)) + ext;
    }

    /**
     * 将传入的所有list合并为一个
     *
     * @param collection list的集合
     * @param <T> 元素类型
     * @return 合并后的list
     */
    public static <T> List<T> combineList(Collection<List<T>> collection) {
        List<T> result = new ArrayList<>();
        for (List<T> list : collection) {
            result.addAll(list);
        }
        return result;
    }
}
