package bizfuse.checkin.work.common;

/**
 * 此项目全局常量定义类
 * 
 * @author chenyh
 *
 */
public class CollectorConstant {

	/**
	 * 此常量用于定义往调度任务传脚本的key
	 */
	public static final String SCRIPT_NAME_KEY = "sript_name";

	/**
	 * 此常量用于定义往调度任务传参数的key
	 */
	public static final String SCRIPT_PARAM_KEY = "script_param";

	/**
	 * 在quartzJob中生命springContext的上下文标记
	 */
	public static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";

	/**
	 * 
	 */
	public static final String JOB_STATE_UNKNOW = "UNKNOW";
}
