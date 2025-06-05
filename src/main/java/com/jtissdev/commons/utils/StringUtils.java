package com.jtissdev.commons.utils;

import java.text.Normalizer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Utilitaires pour la manipulation des chaînes de caractères.
 * Cette classe fournit des méthodes utiles pour travailler avec des chaînes de caractères
 * dans différents contextes d'application.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public final class StringUtils {

    private StringUtils() {
        // Empêche l'instanciation
    }

    /**
     * Vérifie si une chaîne est null, vide ou ne contient que des espaces blancs.
     *
     * @param str La chaîne à vérifier.
     * @return true si la chaîne est null, vide ou ne contient que des espaces blancs, false sinon.
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Vérifie si une chaîne n'est pas null, ni vide, ni composée uniquement d'espaces blancs.
     *
     * @param str La chaîne à vérifier.
     * @return true si la chaîne n'est pas null, ni vide, ni composée uniquement d'espaces blancs, false sinon.
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Tronque une chaîne à la longueur maximale spécifiée, ajoutant des points de suspension
     * si nécessaire.
     *
     * @param str La chaîne à tronquer.
     * @param maxLength La longueur maximale de la chaîne résultante, incluant les points de suspension si ajoutés.
     * @return La chaîne tronquée, ou la chaîne originale si elle est plus courte que maxLength.
     */
    public static String truncate(String str, int maxLength) {
        if (str == null) {
            return null;
        }
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Supprime les accents d'une chaîne de caractères.
     *
     * @param str La chaîne dont les accents doivent être supprimés.
     * @return La chaîne sans accents, ou null si l'entrée est null.
     */
    public static String removeAccents(String str) {
        if (str == null) {
            return null;
        }
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    /**
     * Convertit une chaîne en slug (URL-friendly).
     * Cette méthode supprime les accents, convertit en minuscules, et remplace
     * les espaces et les caractères non alphanumériques par des tirets.
     *
     * @param input La chaîne à convertir en slug.
     * @return Le slug généré, ou null si l'entrée est null.
     */
    public static String slugify(String input) {
        if (input == null) {
            return null;
        }
        String noAccents = removeAccents(input.toLowerCase());
        String noWhitespace = noAccents.replaceAll("\\s+", "-");
        return noWhitespace.replaceAll("[^a-z0-9\\-]", "").replaceAll("-+", "-");
    }

    /**
     * Génère une chaîne aléatoire de caractères alphanumériques de la longueur spécifiée.
     *
     * @param length La longueur de la chaîne à générer.
     * @return Une chaîne aléatoire de la longueur spécifiée.
     * @throws IllegalArgumentException si length est négatif.
     */
    public static String randomAlphanumeric(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative");
        }
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (alphanumeric.length() * Math.random());
            sb.append(alphanumeric.charAt(index));
        }
        return sb.toString();
    }

    /**
     * Génère un UUID comme chaîne de caractères.
     *
     * @return Une chaîne représentant un UUID nouvellement généré.
     */
    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Masque partiellement une chaîne de caractères pour la confidentialité.
     * Utile pour les informations sensibles comme les adresses email ou numéros de téléphone.
     *
     * @param input La chaîne à masquer.
     * @param visibleChars Le nombre de caractères à laisser visibles au début et à la fin.
     * @param maskChar Le caractère à utiliser pour le masquage.
     * @return La chaîne masquée, ou la chaîne originale si elle est trop courte pour être masquée.
     */
    public static String mask(String input, int visibleChars, char maskChar) {
        if (input == null) {
            return null;
        }
        if (input.length() <= 2 * visibleChars) {
            return input;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(input.substring(0, visibleChars));

        int maskLength = input.length() - 2 * visibleChars;
        for (int i = 0; i < maskLength; i++) {
            sb.append(maskChar);
        }

        sb.append(input.substring(input.length() - visibleChars));
        return sb.toString();
    }
}
