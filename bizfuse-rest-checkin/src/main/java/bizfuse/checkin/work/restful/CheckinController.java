package bizfuse.checkin.work.restful;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bizfuse.checkin.work.pool.IRunnerThreadPoolService;
import bizfuse.checkin.work.pool.exception.RequestPoolException;

@Controller
@RequestMapping("/")
public class CheckinController {
	private static final Logger log = LoggerFactory.getLogger(CheckinController.class);

	@Autowired
	private IRunnerThreadPoolService runnerThreadPoolService;

	@RequestMapping("/toWork")
	@ResponseBody
	public String toWork(@RequestParam Map vars) {
		log.info("调用上班打卡请求");
		String message = "上班打卡请求已提交,请稍后查看邮件";
		try {
			runnerThreadPoolService.addRequest("LD_TO_WORK.groovy", vars);
		} catch (RequestPoolException e) {
			message = "已有请求在处理,请稍后在试";
		}
		return message;
	}

	@RequestMapping("/offWork")
	@ResponseBody
	public String offWork(@RequestParam Map vars) {
		log.info("调用下班打卡请求");
		String message = "下班打卡请求已提交,请稍后查看邮件";
		try {
			runnerThreadPoolService.addRequest("LD_OFF_WORK.groovy", vars);
		} catch (RequestPoolException e) {
			message = "已有请求在处理,请稍后在试";
		}
		return message;
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(@RequestParam Map vars) {
		log.info("调用下班打卡请求");
		String message = "测试请求已提交,请稍后查看邮件";
		try {
			runnerThreadPoolService.addRequest("test.groovy", vars);
		} catch (RequestPoolException e) {
			message = "已有请求在处理,请稍后在试";
		}
		return message;
	}
}
