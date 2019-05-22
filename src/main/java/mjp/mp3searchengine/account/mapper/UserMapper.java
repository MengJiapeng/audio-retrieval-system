package mjp.mp3searchengine.account.mapper;

import mjp.mp3searchengine.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Component
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 影响行数
     */
    int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 影响行数
     */
    int updateUser(User user);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User selectUserByUsername(String username);

    /**
     * 根据用户ID查找用户
     *
     * @param id 用户ID
     * @return 用户
     */
    User selectUserById(BigInteger id);

    /**
     * 查询符合条件的用户
     *
     * @param user 查询条件
     * @return 用户列表
     */
    List<User> listUser(User user);
}
