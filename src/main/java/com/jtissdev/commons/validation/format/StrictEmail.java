package com.jtissdev.commons.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation pour valider qu'une chaîne est une adresse email valide selon des critères stricts.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = StrictEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrictEmail {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
