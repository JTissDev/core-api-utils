package com.jtissdev.commons.exceptions;

import java.util.Map;

/**
 * Exception lancée lorsque des erreurs de validation se produisent.
 * Cette exception étend {@link ApiException} et fournit un mécanisme pour
 * capturer et communiquer plusieurs erreurs de validation simultanément.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class ValidationException extends ApiException {

    private static final String DEFAULT_CODE = "VALIDATION_ERROR";
    private final Map<String, String> errors;

    /**
     * Construit une nouvelle instance ValidationException avec un message et une carte des erreurs.
     *
     * @param message Le message d'erreur général.
     * @param errors  Une carte des champs en erreur et leurs messages associés.
     */
    public ValidationException(String message, Map<String, String> errors) {
        super(DEFAULT_CODE, message);
        this.errors = errors;
    }

    /**
     * Construit une nouvelle instance ValidationException avec un code personnalisé, un message
     * et une carte des erreurs.
     *
     * @param code    Le code d'erreur personnalisé.
     * @param message Le message d'erreur général.
     * @param errors  Une carte des champs en erreur et leurs messages associés.
     */
    public ValidationException(String code, String message, Map<String, String> errors) {
        super(code, message);
        this.errors = errors;
    }

    /**
     * Récupère la carte des erreurs de validation.
     *
     * @return Une carte où les clés sont les noms des champs et les valeurs sont les messages d'erreur.
     */
    public Map<String, String> getErrors() {
        return errors;
    }
}
