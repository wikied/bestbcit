package com.scriptofan.ecommerce;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.scriptofan.ecommerce.upload.StorageService;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Resource
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