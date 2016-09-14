package seed.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * request工具类
 * @since 2016-01-06
 * @author 马兆永
 *
 */
public final class RequestUtil {
	

	
	
	private RequestUtil(){}

	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);
	/**
	 * 是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;
	}

	/**
	 * 根据请求返回json不同的content-type，是由于在IE浏览器中不能直接使用application/json
	 * @param request
	 * @return
	 */
	public static String getJsonContentType(HttpServletRequest request){
		String agent = request.getHeader("User-Agent").toLowerCase();
		if(agent.indexOf("msie 7")>0
				|| agent.indexOf("msie 8")>0 ){
			return ContentType.CONTENT_TYPE_IE;
		}else {
			return ContentType.CONTENT_TYPE_JSON;
		}
	}


	public String getBrowserName(String agent) {
		if (agent.indexOf("msie 7") > 0) {
			return "ie7";
		} else if (agent.indexOf("msie 8") > 0) {
			return "ie8";
		} else if (agent.indexOf("msie 9") > 0) {
			return "ie9";
		} else if (agent.indexOf("msie 10") > 0) {
			return "ie10";
		} else if (agent.indexOf("msie") > 0) {
			return "ie";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("opera") > 0) {
			return "opera";
		} else if (agent.indexOf("firefox") > 0) {
			return "firefox";
		} else if (agent.indexOf("webkit") > 0) {
			return "webkit";
		} else if (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0) {
			return "ie11";
		} else {
			return "Others";
		}
	}
}
