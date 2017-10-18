package bizfuse.checkin.work;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EntityScan
public class RestApp {
	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(RestApp.class, args);
		//test
	}
}
