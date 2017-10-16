package bizfuse.checkin.work.runner;

import java.util.Map;

import bizfuse.checkin.work.exception.RunJobException;

public interface IScriptRunnerService {
	public void runScript(String scriptFile, Map vars) throws RunJobException;
}
