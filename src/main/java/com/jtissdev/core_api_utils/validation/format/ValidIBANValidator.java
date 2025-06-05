package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validateur pour l'annotation {@link ValidIBAN}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente un IBAN (International Bank Account Number)
 * valide en termes de format. Il vérifie que la chaîne commence par un code pays à 2 lettres, suivi de 2
 * chiffres de contrôle, puis d'un numéro de compte pouvant contenir des lettres et des chiffres.
 * <p>
 * Note: Cette validation est basique et ne vérifie pas la somme de contrôle de l'IBAN.
 * Pour une validation complète, il faudrait implémenter l'algorithme de vérification MOD 97.
 * <p>
 * Les valeurs null sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see ValidIBAN
 */
public class ValidIBANValidator implements ConstraintValidator<ValidIBAN, String> {

	/**
	 * Vérifie si la valeur fournie représente un IBAN valide en termes de format.
	 * <p>
	 * Cette méthode considère les valeurs null comme valides.
	 * Pour les autres valeurs, elle supprime d'abord tous les espaces, puis vérifie
	 * que le format correspond à un code pays à 2 lettres, suivi de 2 chiffres de contrôle,
	 * puis d'un numéro de compte pouvant contenir des lettres et des chiffres (1 à 30 caractères).
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null ou représente un IBAN valide en termes de format; false sinon
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.replaceAll("\\s", "").matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$");
	}
}
