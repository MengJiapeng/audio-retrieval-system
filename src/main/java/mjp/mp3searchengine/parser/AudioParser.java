package mjp.mp3searchengine.parser;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.exception.ParseException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * 音频文件解析器
 *
 * @author BadCode
 * @date 2019-03-27 11:27
 **/
@Component
public class AudioParser {

    private static final Logger logger = LoggerFactory.getLogger(AudioParser.class);

    public AudioDocument parse(File file) throws ParseException {
        AudioFile audioFile;
        try {
            audioFile = AudioFileIO.read(file);
        } catch (CannotReadException | IOException | ReadOnlyFileException | TagException | InvalidAudioFrameException e) {
            logger.warn("Audio file parse error.", e);
            throw new ParseException(e.getMessage());
        }
        AudioDocument document = new AudioDocument();
        Tag tag = audioFile.getTag();
        document.setTitle(tag.getFirst(FieldKey.TITLE));
        document.setSubtitle(tag.getFirst(FieldKey.SUBTITLE));
        document.setAlbum(tag.getFirst(FieldKey.ALBUM));
        String artist = tag.getFirst(FieldKey.ARTIST);
        document.setArtist(Arrays.asList(artist.split("/")));
        document.setComposer(tag.getFirst(FieldKey.COMPOSER));
        document.setLyricist(tag.getFirst(FieldKey.LYRICIST));
        if (tag.hasField(FieldKey.YEAR)) {
            document.setYear(Integer.parseInt(tag.getFirst(FieldKey.YEAR)));
        }
        document.setGenre(tag.getFirst(FieldKey.GENRE));
        document.setComment(tag.getFirst(FieldKey.COMMENT));
        document.setLyric(tag.getFirst(FieldKey.LYRICS));
        if (tag.hasField(FieldKey.TRACK)) {
            document.setTrack(Integer.parseInt(tag.getFirst(FieldKey.TRACK)));
        }
        AudioHeader audioHeader = audioFile.getAudioHeader();
        document.setTrackLength(audioHeader.getTrackLength());
        document.setId(UUID.randomUUID().toString());
        document.setFormat(Utils.getExtension(audioFile.getFile()));
        document.setUrl(audioFile.getFile().getAbsolutePath());
        document.setUploadTime(new Date());
        return document;
    }
}
