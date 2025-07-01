package com.gestor_tiendas.gestor_tiendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                                .title("API 2025 Gestion de Tiendas")
                                .version("2.0")
                                .description("Documentacion de API para sistema de gestion de tiendas")
        );
    }


}
