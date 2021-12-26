package com.example.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		"com.example.demo.models"
})
@EnableJpaRepositories(basePackages = {
		"com.example.demo.repositories"
})
@SpringBootApplication(scanBasePackages = {
		"com.example.demo.data",
		"com.example.demo.exceptions",
		"com.example.demo.controllers"
})
public class HotelRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelRestDemoApplication.class, args);
	}

}
