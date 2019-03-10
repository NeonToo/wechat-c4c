package com.sap.csc;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

/*
 * @author I326950
 */
@SpringBootApplication
@EnableAsync
public class WechatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WechatApplication.class);
	}
	
	@Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("C4CRestRequest-");
        executor.initialize();
        
        return executor;
    }
	
	@Bean
	public RestTemplate restTemplateBuilder(RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
