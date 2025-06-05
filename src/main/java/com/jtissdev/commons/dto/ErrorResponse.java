package com.jtissdev.commons.dto;

import java.time.LocalDateTime;

/**
 * Structure standardisée pour représenter les erreurs dans les réponses API.
 * Cette classe encapsule les détails d'une erreur, y compris son code, son message,
 * l'horodatage et éventuellement des détails supplémentaires.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class ErrorResponse {

    private String code;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private Object details;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Construit une nouvelle instance ErrorResponse avec le code et le message fournis.
     *
     * @param code    Le code d'erreur, généralement utilisé pour identifier le type d'erreur.
     * @param message Le message d'erreur décrivant le problème.
     */
    public ErrorResponse(String code, String message) {
        this();
        this.code = code;
        this.message = message;
    }

    /**
     * Construit une nouvelle instance ErrorResponse avec le code, le message et le chemin fournis.
     *
     * @param code    Le code d'erreur.
     * @param message Le message d'erreur.
     * @param path    Le chemin de la requête qui a généré l'erreur.
     */
    public ErrorResponse(String code, String message, String path) {
        this(code, message);
        this.path = path;
    }

    /**
     * Construit une nouvelle instance ErrorResponse avec tous les paramètres.
     *
     * @param code    Le code d'erreur.
     * @param message Le message d'erreur.
     * @param path    Le chemin de la requête.
     * @param details Des détails supplémentaires sur l'erreur.
     */
    public ErrorResponse(String code, String message, String path, Object details) {
        this(code, message, path);
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
