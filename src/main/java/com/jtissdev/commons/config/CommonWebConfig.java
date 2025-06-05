package com.jtissdev.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration web commune pour les applications Spring MVC.
 * Cette classe fournit des configurations pour CORS, journalisation des requêtes,
 * et d'autres fonctionnalités web communes.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Configuration
public class CommonWebConfig implements WebMvcConfigurer {

    /**
     * Configure les règles CORS pour toutes les routes.
     *
     * @param registry Le registre CORS à configurer.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Dans un environnement de production, spécifiez les domaines exacts
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    /**
     * Crée un filtre pour journaliser les requêtes entrantes.
     * Ce filtre est utile pour le débogage et le suivi des requêtes.
     *
     * @return Un filtre configuré pour la journalisation des requêtes.
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
