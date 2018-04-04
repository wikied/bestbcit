package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.CSVParser.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@Configuration
public class EcommerceApplication extends WebSecurityConfigurerAdapter implements CommandLineRunner {

	@Autowired
	private StorageService storageService;

	@Autowired
    private Config config;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").anonymous();
	}


	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		// executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

}