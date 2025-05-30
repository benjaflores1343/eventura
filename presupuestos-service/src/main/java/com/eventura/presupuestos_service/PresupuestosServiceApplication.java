package com.eventura.presupuestos_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.eventura.presupuestos.model")
@ComponentScan(basePackages = {"com.eventura.presupuestos_service", "com.eventura.presupuestos"})
@EnableJpaRepositories("com.eventura.presupuestos.repository")
public class PresupuestosServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresupuestosServiceApplication.class, args);
    }

}
