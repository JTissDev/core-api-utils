package com.jtissdev.core_api_utils.exception;

import com.jtissdev.core_api_utils.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestionnaire global d'exceptions pour les contrôleurs REST.
 * <p>
 * Cette classe intercepte les exceptions lancées par les contrôleurs REST
 * et les transforme en réponses HTTP appropriées avec un format standardisé.
 * Elle utilise {@link ApiResponse} pour encapsuler les détails de l'erreur.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Gère les exceptions de type {@link ApiException}.
	 * <p>
	 * Cette méthode intercepte les {@link ApiException} et les transforme en réponses HTTP 400 (Bad Request)
	 * avec un corps contenant un objet {@link ApiResponse} indiquant l'échec et incluant le code et
	 * le message d'erreur formatés.
	 *
	 * @param ex L'exception {@link ApiException} à traiter
	 * @return Un {@link ResponseEntity} avec un statut 400 et un corps contenant les détails de l'erreur
	 * 
	 * @since 1.0.0
	 */
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
		return ResponseEntity
				       .badRequest()
				       .body(ApiResponse.error("[" + ex.getCode() + "] " + ex.getMessage()));
	}
}
