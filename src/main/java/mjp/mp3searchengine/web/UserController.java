package mjp.mp3searchengine.web;

import mjp.mp3searchengine.account.UserService;
import mjp.mp3searchengine.account.exception.LoginException;
import mjp.mp3searchengine.account.exception.RegisterException;
import mjp.mp3searchengine.entity.User;
import mjp.mp3searchengine.security.AuthorityManagement;
import mjp.mp3searchengine.util.RequestUtil;
import org.eclipse.jetty.http2.hpack.NBitInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

/**
 * @author BadCode
 * @date 2019-04-25 19:26
 **/
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user, HttpServletRequest request) {
        try {
            String ip = RequestUtil.getIp(request);
            User registerUser = userService.register(user, ip);
            registerUser.setPassword(null);
            return new Response(200, "注册成功", registerUser);
        } catch (RegisterException e) {
            return new Response(400, e.getMessage());
        }
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user, HttpSession session, HttpServletRequest request) {
        String ip = RequestUtil.getIp(request);
        try {
            user = userService.login(user.getUsername(), user.getPassword(), ip);
        } catch (LoginException e) {
            return new Response(400, e.getMessage());
        }
        session.setAttribute(SessionKey.USER_INFO, user);
        user.setPassword(null);
        return new Response(200, "登陆成功", user);
    }

    @PutMapping("/manage/authority")
    @AuthorityManagement
    public Response updateAuthority(@RequestBody User user) {
        if (userService.updateAuthority(user)) {
            return new Response(200, "权限更新成功");
        }
        return new Response(400,"权限更新失败");
    }

    @GetMapping("/user")
    public Response getUserByUsername(@RequestParam(name = "username", required = false) String username,
                                      @RequestParam(name = "nickname", required = false) String nickname,
                                      @RequestParam(name = "role", required = false) Integer role,
                                      @RequestParam(name = "enabled", required = false) Boolean enabled) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setRole(role);
        user.setEnabled(enabled);
        return new Response(200, "ok", userService.listUser(user));
    }

    @PutMapping("/user/{id}")
    public Response updateUser(@PathVariable BigInteger id, @RequestBody User user) {
        user.setId(id);
        if (userService.updateUser(user)) {
            return new Response(200, "ok");
        }
        return new Response(400,"更新失败");
    }
}
