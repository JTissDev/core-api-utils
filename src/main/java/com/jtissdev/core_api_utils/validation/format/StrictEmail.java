package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les adresses email avec des règles strictes.
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent une adresse email valide selon des critères plus
 * stricts que la validation standard de Jakarta Bean Validation.
 * <p>
 * La validation est effectuée par {@link StrictEmailValidator} qui applique des règles
 * plus strictes concernant la structure de l'adresse email, les caractères autorisés,
 * et les domaines valides.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 * @see StrictEmailValidator
 */
@Documented
@Constraint(validatedBy = StrictEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrictEmail {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid email format";
	
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
