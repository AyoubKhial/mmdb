package com.movies.mmdb.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * Every rest controller will use this annotation to have the same base path <b>"/api"</b>
 * @author Ayoub Khial
 * @version 1.0
 * @see RestController
 * @see Retention
 * @see Target
 * @since 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping("/api")
public @interface RestControllerApi {
}
