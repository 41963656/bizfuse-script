package bizfuse.checkin.work.task;

import bizfuse.checkin.work.exception.RunJobException;

public interface IJobService {
	/**
	 * 上班打卡时间
	 * 
	 * @throws RunJobException
	 */
	public void toWork() throws RunJobException;

	/**
	 * 下班打卡时间
	 */
	public void offWork() throws RunJobException;
}
