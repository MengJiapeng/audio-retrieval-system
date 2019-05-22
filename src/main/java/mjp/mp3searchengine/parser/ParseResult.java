package mjp.mp3searchengine.parser;

/**
 * 解析结果
 *
 * @author BadCode
 * @date 2019-04-24 19:27
 **/
public class ParseResult {

    public static final String AUDIO = "audio";
    public static final String ZIP = "zip";
    public static final String LYRIC = "lyric";

    private String type;
    private Object data;

    public ParseResult(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ParseResult{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
