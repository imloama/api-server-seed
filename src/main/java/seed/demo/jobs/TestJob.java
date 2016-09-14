package seed.demo.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seed.core.quartz.AbstractJob;

public class TestJob  extends AbstractJob{

	public static final Logger LOG = LoggerFactory.getLogger(TestJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		/*
		try {
			UserServiceImpl service = this.getBean(context, UserServiceImpl.class);
			List<User> users = service.selectAll();
			for(User user : users){
				LOG.debug(user.getCode()+","+user.getName());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		 */
	}


	
	
	
}
