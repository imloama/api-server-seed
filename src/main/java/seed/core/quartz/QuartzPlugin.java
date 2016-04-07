package seed.core.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class QuartzPlugin {
	
	private static Logger logger =LoggerFactory.getLogger(QuartzPlugin.class);
	private SchedulerFactory sf = null;
	private Scheduler sched = null;
	private String config = "job.properties";
	private Properties properties;
	private ApplicationContext applicationContext;
	

	public QuartzPlugin(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean start() {
		sf = new StdSchedulerFactory();
		try {
			sched = sf.getScheduler();
		} catch (SchedulerException e) {
			new RuntimeException(e); 
		}
		loadProperties();
		Enumeration<?> enums = properties.keys();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement() + "";
			if (!key.endsWith("job")) {
				continue;
			}
			String cronKey = key.substring(0, key.lastIndexOf("job")) + "cron";
			String enable = key.substring(0, key.lastIndexOf("job")) + "enable";
			if (!isEnableJob(enable)) {
				continue;
			} 
			String jobClassName = properties.get(key) + "";
			String jobCronExp = properties.getProperty(cronKey);
			Class clazz;
			try {
				clazz = Class.forName(jobClassName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			JobDetail job = newJob(clazz).withIdentity(jobClassName, jobClassName).build();
			job.getJobDataMap().put(AbstractJob.KEY_APPLICATION_CONTEXT, applicationContext);
			
			CronTrigger trigger = newTrigger().withIdentity(jobClassName, jobClassName)
					.withSchedule(cronSchedule(jobCronExp)).build();
			Date ft = null;
			try {
				ft = sched.scheduleJob(job, trigger);
				sched.start();
			} catch (SchedulerException e) {
				new RuntimeException(e);
			}
			logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
					+ trigger.getCronExpression());
		}
		return true;
	} 


	private boolean isEnableJob(String enableKey) {
		String enable = properties.getProperty(enableKey);
		return Boolean.parseBoolean(enable);
	}

	private void loadProperties() {
		properties = new Properties(); 
		InputStream is = QuartzPlugin.class.getClassLoader().getResourceAsStream(config);
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean stop() {
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
