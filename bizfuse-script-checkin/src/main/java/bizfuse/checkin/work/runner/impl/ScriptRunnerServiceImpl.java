package bizfuse.checkin.work.runner.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bizfuse.checkin.work.common.CollectorConstant;
import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.IScriptRunnerService;
import bizfuse.checkin.work.runner.dto.RunnerDTO;
import bizfuse.checkin.work.runner.service.IRunnerService;

@Service
public class ScriptRunnerServiceImpl implements IScriptRunnerService {
	@Autowired
	private IRunnerService runnerService;

	@Override
	public void runScript(String scriptFile, Map vars) throws RunJobException {
		Map<String, Object> param = new HashMap(2);
		param.put(CollectorConstant.SCRIPT_NAME_KEY, scriptFile);
		param.put(CollectorConstant.SCRIPT_PARAM_KEY, vars);
		runnerService.run(new RunnerDTO(param));
	}

	public IRunnerService getRunnerService() {
		return runnerService;
	}

	public void setRunnerService(IRunnerService runnerService) {
		this.runnerService = runnerService;
	}

}
