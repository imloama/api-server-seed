package seed.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import seed.quartz.AbstractJob;

public class TestJob extends AbstractJob{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	}

	
}
