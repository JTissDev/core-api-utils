package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validateur pour l'annotation {@link StrictEmail}.
 * <p>
 * Ce validateur vérifie qu'une chaîne de caractères représente une adresse email valide
 * selon un modèle d'expression régulière strict.
 * <p>
 * Les valeurs null sont considérées comme valides pour permettre
 * l'utilisation d'autres annotations comme @NotNull ou @NotEmpty si nécessaire.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see StrictEmail
 */
public class StrictEmailValidator implements ConstraintValidator<StrictEmail, String> {
	private static final Pattern EMAIL_PATTERN = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE
	);

	/**
	 * Vérifie si la valeur fournie représente une adresse email valide.
	 * <p>
	 * Cette méthode considère les valeurs null comme valides.
	 * Pour les autres valeurs, elle utilise un pattern regex pour valider le format de l'email.
	 *
	 * @param value La valeur à valider
	 * @param context Le contexte de validation
	 * @return true si la valeur est null ou représente une adresse email valide; false sinon
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || EMAIL_PATTERN.matcher(value).matches();
	}
}
