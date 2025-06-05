package com.jtissdev.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Utilitaires pour la manipulation des dates.
 * Cette classe fournit des méthodes utiles pour travailler avec les dates et les heures
 * en utilisant les classes de l'API Java Time (java.time).
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public final class DateUtils {

    private DateUtils() {
        // Empêche l'instanciation
    }

    /**
     * Format de date ISO-8601 standard (yyyy-MM-dd).
     */
    public static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_DATE;

    /**
     * Format de date et heure ISO-8601 standard (yyyy-MM-dd'T'HH:mm:ss).
     */
    public static final DateTimeFormatter ISO_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Format de date française (dd/MM/yyyy).
     */
    public static final DateTimeFormatter FRENCH_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Format de date et heure française (dd/MM/yyyy HH:mm:ss).
     */
    public static final DateTimeFormatter FRENCH_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Obtient la date actuelle.
     *
     * @return La date actuelle sous forme de LocalDate.
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Obtient la date et l'heure actuelles.
     *
     * @return La date et l'heure actuelles sous forme de LocalDateTime.
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * Convertit une chaîne en LocalDate en utilisant le format spécifié.
     *
     * @param dateStr La chaîne de date à convertir.
     * @param formatter Le formateur à utiliser pour l'analyse.
     * @return La date convertie en LocalDate.
     * @throws DateTimeParseException Si la chaîne ne peut pas être analysée.
     */
    public static LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * Convertit une chaîne en LocalDateTime en utilisant le format spécifié.
     *
     * @param dateTimeStr La chaîne de date et heure à convertir.
     * @param formatter Le formateur à utiliser pour l'analyse.
     * @return La date et l'heure converties en LocalDateTime.
     * @throws DateTimeParseException Si la chaîne ne peut pas être analysée.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

    /**
     * Formate une LocalDate en chaîne en utilisant le format spécifié.
     *
     * @param date La date à formater.
     * @param formatter Le formateur à utiliser pour le formatage.
     * @return La date formatée en chaîne.
     */
    public static String formatDate(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

    /**
     * Formate une LocalDateTime en chaîne en utilisant le format spécifié.
     *
     * @param dateTime La date et l'heure à formater.
     * @param formatter Le formateur à utiliser pour le formatage.
     * @return La date et l'heure formatées en chaîne.
     */
    public static String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    /**
     * Calcule la différence en jours entre deux dates.
     *
     * @param startDate La date de début.
     * @param endDate La date de fin.
     * @return Le nombre de jours entre les deux dates.
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Ajoute un nombre spécifié de jours à une date.
     *
     * @param date La date de base.
     * @param days Le nombre de jours à ajouter (peut être négatif).
     * @return Une nouvelle date avec les jours ajoutés.
     */
    public static LocalDate addDays(LocalDate date, long days) {
        return date.plusDays(days);
    }

    /**
     * Convertit un Instant en LocalDateTime en utilisant la zone horaire par défaut.
     *
     * @param instant L'instant à convertir.
     * @return Le LocalDateTime correspondant dans la zone horaire par défaut.
     */
    public static LocalDateTime instantToLocalDateTime(Instant instant) {
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Convertit un LocalDateTime en Instant.
     *
     * @param localDateTime Le LocalDateTime à convertir.
     * @return L'Instant correspondant.
     */
    public static Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Vérifie si une date est entre deux autres dates (inclusivement).
     *
     * @param date La date à vérifier.
     * @param startDate La date de début de la plage.
     * @param endDate La date de fin de la plage.
     * @return true si la date est entre les dates de début et de fin (inclusivement), false sinon.
     */
    public static boolean isBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    /**
     * Vérifie si une date et heure est entre deux autres dates et heures (inclusivement).
     *
     * @param dateTime La date et l'heure à vérifier.
     * @param startDateTime La date et l'heure de début de la plage.
     * @param endDateTime La date et l'heure de fin de la plage.
     * @return true si la date et l'heure sont entre les dates et heures de début et de fin (inclusivement), false sinon.
     */
    public static boolean isBetween(LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return !dateTime.isBefore(startDateTime) && !dateTime.isAfter(endDateTime);
    }
}
