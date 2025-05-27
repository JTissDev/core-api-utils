package com.jtissdev.core_api_utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les identifiants UUID.
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent un UUID correctement formaté.
 * <p>
 * Un UUID valide doit respecter le format standard 8-4-4-4-12 caractères hexadécimaux,
 * par exemple: "123e4567-e89b-12d3-a456-426614174000".
 * <p>
 * La validation est effectuée par {@link ValidUUIDValidator}.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 * @see ValidUUIDValidator
 * @see java.util.UUID
 */
@Documented
@Constraint(validatedBy = ValidUUIDValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUUID {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid UUID format";
	
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
