package com.github.imloama.api.security;

import com.github.imloama.api.base.Consts;
import com.github.imloama.api.utils.ResponseUtil;
import com.github.imloama.mybatisplus.bootext.base.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        log.error("权限不足：" + e.getMessage());
        APIResult result = new APIResult();
        result.setCode(Consts.FORBIDDEN);
        result.setMessage(e.getMessage());
        ResponseUtil.json(response, result);
    }
}
