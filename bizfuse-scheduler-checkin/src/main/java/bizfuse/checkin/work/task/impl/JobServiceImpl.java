package bizfuse.checkin.work.task.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bizfuse.checkin.work.common.CollectorConstant;
import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.dto.RunnerDTO;
import bizfuse.checkin.work.runner.service.IRunnerService;
import bizfuse.checkin.work.task.IJobService;

@Component
public class JobServiceImpl implements IJobService {
	private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

	@Autowired
	private IRunnerService runnerService;

	public IRunnerService getRunnerService() {
		return runnerService;
	}

	public void setRunnerService(IRunnerService runnerService) {
		this.runnerService = runnerService;
	}

	public JobServiceImpl() {
		log.info("Quartzjob初始化成功...");
	}

	@Override
	@Scheduled(cron = "0 30 8 ? * MON-FRI")
	public void toWork() throws RunJobException {
		log.info("上班打卡");
		Map<String, String> param = new HashMap(1);
		param.put(CollectorConstant.SCRIPT_NAME_KEY, "LD_TO_WORK.groovy");
		runnerService.run(new RunnerDTO(param));
	}

	@Override
	@Scheduled(cron = "0 00 18 ? * MON-FRI")
	public void offWork() throws RunJobException {
		log.info("下班打卡");
		Map<String, String> param = new HashMap(1);
		param.put(CollectorConstant.SCRIPT_NAME_KEY, "LD_OFF_WORK.groovy");
		runnerService.run(new RunnerDTO(param));
	}
}
