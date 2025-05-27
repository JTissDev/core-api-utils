package com.jtissdev.core_api_utils.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration pour la sérialisation et désérialisation JSON avec Jackson.
 * <p>
 * Cette classe configure un {@link ObjectMapper} avec le support pour les types de date/heure
 * de Java 8 (java.time) et désactive la sérialisation des dates sous forme de timestamps.
 * 
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ObjectMapperConfig {

	/**
	 * Crée et configure un {@link ObjectMapper} pour la sérialisation et désérialisation JSON.
	 * <p>
	 * La configuration inclut:
	 * <ul>
	 *   <li>L'enregistrement du module {@link JavaTimeModule} pour supporter les types java.time</li>
	 *   <li>La désactivation de la sérialisation des dates en timestamps numériques</li>
	 * </ul>
	 *
	 * @return Un {@link ObjectMapper} configuré
	 * 
	 * @since 1.0.0
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}
}
