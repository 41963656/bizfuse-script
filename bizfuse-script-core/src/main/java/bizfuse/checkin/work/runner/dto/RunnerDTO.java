package bizfuse.checkin.work.runner.dto;

import java.util.Map;

public class RunnerDTO {
	private Map jobDataMap;

	public Map getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(Map jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public RunnerDTO(Map jobDataMap) {
		super();
		this.jobDataMap = jobDataMap;
	}
}
