package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les dates au format ISO 8601.
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent une date au format ISO 8601 (yyyy-MM-dd).
 * <p>
 * Exemples de dates valides:
 * <ul>
 *   <li>2023-01-15</li>
 *   <li>2020-02-29</li>
 * </ul>
 * <p>
 * La validation est effectuée par {@link ValidISODateValidator}.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see ValidISODateValidator
 * @see java.time.LocalDate
 */
@Documented
@Constraint(validatedBy = ValidISODateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidISODate {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid ISO 8601 date format (expected yyyy-MM-dd)";
	
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
