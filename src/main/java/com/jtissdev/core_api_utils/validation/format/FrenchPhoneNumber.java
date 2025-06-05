package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les numéros de téléphone français.
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent un numéro de téléphone français valide.
 * <p>
 * Un numéro de téléphone français valide peut commencer par 0 ou +33 suivi d'un chiffre
 * entre 1 et 9, puis de 8 chiffres supplémentaires. Par exemple:
 * <ul>
 *   <li>06 12 34 56 78</li>
 *   <li>+33 6 12 34 56 78</li>
 * </ul>
 * <p>
 * La validation est effectuée par {@link FrenchPhoneNumberValidator}.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see FrenchPhoneNumberValidator
 */
@Documented
@Constraint(validatedBy = FrenchPhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FrenchPhoneNumber {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid French phone number";

	/**
	 * Groupes de validation auxquels cette contrainte appartient.
	 * 
	 * @return Les groupes de validation
	 */
	Class<?>[] groups() default {};

	/**
	 * Payloads associés à cette contrainte.
	 * 
	 * @return Les payloads
	 */
	Class<? extends Payload>[] payload() default {};
}
