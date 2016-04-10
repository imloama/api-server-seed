package seed.core.quartz;

import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.google.common.collect.Lists;

public class QuartzFactory {

	private static final Logger logger = LoggerFactory.getLogger(QuartzFactory.class);

	private String config;
	private ApplicationContext context;

	public QuartzFactory(String config, ApplicationContext context) {
		this.config = config;
		this.context = context;
	}

	public SchedulerFactoryBean build() throws Exception {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource(config));
		propertiesFactoryBean.afterPropertiesSet();
		Properties props = propertiesFactoryBean.getObject();
		List<Trigger> triggers = Lists.newArrayList();
		List<JobModel> jobList = getJobs(props);
		for (JobModel job : jobList) {
			if (!job.isEnable()) {
				continue;
			}
			JobDetailFactoryBean detail = createJobDetail(job.getName(), Class.forName(job.getJob()));
			CronTriggerFactoryBean trigger = createCronTrigger(job.getName(), detail.getObject(), job.getCorn());
			CronTrigger t = trigger.getObject();
			triggers.add(t);
		}
		bean.setTriggers(triggers.toArray(new Trigger[0]));
		//bean.afterPropertiesSet();
		return bean;

	}

	private List<JobModel> getJobs(Properties properties) {
		List<JobModel> jobs = Lists.newArrayList();
		Enumeration<?> enums = properties.keys();
		while (enums.hasMoreElements()) {
			String key = enums.nextElement() + "";
			if (!key.endsWith("job")) {
				continue;
			}
			String namedot = key.substring(0, key.lastIndexOf("job"));
			String name = namedot.substring(0, namedot.length() - 1);
			String cronKey = namedot + "cron";
			String enable = namedot + "enable";
			String jobClassName = properties.get(key).toString();
			String jobCronExp = properties.getProperty(cronKey);
			JobModel job = new JobModel();
			job.setName(name);
			job.setJob(jobClassName);
			job.setCorn(jobCronExp);
			boolean isEnable = isEnableJob(properties, enable);
			job.setEnable(isEnable);
			logger.debug("job:[name]{},[class]{},[corn]{},[enable]{}",name,jobClassName,jobCronExp,isEnable);
			jobs.add(job);
		}
		return jobs;
	}

	private boolean isEnableJob(Properties properties, String enableKey) {
		String enable = properties.getProperty(enableKey);
		return Boolean.parseBoolean(enable);
	}

	private JobDetailFactoryBean createJobDetail(String name, Class<?> jobClass) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setName(name);
		factoryBean.setJobClass(jobClass);
		factoryBean.setApplicationContext(context);
		factoryBean.setApplicationContextJobDataKey(AbstractJob.KEY_APPLICATION_CONTEXT);
		// job has to be durable to be stored in DB:
		factoryBean.setDurability(true);
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}

//	private SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs) {
//		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//		factoryBean.setJobDetail(jobDetail);
//		factoryBean.setStartDelay(0L);
//		factoryBean.setRepeatInterval(pollFrequencyMs);
//		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//		// in case of misfire, ignore all missed triggers and continue :
//		factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
//		return factoryBean;
//	}

	private CronTriggerFactoryBean createCronTrigger(String name, JobDetail jobDetail, String corn)
			throws ParseException {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setName(name);
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setCronExpression(corn);
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}

}
