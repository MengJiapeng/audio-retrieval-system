package mjp.mp3searchengine.web;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author BadCode
 * @date 2019-04-10 10:30
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Integer code;
    private String msg;
    private Object data;

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
