package mjp.mp3searchengine.web;

import mjp.mp3searchengine.security.AudioManagement;
import mjp.mp3searchengine.security.AuthorityManagement;
import mjp.mp3searchengine.security.UserManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面访问控制controller
 *
 * @author badcode
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class PageController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }

    @RequestMapping
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/admin/audioManage")
    @AudioManagement
    public String audioManagePage() {
        return "/audioManage";
    }

    @RequestMapping("/admin/userManage")
    @UserManagement
    public String userManagePage() {
        return "/userManage";
    }

    @RequestMapping("/admin/authorityManage")
    @AuthorityManagement
    public String authorityManagePage() {
        return "/authorityManage";
    }
}
