package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validateur pour l'annotation {@link FrenchPhoneNumber}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente un numéro de téléphone français valide,
 * c'est-à-dire un numéro commençant par 0 ou +33, suivi d'un chiffre entre 1 et 9,
 * puis de 8 chiffres supplémentaires.
 * <p>
 * Les valeurs null sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see FrenchPhoneNumber
 */
public class FrenchPhoneNumberValidator implements ConstraintValidator<FrenchPhoneNumber, String> {

	/**
	 * Vérifie si la valeur fournie représente un numéro de téléphone français valide.
	 * <p>
	 * Cette méthode considère les valeurs null comme valides.
	 * Pour les autres valeurs, elle vérifie qu'elles correspondent au format d'un numéro français:
	 * commençant par 0 ou +33, suivi d'un chiffre entre 1 et 9, puis de 8 chiffres supplémentaires.
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null ou représente un numéro de téléphone français valide; false sinon
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.matches("^(\\+33|0)[1-9](\\d{8})$");
	}
}
