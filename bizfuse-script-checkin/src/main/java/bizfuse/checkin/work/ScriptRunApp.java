package bizfuse.checkin.work;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.IScriptRunnerService;

@Configuration
@ComponentScan
public class ScriptRunApp {
	private static final Logger log = LoggerFactory.getLogger(ScriptRunApp.class);

	@Autowired
	private IScriptRunnerService scriptRunnerService;

	private static ApplicationContext context;

	public static void main(String[] args) throws RunJobException, FileNotFoundException, IOException {
		context = new AnnotationConfigApplicationContext(ScriptRunApp.class);
		ScriptRunApp runApp = context.getBean(ScriptRunApp.class);
		runApp.doit(args);
	}

	private boolean isVaild(String[] args) {
		if (args != null && args.length > 0) {
			return true;
		}
		log.warn("没有有效参数");
		return false;
	}

	private Map loadProp(String[] args) throws FileNotFoundException, IOException {
		Map vars = new HashMap(0);
		if (args != null && args.length > 1) {
			Properties pop = new Properties();
			pop.load(new FileInputStream(args[1]));
			vars = pop;
		}
		return vars;
	}

	public void doit(String[] args) {
		try {
			if (isVaild(args)) {
				String groovyFileName = args[0];
				Map vars = loadProp(args);
				scriptRunnerService.runScript(groovyFileName, vars);
			}
		} catch (RunJobException e) {
			log.error("脚本运行时异常", e);
		} catch (FileNotFoundException e) {
			log.error("文件不存在", e);
		} catch (IOException e) {
			log.error("文件读取失败", e);
		}
	}

}
