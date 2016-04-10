package seed.core.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

public abstract class AbstractJob implements Job {

	
	public static final String KEY_APPLICATION_CONTEXT = "QuartzSpringContext";
	
	
	public ApplicationContext getApplicationContext(JobExecutionContext context) {
		return (ApplicationContext) context.getJobDetail().getJobDataMap().get(KEY_APPLICATION_CONTEXT);
	}

	public <T> T getBean(JobExecutionContext context,Class<T> clasz){
		return getApplicationContext(context).getBean(clasz);
	}

	
	
}