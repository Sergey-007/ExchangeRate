package com.application.exchangeRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.application.exchangeRate"})
public class TestAbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAbApplication.class, args);
	}
}
