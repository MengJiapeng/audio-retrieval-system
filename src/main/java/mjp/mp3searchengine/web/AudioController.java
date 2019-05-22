package mjp.mp3searchengine.web;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.ParseService;
import mjp.mp3searchengine.parser.exception.ParseException;
import mjp.mp3searchengine.search.SolrService;
import mjp.mp3searchengine.search.imp.SolrServiceImpl;
import mjp.mp3searchengine.security.AudioManagement;
import mjp.mp3searchengine.util.Utils;
import mjp.mp3searchengine.web.vo.AudioDocumentVO;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Suggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BadCode
 * @date 2019-04-10 10:27
 **/
@RestController
@RequestMapping("audio")
public class AudioController {

    private static final Logger logger = LoggerFactory.getLogger(AudioController.class);

    @Value("${audioFilePath}")
    private String audioFilePath;

    private final ParseService parseService;
    private final SolrService solrService;

    @Autowired
    public AudioController(SolrServiceImpl solrService, ParseService parseService) {
        this.solrService = solrService;
        this.parseService = parseService;
    }

    @PostConstruct
    public void init() {
        File uploadDirectory = new File(audioFilePath);
        if (!uploadDirectory.exists() && !uploadDirectory.mkdirs()) {
            logger.error("upload directory created failed: " + audioFilePath);
        }
    }

    @PostMapping
    @AudioManagement
    public Response uploadAudio(@RequestParam("file") MultipartFile uploadFile) {
        String originName = uploadFile.getOriginalFilename();
        // 存储文件
        File file;
        try {
            file = saveFile(uploadFile);
            if (file == null) {
                return new Response(400, "文件不能为空");
            }
        } catch (IOException e) {
            logger.warn("An error occurred when save file: ", e);
            return new Response(500, "保存文件出错，请重试");
        }
        // 解析文件
        AudioDocument document;
        try {
            document = parseService.parseAudio(file);
        } catch (ParseException e) {
            return new Response(400, uploadFile.getOriginalFilename() + "：文件格式错误");
        }
        // 上传文档
        document.setOriginName(originName);
        try {
            solrService.save(document);
        } catch (IOException | SolrServerException e) {
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
            logger.error("An error occurred when add document to the solr server: ", e);
            return new Response(500, "系统错误，请联系网站管理员");
        }
        return new Response(201, "文件上传成功", new AudioDocumentVO(document));
    }

    @GetMapping
    public Response searchAudio(String q, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        try {
            page = page < 1 ? 1 : page;
            Map<String, Object> searchResult = solrService.search(q, page);
            List documentList = (List) searchResult.get("docs");
            List<AudioDocumentVO> data = new ArrayList<>();
            if (documentList != null && documentList.size() > 0) {
                for (Object document : documentList) {
                    data.add(new AudioDocumentVO((AudioDocument) document));
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("numFound", searchResult.get("numFound"));
            map.put("data", data);
            map.put("page", page);
            return new Response(200, "ok", map);
        } catch (IOException | SolrServerException e) {
            logger.error("An error occurred when search: ", e);
            return new Response(500, "系统错误，请联系网站管理员");
        }
    }

    @PutMapping("/{id}")
    public Response updateAudio(@RequestBody HashMap<String, String> request, @PathVariable String id) {
        try {
            solrService.update(id, request);
            return new Response(200, "ok");
        } catch (SolrServerException | IOException e) {
            logger.error("An error occurred when update audio document: ", e);
            return new Response(500, "系统错误，请联系网站管理员");
        }
    }

    @GetMapping("/suggest")
    public Response getSuggestion(String q) {
        try {
            Map<String, List<Suggestion>> suggestions = solrService.suggest(q);
            return new Response(200, "ok", suggestions);
        } catch (IOException | SolrServerException e) {
            logger.error("An error occurred when suggest: ", e);
            return new Response(500, "系统错误，请联系网站管理员");
        }
    }

    @PostMapping("/lyric")
    @AudioManagement
    public Response uploadLyric(@RequestParam("file") MultipartFile uploadFile, @RequestParam("id") String id) {
        // 存储文件
        File file;
        try {
            file = saveFile(uploadFile);
            if (file == null) {
                return new Response(400, "文件不能为空");
            }
        } catch (IOException e) {
            logger.warn("An error occurred when save file: ", e);
            return new Response(500, "保存文件时出错，请重试");
        }
        // 解析文件
        HashMap<String, String> parseResult;
        try {
            parseResult = parseService.parseLyric(file);
        } catch (ParseException e) {
            return new Response(400, uploadFile.getOriginalFilename() + "：文件格式错误");
        }
        // 上传文档
        try {
            solrService.update(id, parseResult);
        } catch (IOException | SolrServerException e) {
            logger.error("An error occurred when update document: ", e);
            return new Response(500, "系统错误，请联系网站管理员");
        }
        if (file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        return new Response(200, "文件上传成功");
    }

    private File saveFile(MultipartFile uploadFile) throws IOException {
        if (uploadFile.isEmpty() || uploadFile.getOriginalFilename() == null || "".equals(uploadFile.getOriginalFilename())) {
            return null;
        }
        String originFilename = uploadFile.getOriginalFilename();
        String ext = Utils.getExtension(originFilename);
        String filename = Utils.randomFilename(ext);
        File file = new File(audioFilePath + filename);
        if (file.createNewFile()) {
            uploadFile.transferTo(file);
            return file;
        }
        return null;
    }
}
