package mjp.mp3searchengine.parser;

import mjp.mp3searchengine.entity.AudioDocument;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author BadCode
 * @date 2019-04-03 19:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AudioParserTest {

}
