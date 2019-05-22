package mjp.mp3searchengine.parser;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.exception.ParseException;

import java.io.File;
import java.util.HashMap;

public interface ParseService {

    /**
     * 解析音频文件
     *
     * @param file 要解析的文件
     * @return 解析结果
     * @throws ParseException 文件解析时出现错误
     */
    AudioDocument parseAudio(File file) throws ParseException;

    /**
     * 解析歌词文件
     *
     * @param file 要解析的文件
     * @return 解析结果
     * @throws ParseException 文件解析时出现错误
     */
    HashMap<String, String> parseLyric(File file) throws ParseException;
}
