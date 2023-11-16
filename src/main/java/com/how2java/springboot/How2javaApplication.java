package com.how2java.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class How2javaApplication {
	public static void main(String[] args) {
		SpringApplication.run(How2javaApplication.class, args);
	}

}
