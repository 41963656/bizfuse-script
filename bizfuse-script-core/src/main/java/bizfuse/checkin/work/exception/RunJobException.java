package bizfuse.checkin.work.exception;

/**
 * 对任务运行时的异常处理
 * 
 * @author chenyh
 *
 */
public class RunJobException extends Exception {

	public RunJobException() {
		super();

	}

	public RunJobException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public RunJobException(String message, Throwable cause) {
		super(message, cause);

	}

	public RunJobException(String message) {
		super(message);

	}

	public RunJobException(Throwable cause) {
		super(cause);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1108108425595383827L;

}
