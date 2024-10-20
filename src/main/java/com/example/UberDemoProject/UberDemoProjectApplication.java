package com.example.UberDemoProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // for integrating reddis caching into methods
public class UberDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberDemoProjectApplication.class, args);
	}
}