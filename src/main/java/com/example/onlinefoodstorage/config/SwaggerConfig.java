package com.example.onlinefoodstorage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Online Food Storage", version = "3.2.4", description = "THIS ENVIRONMENT HAS BEEN CONFIGURED FOR THE TEST PROJECT"))
@SecurityScheme(
        name = "Authorization",
        bearerFormat = "JWT",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
