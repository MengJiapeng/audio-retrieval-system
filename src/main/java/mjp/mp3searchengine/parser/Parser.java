package mjp.mp3searchengine.parser;

import mjp.mp3searchengine.parser.exception.ParseException;

import java.io.File;

public interface Parser {

    /**
     * 对文件进行解析
     *
     * @param file 待解析的文件
     * @return 解析结果
     * @throws ParseException 解析异常
     */
    ParseResult parse(File file) throws ParseException;
}
