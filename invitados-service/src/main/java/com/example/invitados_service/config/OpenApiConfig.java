package com.example.invitados_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI invitadosServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Invitados Service API")
                .description("API para gestión de invitados")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("Documentación del proyecto")
                .url("https://github.com/tu-repositorio/invitados-service"));
    }
}
