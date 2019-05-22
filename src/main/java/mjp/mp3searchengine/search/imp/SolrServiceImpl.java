package mjp.mp3searchengine.search.imp;

import mjp.mp3searchengine.entity.AudioDocument;
import mjp.mp3searchengine.parser.ParseService;
import mjp.mp3searchengine.search.SolrService;
import mjp.mp3searchengine.util.Utils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;
import org.apache.solr.client.solrj.response.Suggestion;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

/**
 * @author BadCode
 * @date 2019-04-03 21:19
 **/
@Service
public class SolrServiceImpl implements SolrService {

    private static final Logger logger = LoggerFactory.getLogger(SolrServiceImpl.class);

    private SolrClient solrClient;

    @Value("${solr.host}")
    private String solrHost;

    @Value("${solr.collection}")
    private String collection;

    @PostConstruct
    private void init() {
        solrClient = new HttpSolrClient.Builder(solrHost + collection).build();
    }

    @Override
    public void save(AudioDocument document) throws IOException, SolrServerException {
        solrClient.addBean(document);
    }

    @Override
    public void update(String id, HashMap<String, String> map) throws IOException, SolrServerException {
        if (StringUtils.isEmpty(id) || map == null || map.isEmpty()) {
            return;
        }
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", id);
        for (String key : map.keySet()) {
            HashMap<String, String> m = new HashMap<>();
            m.put("set", map.get(key));
            document.addField(key, m);
        }
        solrClient.add(document);
        solrClient.commit();
    }

    @Override
    public Map<String, Object> search(String text, int page) throws IOException, SolrServerException {
        String queryParam = buildQueryParam(text);
        SolrQuery query = new SolrQuery();
        query.set("q", queryParam);
        query.set("start", (page - 1) * 10);
        query.set("sort", "uploadTime desc");
        QueryResponse response = solrClient.query(query);
        Map<String, Object> map = new HashMap<>();
        map.put("numFound", response.getResults().getNumFound());
        map.put("docs", response.getBeans(AudioDocument.class));
        return map;
    }

    @Override
    public Map<String, List<Suggestion>> suggest(String input) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery(input);
        QueryRequest suggestRequest = new QueryRequest(query);
        suggestRequest.setPath("/suggest");
        QueryResponse response = suggestRequest.process(solrClient);
        SuggesterResponse suggesterResponse = response.getSuggesterResponse();
        return suggesterResponse.getSuggestions();
    }

    /**
     * 构建查询参数q
     *
     * @param text 用户输入
     * @return 查询参数q
     */
    private String buildQueryParam(String text) {
        if (text == null) {
            return "*:*";
        }
        text = text.toLowerCase();
        int index;
        if ((index = text.indexOf(':')) != -1) {
            String searchField = text.substring(0, index).trim().toLowerCase();
            if (AudioDocument.getSearchFields().contains(searchField)) {
                return text;
            }
        }
        String queryText = text.substring(index + 1);
        return "all_text:" + queryText;
    }

    @PreDestroy
    private void close() {
        if (solrClient != null) {
            try {
                solrClient.close();
                logger.info("solr client has been closed properly.");
            } catch (IOException e) {
                logger.error("solr client did not close properly!");
            }
        }
    }
}
