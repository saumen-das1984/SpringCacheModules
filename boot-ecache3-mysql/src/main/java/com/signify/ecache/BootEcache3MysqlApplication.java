package com.signify.ecache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootEcache3MysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEcache3MysqlApplication.class, args);
	}

}
