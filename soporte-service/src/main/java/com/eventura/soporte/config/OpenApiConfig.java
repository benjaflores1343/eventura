package com.eventura.soporte.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la documentación OpenAPI (Swagger) del microservicio soporte-service.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI soporteServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Soporte Service API")
                        .description("Documentación de la API del microservicio de soporte")
                        .version("1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
