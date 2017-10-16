package bizfuse.checkin.work;

import org.quartz.SchedulerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@ComponentScan
public class SchedulerApp {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		new AnnotationConfigApplicationContext(SchedulerApp.class);

	}
}
