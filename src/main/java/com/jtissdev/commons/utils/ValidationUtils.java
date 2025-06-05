package com.jtissdev.commons.utils;

import com.jtissdev.commons.exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Utilitaires pour la validation des entrées.
 * Cette classe fournit des méthodes utiles pour valider différents types d'entrées
 * et lancer des exceptions appropriées en cas d'échec de validation.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public final class ValidationUtils {

    private ValidationUtils() {
        // Empêche l'instanciation
    }

    // Patterns for common validations
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+33|0)[1-9](\\d{8})$");

    private static final Pattern POSTAL_CODE_PATTERN = Pattern.compile("\\d{5}");

    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    );

    /**
     * Valide une adresse email.
     *
     * @param email L'adresse email à valider.
     * @return true si l'adresse email est valide, false sinon.
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Valide un numéro de téléphone français.
     *
     * @param phone Le numéro de téléphone à valider.
     * @return true si le numéro de téléphone est valide, false sinon.
     */
    public static boolean isValidFrenchPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Valide un code postal français.
     *
     * @param postalCode Le code postal à valider.
     * @return true si le code postal est valide, false sinon.
     */
    public static boolean isValidFrenchPostalCode(String postalCode) {
        return postalCode != null && POSTAL_CODE_PATTERN.matcher(postalCode).matches();
    }

    /**
     * Valide un UUID.
     *
     * @param uuid L'UUID à valider.
     * @return true si l'UUID est valide, false sinon.
     */
    public static boolean isValidUuid(String uuid) {
        return uuid != null && UUID_PATTERN.matcher(uuid).matches();
    }

    /**
     * Vérifie qu'une chaîne n'est pas vide.
     *
     * @param value Le texte à vérifier.
     * @param fieldName Le nom du champ pour le message d'erreur.
     * @throws ValidationException si la chaîne est vide.
     */
    public static void validateNotEmpty(String value, String fieldName) {
        if (StringUtils.isBlank(value)) {
            throw createValidationException(fieldName, "ne peut pas être vide");
        }
    }

    /**
     * Vérifie qu'un objet n'est pas null.
     *
     * @param value L'objet à vérifier.
     * @param fieldName Le nom du champ pour le message d'erreur.
     * @throws ValidationException si l'objet est null.
     */
    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw createValidationException(fieldName, "ne peut pas être null");
        }
    }

    /**
     * Vérifie qu'une valeur numérique est dans la plage spécifiée.
     *
     * @param value La valeur à vérifier.
     * @param min La valeur minimale (inclusive).
     * @param max La valeur maximale (inclusive).
     * @param fieldName Le nom du champ pour le message d'erreur.
     * @throws ValidationException si la valeur est en dehors de la plage.
     */
    public static void validateRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw createValidationException(fieldName, 
                    String.format("doit être entre %d et %d", min, max));
        }
    }

    /**
     * Vérifie qu'un email est valide.
     *
     * @param email L'email à valider.
     * @param fieldName Le nom du champ pour le message d'erreur.
     * @throws ValidationException si l'email est invalide.
     */
    public static void validateEmail(String email, String fieldName) {
        if (!isValidEmail(email)) {
            throw createValidationException(fieldName, "n'est pas une adresse email valide");
        }
    }

    /**
     * Crée une exception de validation avec un champ et un message.
     *
     * @param fieldName Le nom du champ en erreur.
     * @param message Le message d'erreur.
     * @return Une ValidationException avec le champ et le message spécifiés.
     */
    private static ValidationException createValidationException(String fieldName, String message) {
        Map<String, String> errors = new HashMap<>();
        errors.put(fieldName, message);
        return new ValidationException("Erreur de validation", errors);
    }
}
