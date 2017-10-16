package bizfuse.checkin.work.runner.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bizfuse.checkin.work.common.CollectorConstant;
import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.dto.RunnerDTO;
import bizfuse.checkin.work.runner.script.IScriptRunner;
import bizfuse.checkin.work.runner.service.IRunnerService;

@Service
public class RunnerServiceImpl implements IRunnerService {

	private static final Logger logger = LoggerFactory.getLogger(RunnerServiceImpl.class);
	@Autowired
	private IScriptRunner scriptRunner;

	@Override
	public void run(RunnerDTO runner) throws RunJobException {
		Map data = runner.getJobDataMap();
		String scriptName = (String) data.get(CollectorConstant.SCRIPT_NAME_KEY);
		Map param = (Map) data.get(CollectorConstant.SCRIPT_PARAM_KEY);
		Map retValue = scriptRunner.run(scriptName, param);
		this.printRetValue(retValue);
	}

	/**
	 * 打印脚本执行返回结果集
	 * 
	 * @param retValue
	 */
	private void printRetValue(Map retValue) {
		if (retValue == null || retValue.isEmpty()) {
			logger.info("Result is Empty!");
			return;
		}
		for (Iterator<Map.Entry<String, Object>> iter = retValue.entrySet().iterator(); iter.hasNext();) {
			Entry<String, Object> entry = iter.next();
			logger.info("key:{},value:{}", entry.getKey(), entry.getValue());
		}
	}
}
