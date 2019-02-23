package com.github.imloama.api.base;


import com.github.imloama.api.utils.RequestUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected StringRedisTemplate redisTemplate;

    public String getStrParam(String key){
        return RequestUtil.getStrParam(this.request, key);
    }


}
