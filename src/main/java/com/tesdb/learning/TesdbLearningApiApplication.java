package com.tesdb.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TesdbLearningApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesdbLearningApiApplication.class, args);
		System.out.println("Welcome to E-Learning");
	}

}
