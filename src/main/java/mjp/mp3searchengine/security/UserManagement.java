package mjp.mp3searchengine.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 用户管理
 *
 * @author BadCode
 * @date 2019-05-08 12:24
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize("hasAuthority('USER_MANAGEMENT')")
public @interface UserManagement {
}
