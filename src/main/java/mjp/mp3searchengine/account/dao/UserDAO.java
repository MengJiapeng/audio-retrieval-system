package mjp.mp3searchengine.account.dao;

import mjp.mp3searchengine.account.mapper.UserMapper;
import mjp.mp3searchengine.entity.User;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-25 13:18
 **/
@Component
public class UserDAO {

    private final UserMapper userMapper;

    @Autowired
    public UserDAO(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean insertUser(User user) {
        if (user == null || StringUtils.isEmpty(user.getUsername())
                || StringUtils.isEmpty(user.getPassword()) || user.getRole() == null) {
            return false;
        }
        return userMapper.insertUser(user) == 1;
    }

    public boolean updateUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        return userMapper.updateUser(user) == 1;
    }

    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public List<User> listUser(User user) {
        return userMapper.listUser(user);
    }
}
