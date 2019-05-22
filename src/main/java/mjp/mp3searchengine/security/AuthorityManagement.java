package mjp.mp3searchengine.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 权限管理
 *
 * @author BadCode
 * @date 2019-05-08 12:26
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize("hasAuthority('AUTHORITY_MANAGEMENT')")
public @interface AuthorityManagement {
}
