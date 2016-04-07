package seed.core.quartz;

import javax.sql.DataSource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

public abstract class AbstractJob implements Job {

	
	public static final String KEY_APPLICATION_CONTEXT = "QuartzSpringContext";
	
	public static final String KEY_DATASOURCE = "QuartzDataSource";
	
	
	public ApplicationContext getApplicationContext(JobExecutionContext context) {
		return (ApplicationContext) context.get(KEY_APPLICATION_CONTEXT);
	}


	public DataSource getDataSource(JobExecutionContext context) {
		return (DataSource) context.get(KEY_DATASOURCE);
	}


	
	
}
