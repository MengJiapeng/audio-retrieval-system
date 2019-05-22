package mjp.mp3searchengine.service;

import com.sun.xml.internal.ws.message.saaj.SAAJMessage;
import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.AudioParser;
import mjp.mp3searchengine.parser.DirectoryParser;
import mjp.mp3searchengine.parser.LyricParser;
import mjp.mp3searchengine.parser.exception.ParseException;
import mjp.mp3searchengine.search.SolrService;
import org.apache.solr.client.solrj.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-03 23:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrServiceTest {

    @Autowired
    private SolrService solrService;

    @Autowired
    private DirectoryParser directoryParser;

    @Autowired
    private AudioParser audioParser;

    @Autowired
    private LyricParser lyricParser;

    @Test
    public void testSuggest() throws IOException, SolrServerException {
        System.out.println(solrService.suggest("周"));
    }

    @Test
    public void testBatchSave() throws ParseException, IOException, SolrServerException {
        List<File> fileList = directoryParser.parse("/home/badcode/Music/测试音乐");
        for (File file : fileList) {
            AudioDocument document = audioParser.parse(file);
            document.setOriginName(file.getName());
            solrService.save(document);
        }
    }

    @Test
    public void testUpdate() throws ParseException, IOException, SolrServerException {
        HashMap<String, String> map = lyricParser.parse(new File("C:\\Users\\mjp17\\Desktop\\奇谈.lrc"));
        String id = "d8694a81-6d22-4667-bd01-a2658df04d2a";
        solrService.update(id, map);
    }
}
