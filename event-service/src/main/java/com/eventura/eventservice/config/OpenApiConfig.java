package com.eventura.eventservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
/*
import org.springdoc.core.GroupedOpenApi;
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la documentación OpenAPI (Swagger) del microservicio event-service.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Event Service API")
                        .description("API para la gestión de eventos")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación externa")
                        .url("https://example.com/docs"));
    }

/*
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("event-service-public")
                .pathsToMatch("/api/**")
                .build();
    }
*/
}
