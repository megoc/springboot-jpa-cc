package com.flymegoc.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootJpaCcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaCcApplication.class, args);
	}
}
