package seed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStoppedEvent;

import seed.core.quartz.QuartzPlugin;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication();
		ConfigurableApplicationContext context = SpringApplication.run(Application.class);
		QuartzPlugin plugin = new QuartzPlugin(context);
		plugin.start();
		app.addListeners(new ApplicationStopListener(plugin));
	}
	
	
	static class ApplicationStopListener implements ApplicationListener<ContextStoppedEvent>{

		private final QuartzPlugin quartzPlugin;
		
		public ApplicationStopListener(QuartzPlugin quartzPlugin){
			this.quartzPlugin = quartzPlugin;
		}
		
		@Override
		public void onApplicationEvent(ContextStoppedEvent event) {
			if(quartzPlugin!=null){
				quartzPlugin.stop();
			}
		}
		
	}
	
}
