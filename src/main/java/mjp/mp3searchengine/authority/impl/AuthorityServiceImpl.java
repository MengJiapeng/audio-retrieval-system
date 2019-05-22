package mjp.mp3searchengine.authority.impl;

import mjp.mp3searchengine.authority.AuthorityService;
import mjp.mp3searchengine.authority.dao.AuthorityDAO;
import mjp.mp3searchengine.entity.Authority;
import mjp.mp3searchengine.entity.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-28 23:02
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityDAO authorityDAO;

    @Autowired
    public AuthorityServiceImpl(AuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    public List<UserAuthority> getAllUserAuthority() {
        return authorityDAO.listUserAuthority();
    }

    @Override
    public boolean addAuthority(UserAuthority userAuthority) {
        if (userAuthority == null) {
            return false;
        }
        if (!Authority.isSupported(userAuthority.getAuthority())) {
            return false;
        }
        if (authorityDAO.selectUserAuthorityByUserIdAndAuthority(userAuthority) != null) {
            return false;
        }
        return authorityDAO.insertAuthority(userAuthority);
    }

    @Override
    public boolean revokeAuthority(UserAuthority userAuthority) {
        if (userAuthority == null || userAuthority.getId() == null) {
            return false;
        }
        return authorityDAO.deleteAuthority(userAuthority);
    }
}
