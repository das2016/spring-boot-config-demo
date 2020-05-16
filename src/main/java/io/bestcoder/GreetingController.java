package io.bestcoder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// do post call to /actuator/refresh after config changes
@RefreshScope
public class GreetingController {

	@Value("${app.sayhello}")
	private String greeting;

	@Value("${app.nothing: empty message}")
	private String empty;

	@Value("${app.list}")
	private List<String> list;

	@Value("#{${dbvalues}}")
	private Map<String, String> dbValues;

	@Autowired
	private DbSettings dbSettings;

	@Autowired
	private Environment env;

	@GetMapping("/greeting")
	public String sayHello() {
		return greeting + empty + list + dbValues + " ===> " + dbSettings.getConnection() + " " + dbSettings.getPort();
	}

	@GetMapping("/envdetails")
	public String envdetails() {
		return env.toString();
	}
}
