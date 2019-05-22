package mjp.mp3searchengine.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

/**
 * 音频管理
 *
 * @author BadCode
 * @date 2019-05-08 12:25
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize("hasAuthority('AUDIO_MANAGEMENT')")
public @interface AudioManagement {
}
