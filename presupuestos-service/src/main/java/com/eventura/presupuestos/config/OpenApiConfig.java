package com.eventura.presupuestos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Presupuestos Service API")
                        .version("1.0")
                        .description("Documentación de la API del microservicio de presupuestos"));
    }

    @Bean
    public GroupedOpenApi presupuestosApi() {
        return GroupedOpenApi.builder()
                .group("presupuestos")
                .pathsToMatch("/api/presupuestos/**")
                .build();
    }
}
