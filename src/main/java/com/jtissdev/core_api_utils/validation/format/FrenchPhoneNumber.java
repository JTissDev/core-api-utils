package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FrenchPhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FrenchPhoneNumber {
	String message() default "Invalid French phone number";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
