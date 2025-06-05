package com.jtissdev.commons.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration commune pour OpenAPI/Swagger.
 * Cette classe configure la documentation OpenAPI pour les APIs Spring Boot,
 * incluant les informations de base, la sécurité, et d'autres paramètres.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Configuration
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true", matchIfMissing = true)
public class CommonOpenApiConfig {

    @Value("${spring.application.name:API Application}")
    private String applicationName;

    @Value("${api.version:1.0.0}")
    private String apiVersion;

    @Value("${api.description:REST API}")
    private String apiDescription;

    @Value("${api.contact.name:API Support}")
    private String contactName;

    @Value("${api.contact.email:support@example.com}")
    private String contactEmail;

    @Value("${api.contact.url:https://example.com/support}")
    private String contactUrl;

    @Value("${api.license.name:MIT License}")
    private String licenseName;

    @Value("${api.license.url:https://opensource.org/licenses/MIT}")
    private String licenseUrl;

    /**
     * Crée et configure un bean OpenAPI avec les informations de l'API et la configuration de sécurité.
     *
     * @return Un bean OpenAPI configuré.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .version(apiVersion)
                        .description(apiDescription)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail)
                                .url(contactUrl))
                        .license(new License()
                                .name(licenseName)
                                .url(licenseUrl)))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Entrez un token JWT pour l'authentification")));
    }
}
