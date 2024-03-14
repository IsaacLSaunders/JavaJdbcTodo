package com.jdbccrud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocumentation(){
        return new OpenAPI()
                .info(new Info()
                        .title("To Do List")
                        .description("Training Crud Application with JDBC")
                        );
    }

}
