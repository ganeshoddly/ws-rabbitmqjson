package com.example.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitmqjsonproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqjsonproducerApplication.class, args);
	}

}
