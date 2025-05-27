package com.jtissdev.core_api_utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

/**
 * Validateur pour l'annotation {@link ValidUUID}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente un UUID valide
 * en utilisant la méthode {@link UUID#fromString(String)}.
 * <p>
 * Les valeurs null ou vides sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 * @see ValidUUID
 * @see UUID
 */
public class ValidUUIDValidator implements ConstraintValidator<ValidUUID, String> {
	
	/**
	 * Vérifie si la valeur fournie représente un UUID valide.
	 * <p>
	 * Cette méthode considère les valeurs null ou vides comme valides.
	 * Pour les autres valeurs, elle tente de les convertir en UUID via
	 * {@link UUID#fromString(String)}.
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null, vide, ou représente un UUID valide; false sinon
	 * 
	 * @since 1.0.0
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) return true;
		try {
			UUID.fromString(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
