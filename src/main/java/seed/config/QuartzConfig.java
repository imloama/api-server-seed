package seed.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import seed.core.quartz.QuartzFactory;

/**
 * quartz定时任务功能
 * 
 * @author 马兆永
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class QuartzConfig {

	private static final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);
	
	private String config = "/job.properties";
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws Exception {
		logger.info("初始化quartz定时器");
		QuartzFactory factory = new QuartzFactory(config,context);
		return factory.build();
	}
	
	




}
