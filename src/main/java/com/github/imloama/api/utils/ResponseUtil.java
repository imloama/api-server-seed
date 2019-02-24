package com.github.imloama.api.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * response内容工具栏
 *
 * Created by mazhyb on 2016-01-08.
 */
public final class ResponseUtil {

    private ResponseUtil(){}

    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * 返回json结果
     * @param response
     * @param obj
     */
    public static void json(HttpServletResponse response,String contentType,Object obj){
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", contentType);
        try {
            String result = "{}";
            if(obj != null){
                if(obj instanceof String){
                    result = (String)obj;
                }else{
                    result = JSON.toJSONString(obj);
                }
            }
            response.getWriter().print(result);
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error("返回json结果时错误！",e);
        }

    }


    public static void json(HttpServletResponse response,Object obj){
        json(response, ContentType.CONTENT_TYPE_JSON,obj);
    }

}
