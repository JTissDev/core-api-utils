package com.jtissdev.commons.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * Validateur pour l'annotation StrictEmail.
 * Vérifie qu'une chaîne est une adresse email valide selon un modèle d'expression régulière strict.
 *
 * @author J.Tissier
 * @version 1.3.0
 * @since 1.0.0
 */
public class StrictEmailValidator implements ConstraintValidator<StrictEmail, String> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || EMAIL_PATTERN.matcher(value).matches();
    }
}
