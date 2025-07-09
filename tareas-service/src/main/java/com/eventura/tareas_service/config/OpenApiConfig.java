package com.eventura.tareas_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la documentación OpenAPI (Swagger) del microservicio tareas-service.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tareasServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tareas Service API")
                        .description("Documentación de la API del microservicio de tareas")
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
