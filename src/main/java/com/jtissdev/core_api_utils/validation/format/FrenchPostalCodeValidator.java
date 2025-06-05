package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validateur pour l'annotation {@link FrenchPostalCode}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente un code postal français valide,
 * c'est-à-dire une séquence de 5 chiffres (par exemple "75001" pour Paris 1er).
 * <p>
 * Les valeurs null sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see FrenchPostalCode
 */
public class FrenchPostalCodeValidator implements ConstraintValidator<FrenchPostalCode, String> {

	/**
	 * Vérifie si la valeur fournie représente un code postal français valide.
	 * <p>
	 * Cette méthode considère les valeurs null comme valides.
	 * Pour les autres valeurs, elle vérifie qu'elles correspondent exactement à 5 chiffres.
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null ou représente un code postal français valide; false sinon
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.matches("\\d{5}");
	}
}
