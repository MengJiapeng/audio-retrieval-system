package mjp.mp3searchengine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-24 19:10
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    public static final int ROLE_ADMINISTRATOR = 0;
    public static final int ROLE_USER = 1;

    private BigInteger id;
    private String username;
    private String password;
    private String nickname;
    private Integer role;
    @JsonIgnore
    private List<SimpleGrantedAuthority> authorityList;
    private Boolean enabled;
    private String lastLoginIp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant lastLoginTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<SimpleGrantedAuthority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<SimpleGrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Instant getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Instant lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", authorityList=" + authorityList +
                ", enabled=" + enabled +
                '}';
    }
}
