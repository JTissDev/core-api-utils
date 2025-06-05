package com.jtissdev.commons.exceptions;

/**
 * Exception de base pour toutes les erreurs d'API.
 * Cette exception est utilisée pour signaler des erreurs spécifiques à l'API,
 * incluant un code d'erreur pour faciliter l'identification du type d'erreur.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class ApiException extends RuntimeException {

    private final String code;

    /**
     * Construit une nouvelle instance ApiException avec le code et le message spécifiés.
     *
     * @param code    Le code d'erreur associé à cette exception.
     * @param message Le message d'erreur décrivant la cause de l'exception.
     */
    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Construit une nouvelle instance ApiException avec le code, le message et la cause spécifiés.
     *
     * @param code    Le code d'erreur associé à cette exception.
     * @param message Le message d'erreur.
     * @param cause   La cause de cette exception.
     */
    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Récupère le code d'erreur associé à cette exception.
     *
     * @return Le code d'erreur.
     */
    public String getCode() {
        return code;
    }
}
