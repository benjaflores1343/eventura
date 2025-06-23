package com.eventura.serviciotareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.eventura.serviciotareas", "com.eventura.invitados"})
public class ServicioTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioTareasApplication.class, args);
	}

}
