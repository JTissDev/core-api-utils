package com.jtissdev.commons.exceptions;

/**
 * Exception lancée lorsqu'une ressource demandée est introuvable.
 * Cette exception étend {@link ApiException} et est utilisée spécifiquement
 * pour indiquer qu'une entité ou ressource n'a pas pu être trouvée avec l'identifiant fourni.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class ResourceNotFoundException extends ApiException {

    private static final String DEFAULT_CODE = "RESOURCE_NOT_FOUND";

    /**
     * Construit une nouvelle instance ResourceNotFoundException avec un message personnalisé.
     *
     * @param message Le message d'erreur détaillant quelle ressource n'a pas été trouvée.
     */
    public ResourceNotFoundException(String message) {
        super(DEFAULT_CODE, message);
    }

    /**
     * Construit une nouvelle instance ResourceNotFoundException avec un message généré à partir
     * du type de ressource et de l'identifiant.
     *
     * @param resourceType Le type de ressource qui n'a pas été trouvée (ex: "User", "Product").
     * @param id           L'identifiant de la ressource qui n'a pas été trouvée.
     */
    public ResourceNotFoundException(String resourceType, Object id) {
        super(DEFAULT_CODE, String.format("%s with id %s not found", resourceType, id));
    }

    /**
     * Construit une nouvelle instance ResourceNotFoundException avec un code d'erreur personnalisé
     * et un message.
     *
     * @param code    Le code d'erreur personnalisé.
     * @param message Le message d'erreur.
     */
    public ResourceNotFoundException(String code, String message) {
        super(code, message);
    }
}
