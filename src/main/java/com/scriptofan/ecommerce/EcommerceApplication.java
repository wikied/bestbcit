package com.scriptofan.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.concurrent.Executor;


/**
 * Main running class for the application. Also currently handles
 * configuration, though it may be advisable to move that elsewhere
 * as the application grows.
 */
@SpringBootApplication
@EnableAsync
@Configuration
public class EcommerceApplication extends WebSecurityConfigurerAdapter implements CommandLineRunner
{

	@Autowired
    private Config config;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}


    /**
     * Configures Spring Security to disable authentication across all
     * endpoints. This was done to speed development, and should be
     * replaced later.
     *
     * @param http
     * @throws Exception
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").anonymous();
        http.csrf().disable();
	}


    /**
     * Enables Async.
     * @return
     */
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