package mjp.mp3searchengine.parser;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.exception.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 歌词文件解析器
 *
 * @author BadCode
 * @date 2019-04-24 19:48
 **/
@Component
public class LyricParser {

    private static final Logger logger = LoggerFactory.getLogger(LyricParser.class);

    /**
     * 歌词文件解析
     *
     * @param file 歌词文件
     * @return
     * @throws ParseException
     */
    public HashMap<String, String> parse(File file) throws ParseException {
        try (FileReader reader = new FileReader(file)) {
            Pattern pattern = Pattern.compile("(\\[.+:.+])(.*)");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuilder lyric = new StringBuilder();
            HashMap<String, String> map = new HashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    parseLabel(matcher.group(1), map);
                    lyric.append(matcher.group(2)).append(' ');
                }
            }
            map.put("lyric", lyric.toString());
            return map;
        } catch (IOException e) {
            logger.warn("parse lyric file error.", e);
            throw new ParseException();
        }
    }

    private void parseLabel(String label, HashMap<String, String> map) {
        label = label.trim();
        label = label.substring(1, label.length() - 1);
        String[] array = label.split(":");
        switch (array[0].trim().toLowerCase()) {
            case "al":
                map.put("album", array[1].trim());
                return;
            case "ar":
                map.put("artist", array[1].trim());
                return;
            case "au":
                map.put("lyricist", array[1].trim());
                return;
            case "ti":
                map.put("title", array[1].trim());
        }
    }
}
