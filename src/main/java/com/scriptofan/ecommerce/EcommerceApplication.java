package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.scriptofan.ecommerce.upload.StorageService;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Autowired
	private StorageService storageService;

	public static void main(String[] args) {
	    try {

            Config.init();
            SpringApplication.run(EcommerceApplication.class, args);

        } catch (AlreadyInitializedException e) {
	        // init() must only be called once, but this is the entrypoint to the application.
        }
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}