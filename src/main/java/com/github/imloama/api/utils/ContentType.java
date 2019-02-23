package com.github.imloama.api.utils;

public interface ContentType {
	  /**
     * http response 返回json时的头部
     */
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    /**
     * http response 返回json时的头部 ,只针对IE
     */
    public static final String CONTENT_TYPE_IE = "text/plain;charset=UTF-8";

    /**
     * http response 返回页面时的头部
     */
    public static final String CONTENT_TYPE_HTML = "text/html;chartset=UTF-8";
}
