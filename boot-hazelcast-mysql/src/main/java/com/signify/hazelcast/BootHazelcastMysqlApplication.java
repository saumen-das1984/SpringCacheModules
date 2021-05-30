package com.signify.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BootHazelcastMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootHazelcastMysqlApplication.class, args);
	}

}
