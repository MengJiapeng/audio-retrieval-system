package mjp.mp3searchengine.authority.dao;

import mjp.mp3searchengine.account.mapper.UserMapper;
import mjp.mp3searchengine.authority.mapper.AuthorityMapper;
import mjp.mp3searchengine.entity.User;
import mjp.mp3searchengine.entity.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-25 19:53
 **/
@Component
public class AuthorityDAO {

    private final AuthorityMapper authorityMapper;
    private final UserMapper userMapper;

    @Autowired
    public AuthorityDAO(AuthorityMapper authorityMapper, UserMapper userMapper) {
        this.authorityMapper = authorityMapper;
        this.userMapper = userMapper;
    }

    public boolean insertAuthority(UserAuthority userAuthority) {
        return authorityMapper.insertAuthority(userAuthority) == 1;
    }

    public boolean deleteAuthority(UserAuthority userAuthority) {
        return authorityMapper.deleteAuthority(userAuthority) == 1;
    }

    public UserAuthority selectUserAuthorityByUserIdAndAuthority(UserAuthority userAuthority) {
        return authorityMapper.selectUserAuthorityByUserIdAndAuthority(userAuthority);
    }

    public List<UserAuthority> listUserAuthority() {
        List<UserAuthority> userAuthorityList = authorityMapper.listUserAuthority();
        if (userAuthorityList == null || userAuthorityList.size() == 0) {
            return userAuthorityList;
        }
        for (UserAuthority userAuthority : userAuthorityList) {
            User fullUserInfo = userMapper.selectUserById(userAuthority.getUser().getId());
            User user = new User();
            user.setId(fullUserInfo.getId());
            user.setUsername(fullUserInfo.getUsername());
            user.setNickname(fullUserInfo.getNickname());
            userAuthority.setUser(user);
        }
        return userAuthorityList;
    }
}
