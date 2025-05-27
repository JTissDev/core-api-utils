package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Annotation de validation pour les codes postaux français.
 * <p>
 * Cette annotation peut être appliquée aux champs ou paramètres de type String
 * pour valider qu'ils contiennent un code postal français valide.
 * <p>
 * Un code postal français valide est composé de 5 chiffres, par exemple "75001" pour Paris 1er.
 * Pour les départements d'outre-mer, les codes commencent par 97, suivis de 1 à 6 pour 
 * identifier le territoire spécifique.
 * <p>
 * La validation est effectuée par {@link FrenchPostalCodeValidator}.
 *
 * @author J.Tissier
 * @version 1.0.0
 * @since 1.0.0
 * @see FrenchPostalCodeValidator
 */
@Documented
@Constraint(validatedBy = FrenchPostalCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FrenchPostalCode {
	/**
	 * Message d'erreur à afficher lorsque la validation échoue.
	 * 
	 * @return Le message d'erreur par défaut
	 */
	String message() default "Invalid French postal code format";
	
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
