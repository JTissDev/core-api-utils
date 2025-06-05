package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Validateur pour l'annotation {@link ValidISODate}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente une date valide
 * au format ISO 8601 (yyyy-MM-dd) en utilisant la méthode {@link LocalDate#parse(CharSequence)}.
 * <p>
 * Les valeurs null ou vides sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see ValidISODate
 * @see LocalDate
 */
public class ValidISODateValidator implements ConstraintValidator<ValidISODate, String> {
	
	/**
	 * Vérifie si la valeur fournie représente une date valide au format ISO 8601.
	 * <p>
	 * Cette méthode considère les valeurs null ou vides comme valides.
	 * Pour les autres valeurs, elle tente de les convertir en LocalDate via
	 * {@link LocalDate#parse(CharSequence)}, qui attend le format yyyy-MM-dd.
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null, vide, ou représente une date ISO 8601 valide; false sinon
	 * 
	 * @since 1.0.0
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) return true;
		try {
			LocalDate.parse(value); // yyyy-MM-dd
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
