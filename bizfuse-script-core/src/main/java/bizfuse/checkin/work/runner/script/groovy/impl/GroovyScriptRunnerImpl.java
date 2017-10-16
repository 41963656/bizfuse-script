package bizfuse.checkin.work.runner.script.groovy.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bizfuse.checkin.work.common.MD5Utils;
import bizfuse.checkin.work.exception.RunJobException;
import bizfuse.checkin.work.runner.cfg.GroovyProperties;
import bizfuse.checkin.work.runner.script.IScriptRunner;
import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * groovy脚本运行类
 * 
 * @author chenyh
 *
 */
@Service
public class GroovyScriptRunnerImpl implements IScriptRunner {
	// 脚本缓存
	private Map<String, Script> scriptRunner = new ConcurrentHashMap<String, Script>(10);

	private GroovyShell shell = new GroovyShell(Thread.currentThread().getContextClassLoader());

	@Autowired
	private GroovyProperties groovyProperties;

	/*
	 * (non-Javadoc)
	 * 
	 * @see bizfuse.collector.platform.groovy.IGroovyScriptRunner#run(java.lang.
	 * String, java.util.Map)
	 */
	public Map run(String fileName, Map<String, Object> param) throws RunJobException {
		Script script = null;
		synchronized (fileName.intern()) {
			try {
				// 定义脚本文件
				File scriptFile = new File(String.format("%s/%s", groovyProperties.getPath(), fileName));
				// 得到脚本文件的md5码
				String fileKey = MD5Utils.getMD5(scriptFile);

				// 判断使用缓存还是重新加载脚本件
				if (scriptRunner.containsKey(fileKey)) {
					script = this.loadScriptRunner(fileKey);
				} else {
					script = this.loadScriptFile(fileKey, scriptFile);
				}
				// 绑定参数
				script.setBinding(new Binding(param));
				script.invokeMethod("run", new Object[0]);
				Binding binding = (Binding) script.invokeMethod("getBinding", new Object[0]);
				return binding.getVariables();
			} catch (IOException e) {
				throw new RunJobException("md5生成失败", e);
			} catch (RuntimeException e) {
				throw new RunJobException("脚本运行时异常", e);
			}
		}
	}

	/**
	 * 通过缓存获取执行脚本对象
	 * 
	 * @param fileKey
	 * @return
	 */
	private Script loadScriptRunner(String fileKey) {
		return scriptRunner.get(fileKey);
	}

	/**
	 * 通过文件获取执行脚本对象
	 * 
	 * @deprecated
	 * 
	 * @param fileKey
	 * @param scriptFile
	 * @param param
	 * @return
	 * @throws RunJobException
	 */
	private Script loadScriptFile(String fileKey, File scriptFile, Map<String, Object> param) throws RunJobException {
		Script script;
		try {
			GroovyCodeSource source = new GroovyCodeSource(scriptFile);
			script = shell.parse(source);
			if (param != null) {
				for (Iterator<Entry<String, Object>> entrys = param.entrySet().iterator(); entrys.hasNext();) {
					Entry<String, Object> entry = entrys.next();
					script.setProperty(entry.getKey(), entry.getValue());
				}
			}
			scriptRunner.put(fileKey, script);
		} catch (IOException e) {
			throw new RunJobException("", e);
		}
		return script;
	}

	/**
	 * 通过文件获取执行脚本对象
	 * 
	 * @param fileKey
	 * @param scriptFile
	 * @param param
	 * @return
	 * @throws RunJobException
	 */
	private Script loadScriptFile(String fileKey, File scriptFile) throws RunJobException {
		Script script;
		try {
			GroovyCodeSource source = new GroovyCodeSource(scriptFile);
			script = shell.parse(source);
			scriptRunner.put(fileKey, script);

		} catch (IOException e) {
			throw new RunJobException("", e);
		}
		return script;
	}

	public Map<String, Script> getScriptRunner() {
		return scriptRunner;
	}

	public void setScriptRunner(Map<String, Script> scriptRunner) {
		this.scriptRunner = scriptRunner;
	}

	public GroovyShell getShell() {
		return shell;
	}

	public void setShell(GroovyShell shell) {
		this.shell = shell;
	}

	public GroovyProperties getGroovyProperties() {
		return groovyProperties;
	}

	public void setGroovyProperties(GroovyProperties groovyProperties) {
		this.groovyProperties = groovyProperties;
	}

}
