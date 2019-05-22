package mjp.mp3searchengine.web;

import mjp.mp3searchengine.authority.AuthorityService;
import mjp.mp3searchengine.entity.UserAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/userAuthority")
public class UserAuthorityController {

    private final AuthorityService authorityService;

    @Autowired
    public UserAuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping
    public Response getAllUserAuthority() {
        return new Response(200, "ok", authorityService.getAllUserAuthority());
    }

    @DeleteMapping("/{id}")
    public Response revokeAuthority(@PathVariable BigInteger id) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setId(id);
        if (authorityService.revokeAuthority(userAuthority)) {
            return new Response(204, "ok");
        } else {
            return new Response(400, "操作失败");
        }
    }

    @PostMapping
    public Response insertUserAuthority(@RequestBody UserAuthority userAuthority) {
        if (authorityService.addAuthority(userAuthority)) {
            return new Response(201, "ok", userAuthority);
        } else {
            return new Response(400, "操作失败");
        }
    }
}
