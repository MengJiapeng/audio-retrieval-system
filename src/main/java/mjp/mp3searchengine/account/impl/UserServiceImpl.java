package mjp.mp3searchengine.account.impl;

import mjp.mp3searchengine.authority.dao.AuthorityDAO;
import mjp.mp3searchengine.account.exception.LoginException;
import mjp.mp3searchengine.account.exception.RegisterException;
import mjp.mp3searchengine.account.UserService;
import mjp.mp3searchengine.account.dao.UserDAO;
import mjp.mp3searchengine.entity.Authority;
import mjp.mp3searchengine.entity.User;
import mjp.mp3searchengine.util.MD5Util;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-25 12:38
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(User user, String ip) throws RegisterException {
        String username, password;
        if (user == null || StringUtils.isEmpty(username = user.getUsername()) || StringUtils.isEmpty(password = user.getPassword())) {
            throw new RegisterException("用户名或密码不能为空");
        }
        User userWithSameUsername = userDAO.selectUserByUsername(username);
        if (userWithSameUsername != null) {
            throw new RegisterException("用户名已存在");
        }
        try {
            user.setPassword(MD5Util.md5(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RegisterException("系统异常，请联系管理员！");
        }
        user.setRole(User.ROLE_USER);
        user.setEnabled(true);
        user.setLastLoginIp(ip);
        user.setLastLoginTime(Instant.now());
        if (!userDAO.insertUser(user)) {
            throw new RegisterException("注册失败，请重试！");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }

    @Override
    public User login(String username, String password, String ip) throws LoginException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new LoginException("用户名或密码不能为空");
        }
        User user = userDAO.selectUserByUsername(username);
        if (user == null) {
            throw new LoginException("用户不存在");
        }
        try {
            if (!user.getPassword().equals(MD5Util.md5(password.getBytes()))) {
                throw new LoginException("用户名和密码不匹配");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new LoginException("系统异常，请联系管理员！");
        }
        user.setLastLoginIp(ip);
        user.setLastLoginTime(Instant.now());
        userDAO.updateUser(user);
        if (!user.isEnabled()) {
            throw new LoginException("当前账户被冻结");
        }
        if (user.getRole() == User.ROLE_ADMINISTRATOR) {
            List<SimpleGrantedAuthority> allAuthority = new ArrayList<>();
            for (Authority authority : Authority.values()) {
                allAuthority.add(new SimpleGrantedAuthority(authority.getValue()));
            }
            user.setAuthorityList(allAuthority);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                user.getAuthorityList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }

    @Override
    public boolean updateAuthority(User user) {
        return false;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userDAO.selectUserByUsername(username);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public List<User> listUser(User user) {
        return userDAO.listUser(user);
    }
}
