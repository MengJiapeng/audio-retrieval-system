package mjp.mp3searchengine.authority.mapper;

import mjp.mp3searchengine.entity.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AuthorityMapper {

    /**
     * 为某用户增加权限
     *
     */
    int insertAuthority(UserAuthority userAuthority);

    /**
     * 删除用户权限
     *
     */
    int deleteAuthority(UserAuthority userAuthority);

    /**
     * 根据用户ID与权限名查找用户权限
     *
     * @param userAuthority 用户ID与权限名
     * @return 用户权限
     */
    UserAuthority selectUserAuthorityByUserIdAndAuthority(UserAuthority userAuthority);

    /**
     * 获取所有用户权限
     *
     * @return 用户权限列表
     */
    List<UserAuthority> listUserAuthority();
}
