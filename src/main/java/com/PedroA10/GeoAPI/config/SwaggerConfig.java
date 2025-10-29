package com.PedroA10.GeoAPI.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "GeoAPI",
    version = "1.0.0",
    description = "GeoAPI API Documentation",
    contact = @Contact(name = "Pedro Augusto de Souza Rodrigues", email = "pedro.API.REST@gmail.com"),
    license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT")
  ),
  security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
  name = "bearerAuth",
  type = SecuritySchemeType.HTTP,
  scheme = "bearer",
  bearerFormat = "JWT"
)

public class SwaggerConfig {
}
