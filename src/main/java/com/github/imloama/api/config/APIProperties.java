package com.github.imloama.api.config;

import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * api配置文件 
 * @author 马兆永
 * @time 2016年9月14日上午11:42:45
 */
@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class APIProperties {
	
	private String prefix = "/api";
	
	/**
	 * 哪些url不做权限过滤
	 */
	private List<String> excludeUrls;

	private long workerId = 0L;
	private long datacenterId = 0L;
	
	
	
}
