package service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@ComponentScan(basePackages = {"service.implementations"})
@Configuration
public class ServiceConfig {
	//necesitamos crear un objeto RestClient
	@Bean
	public RestClient getClient() {
		return RestClient.create();
	}
}
