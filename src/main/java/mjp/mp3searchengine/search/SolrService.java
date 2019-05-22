package mjp.mp3searchengine.search;

import mjp.mp3searchengine.entity.AudioDocument;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Suggestion;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SolrService {

    /**
     * 上传音频文件
     *
     * @param document 音频文档
     * @throws IOException if there is a communication error with the server
     * @throws SolrServerException if there is an error on the server
     */
    void save(AudioDocument document) throws IOException, SolrServerException;

    /**
     * 更新文档
     *
     * @param id 文档id
     * @param map 要更新的域值
     * @throws IOException if there is a communication error with the server
     * @throws SolrServerException if there is an error on the server
     */
    void update(String id, HashMap<String, String> map) throws IOException, SolrServerException;

    /**
     * 查询
     *
     * @param query 搜索关键字
     * @return 命中文档列表
     * @throws IOException if there is a communication error with the server
     * @throws SolrServerException if there is an error on the server
     */
    Map<String, Object> search(String query, int page) throws IOException, SolrServerException;

    /**
     * 根据用户输入提供输入建议
     *
     * @param input 用户输入
     * @return 输入建议
     * @throws IOException if there is a communication error with the server
     * @throws SolrServerException if there is an error on the server
     */
    Map<String, List<Suggestion>> suggest(String input) throws IOException, SolrServerException;
}
