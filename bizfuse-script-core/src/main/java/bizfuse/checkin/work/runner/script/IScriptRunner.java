package bizfuse.checkin.work.runner.script;

import java.util.Map;

import bizfuse.checkin.work.exception.RunJobException;

public interface IScriptRunner {
	public Map run(String fileName, Map<String, Object> param) throws RunJobException;
}
