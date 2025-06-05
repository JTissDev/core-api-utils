package com.jtissdev.commons.exceptions;

import com.jtissdev.commons.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire global pour convertir les exceptions en réponses HTTP cohérentes.
 * Cette classe capture différents types d'exceptions et les transforme en
 * réponses API standardisées avec des codes d'état HTTP appropriés.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Gère les exceptions de type ApiException.
     *
     * @param ex      L'exception ApiException capturée.
     * @param request La requête HTTP qui a généré l'exception.
     * @return Une réponse API standardisée avec le message d'erreur et un code d'état 400.
     */
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Object>> handleApiException(
            ApiException ex, HttpServletRequest request) {

        logger.error("API Exception: {} - {}", ex.getCode(), ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Gère les exceptions de type ResourceNotFoundException.
     *
     * @param ex      L'exception ResourceNotFoundException capturée.
     * @param request La requête HTTP qui a généré l'exception.
     * @return Une réponse API standardisée avec le message d'erreur et un code d'état 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {

        logger.error("Resource not found: {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    /**
     * Gère les exceptions de validation.
     *
     * @param ex      L'exception ValidationException capturée.
     * @param request La requête HTTP qui a généré l'exception.
     * @return Une réponse API standardisée avec les détails des erreurs de validation et un code d'état 422.
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            ValidationException ex, HttpServletRequest request) {

        logger.error("Validation error: {}", ex.getMessage());

        ApiResponse<Map<String, String>> response = new ApiResponse<>(false, ex.getErrors(), ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    /**
     * Gère les exceptions de validation des arguments de méthode.
     *
     * @param ex      L'exception MethodArgumentNotValidException capturée.
     * @param request La requête HTTP qui a généré l'exception.
     * @return Une réponse API standardisée avec les détails des erreurs de validation et un code d'état 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        logger.error("Validation error on request parameters: {}", errors);

        ApiResponse<Map<String, String>> response = new ApiResponse<>(false, errors, "Validation failed");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    /**
     * Gère toutes les autres exceptions non capturées.
     *
     * @param ex      L'exception capturée.
     * @param request La requête HTTP qui a généré l'exception.
     * @return Une réponse API standardisée avec un message d'erreur générique et un code d'état 500.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse<Object>> handleException(
            Exception ex, HttpServletRequest request) {

        logger.error("Unhandled exception: {}", ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Une erreur interne est survenue"));
    }
}
