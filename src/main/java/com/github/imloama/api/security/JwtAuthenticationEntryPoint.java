package com.github.imloama.api.security;

import com.alibaba.fastjson.JSON;
import com.github.imloama.api.base.Consts;
import com.github.imloama.api.utils.ResponseUtil;
import com.github.imloama.mybatisplus.bootext.base.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 认证失败处理类，返回401
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //验证为未登陆状态会进入此方法，认证错误
        log.error("认证失败：" + authException.getMessage());
        APIResult result = new APIResult();
        result.setCode(Consts.UNAUTHORIZED);
        result.setMessage(authException.getMessage());
        ResponseUtil.json(response, result);

    }
}