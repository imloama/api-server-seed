package com.github.imloama.api.config.jwt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 从jwt token中获取相应用户信息
 * @author 马兆永
 * @time 2016年9月14日下午12:53:35
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JWTTokenUser {

}
