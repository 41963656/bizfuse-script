package bizfuse.checkin.work.pool.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bizfuse.checkin.work.common.CollectorConstant;
import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.pool.IRunnerThreadPoolService;
import bizfuse.checkin.work.pool.exception.RequestPoolException;
import bizfuse.checkin.work.runner.dto.RunnerDTO;
import bizfuse.checkin.work.runner.service.IRunnerService;

@Service

public class RunnerThreadPoolServiceImpl implements IRunnerThreadPoolService {
	private static final Logger log = LoggerFactory.getLogger(RunnerThreadPoolServiceImpl.class);

	ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 0l, TimeUnit.MILLISECONDS,
			new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());

	@Autowired
	private IRunnerService runnerService;

	@Override
	public void addRequest(String scriptFile, Map vars) throws RequestPoolException {

		try {
			poolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						runScript(scriptFile, vars);
					} catch (RunJobException e) {
						log.warn("", e);
					}
				}
			});
		} catch (RejectedExecutionException e) {
			throw new RequestPoolException("", e);
		}
	}

	private void runScript(String scriptFile, Map vars) throws RunJobException {
		Map<String, Object> param = new HashMap(2);
		param.put(CollectorConstant.SCRIPT_NAME_KEY, scriptFile);
		param.put(CollectorConstant.SCRIPT_PARAM_KEY, vars);
		runnerService.run(new RunnerDTO(param));
	}

}
