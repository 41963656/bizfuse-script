package bizfuse.checkin.work.pool;

import java.util.Map;

import bizfuse.checkin.work.pool.exception.RequestPoolException;

public interface IRunnerThreadPoolService {

	public void addRequest(String scriptFile, Map vars) throws RequestPoolException;
}
