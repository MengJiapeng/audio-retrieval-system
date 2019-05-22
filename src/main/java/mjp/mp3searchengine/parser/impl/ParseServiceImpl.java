package mjp.mp3searchengine.parser.impl;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.*;
import mjp.mp3searchengine.parser.exception.ParseException;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;

/**
 * @author BadCode
 * @date 2019-04-24 19:32
 **/
@Service
public class ParseServiceImpl implements ParseService {

    @Override
    public AudioDocument parseAudio(File file) throws ParseException {
        AudioParser parser = new AudioParser();
        return parser.parse(file);
    }

    @Override
    public HashMap<String, String> parseLyric(File file) throws ParseException {
        LyricParser parser = new LyricParser();
        return parser.parse(file);
    }
}
