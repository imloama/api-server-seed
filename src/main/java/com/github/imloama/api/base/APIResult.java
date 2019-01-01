package com.github.imloama.api.base;

import lombok.Data;

/**
 * api结果
 * @author 马兆永
 * @time 2016年9月18日下午1:24:07
 */
@Data
public class APIResult{

    private int code;
    private String message;
    private Object data;


    public static APIResult ok(String message,Object data){
        APIResult result = new APIResult();
        result.code = CodeConsts.CODE_SUCCESS;
        result.message = message;
        result.data = data;
        return result;
    }

    public static APIResult ok(String message){
       return ok(message,null);
    }


    public static APIResult fail(String message,Object data){
        APIResult result = new APIResult();
        result.code = CodeConsts.CODE_ERR;
        result.message = message;
        result.data = data;
        return result;
    }

    public static APIResult fail(String message){
        return fail(message,null);
    }


}
