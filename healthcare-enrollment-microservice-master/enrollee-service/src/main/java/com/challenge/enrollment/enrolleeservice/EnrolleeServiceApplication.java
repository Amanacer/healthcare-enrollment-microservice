package com.challenge.enrollment.enrolleeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EnrolleeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrolleeServiceApplication.class, args);
	}

}
