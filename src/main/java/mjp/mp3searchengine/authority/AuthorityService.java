package mjp.mp3searchengine.authority;

import mjp.mp3searchengine.entity.UserAuthority;

import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-28 23:00
 **/
public interface AuthorityService {

    /**
     * 列出所有用户权限
     *
     * @return 用户权限列表
     */
    List<UserAuthority> getAllUserAuthority();

    /**
     * 赋予权限
     *
     * @param userAuthority 用户ID与权限
     * @return 操作是否成功
     */
    boolean addAuthority(UserAuthority userAuthority);

    /**
     * 撤销权限
     *
     * @param userAuthority 用户ID与权限
     * @return 操作是否成功
     */
    boolean revokeAuthority(UserAuthority userAuthority);
}
