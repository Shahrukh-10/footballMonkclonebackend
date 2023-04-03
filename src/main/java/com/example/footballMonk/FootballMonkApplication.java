package com.example.footballMonk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class },scanBasePackages = "com/example/footballMonk")
@EnableMongoRepositories
public class FootballMonkApplication {
	public static void main(String[] args) {
		SpringApplication.run(FootballMonkApplication.class, args);
	}

}
