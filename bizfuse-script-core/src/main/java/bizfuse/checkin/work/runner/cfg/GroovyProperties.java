package bizfuse.checkin.work.runner.cfg;


import org.springframework.stereotype.Component;

@Component
public class GroovyProperties {
	private String path = "script";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
