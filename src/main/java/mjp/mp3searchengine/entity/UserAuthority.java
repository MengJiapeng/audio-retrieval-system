package mjp.mp3searchengine.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

/**
 * @author BadCode
 * @date 2019-04-28 23:11
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthority {

    private BigInteger id;
    private User user;
    private String authority;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "id=" + id +
                ", user=" + user +
                ", authority='" + authority + '\'' +
                '}';
    }
}
