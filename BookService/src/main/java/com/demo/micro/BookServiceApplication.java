package com.demo.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;

@EnableDiscoveryClient
@SpringBootApplication
@ControllerAdvice("com.demo.micro.exceptions")
@ComponentScan(basePackages = {"com.demo.micro.controller", "com.demo.micro.service", "com.demo.micro.repository",})
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
