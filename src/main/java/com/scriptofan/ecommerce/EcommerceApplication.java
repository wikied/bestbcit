package com.scriptofan.ecommerce;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.scriptofan.ecommerce.upload.StorageService;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Autowired
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}