package es.iespuertodelacruz.jcr.tiktaktoe.shared.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api TikTakToeYEPAAAAAA", version = "v1"), tags = {
                @Tag(name = "V1", description = "Acceso Libre"),
                @Tag(name = "V2", description = "Requiere Autenticacion"),
                @Tag(name = "V3", description = "Requiere Autorizacion") })
public class SwaggerConfig {
        @Bean
        public OpenAPI customizeOpenAPI() {
                final String securitySchemeName = "bearerAuth";
                return new OpenAPI()
                                .addSecurityItem(new SecurityRequirement()
                                                .addList(securitySchemeName))
                                .components(new Components()
                                                .addSecuritySchemes(securitySchemeName,
                                                                new SecurityScheme()
                                                                                .name(securitySchemeName)
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")));
        }

}
