package seed.demo.jobs;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seed.core.quartz.AbstractJob;
import seed.demo.models.User;
import seed.demo.service.UserService;

public class TestJob  extends AbstractJob{

	public static final Logger LOG = LoggerFactory.getLogger(TestJob.class);
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			UserService service = this.getBean(context, UserService.class);
			List<User> users = service.selectAll();
			for(User user : users){
				LOG.debug(user.getCode()+","+user.getName());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}


	
	
	
}
