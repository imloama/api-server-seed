package com.github.imloama.api.config;

import cn.hutool.core.lang.Snowflake;
import com.github.imloama.api.config.jwt.JWTTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private APIProperties props;
	
	@Bean
	@Order
	public ServletRegistrationBean statViewServlet() {
		StatViewServlet servlet = new StatViewServlet();
		ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/druid/*");
		return bean;
	}
	
	/**
	 * druid的url监控
	 * @return
	 */
	@Bean
	public FilterRegistrationBean webStatFilter(){
		WebStatFilter filter = new WebStatFilter();
		FilterRegistrationBean bean = new FilterRegistrationBean(filter);
		bean.addUrlPatterns("/*");
		bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.font,/druid/*");
		return bean;
	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		JWTTokenInterceptor interceptor = new JWTTokenInterceptor();
		interceptor.setProps(props);
		registry.addInterceptor(interceptor);
	}

	@Bean
	public Snowflake defaultSnowflake(){
		Snowflake flake = new Snowflake(props.getWorkerId(),props.getDatacenterId());
		return  flake;
	}


}
