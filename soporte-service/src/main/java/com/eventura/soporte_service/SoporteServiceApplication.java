package com.eventura.soporte_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.eventura.soporte.repository")
@EntityScan(basePackages = "com.eventura.soporte.model")
@ComponentScan(basePackages = {"com.eventura.soporte.controller", "com.eventura.soporte.service"})
public class SoporteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoporteServiceApplication.class, args);
	}

}
