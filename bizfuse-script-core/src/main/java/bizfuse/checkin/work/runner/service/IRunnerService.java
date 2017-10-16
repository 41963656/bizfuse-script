package bizfuse.checkin.work.runner.service;

import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.dto.RunnerDTO;

public interface IRunnerService {
	public void run(RunnerDTO runne) throws RunJobException;
}
