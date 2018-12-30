package com.github.imloama.api.config.jwt;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.imloama.api.base.CodeConsts;
import com.github.imloama.api.config.APIProperties;
import com.github.imloama.api.utils.RequestUtil;
import com.github.imloama.api.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;



public class JWTTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JWTTokenInterceptor.class);

    private APIProperties props;
    
    public APIProperties getProps() {
		return props;
	}
    
    public void setProps(APIProperties props) {
		this.props = props;
	}
    
    /**
     * 如果是api的请求，做过滤，否则不做过滤
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String url = request.getRequestURI();
        //路径没有以/api作前缀，则直接返回
        if(!url.startsWith(props.getPrefix())){
            return true;
        }
        //路径是否为不需要处理的路径
        for(String uri : this.props.getExcludeUrls()){
            if(url.startsWith(uri)){
                return true;
            }
        }
        try {
            JWTUser user = JWT.getJWTUser(request);
            if(user!=null){
                return true;
            }
        }catch(JWTException e){
            logger.error("校验token失败!",e);
        }
        JSONObject result = new JSONObject();
        result.put(CodeConsts.CODE_KEY, CodeConsts.CODE_UNLOGIN);
        result.put(CodeConsts.MSG_KEY, "未登陆！");
        ResponseUtil.json(response, RequestUtil.getJsonContentType(request), result);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
