package com.github.imloama.api.demo.controller;

import com.github.imloama.api.config.jwt.JWT;
import com.github.imloama.api.config.jwt.JWTUser;
import com.github.imloama.mybatisplus.bootext.base.APIResult;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JWTController {

    @RequestMapping(value = "/api/v1/login", method = RequestMethod.POST)
    public APIResult login(@RequestParam("username") String username,
                           @RequestParam("secretkey") String secretkey){
        //测试用，直接判断
        if("admin".equals(username) && "123".equals(secretkey)){
            JWTUser user = new JWTUser("1",username);
            try {
                APIResult result = new APIResult();
                result.setData(JWT.newToken(user));
                return result;
            } catch (JOSEException e) {
                throw new RuntimeException(e.getMessage(),e);
            }
        }
        throw new RuntimeException("验证用户失败！");
    }




}