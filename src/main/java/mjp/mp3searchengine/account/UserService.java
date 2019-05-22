package mjp.mp3searchengine.account;

import mjp.mp3searchengine.account.exception.LoginException;
import mjp.mp3searchengine.account.exception.RegisterException;
import mjp.mp3searchengine.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @param ip 注册时的ip
     */
    User register(User user, String ip) throws RegisterException;

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param ip 本次登录IP
     * @return 用户信息
     */
    User login(String username, String password, String ip) throws LoginException;

    /**
     * 更新用户权限
     *
     * @param user 用户
     * @return 是否成功
     */
    boolean updateAuthority(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User selectUserByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user user
     * @return 更新是否成功
     */
    boolean updateUser(User user);

    /**
     * 查询符合条件的用户
     *
     * @param user 查询条件
     * @return 用户列表
     */
    List<User> listUser(User user);
}
