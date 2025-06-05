package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les IBAN (International Bank Account Number).
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent un IBAN valide.
 * <p>
 * Un IBAN valide commence par un code pays à 2 lettres, suivi de 2 chiffres de contrôle,
 * puis d'un numéro de compte pouvant contenir des lettres et des chiffres.
 * La longueur totale varie selon les pays (par exemple, 27 caractères pour la France).
 * <p>
 * La validation est effectuée par {@link ValidIBANValidator}.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 * @see ValidIBANValidator
 */
@Documented
@Constraint(validatedBy = ValidIBANValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIBAN {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid IBAN format";

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
