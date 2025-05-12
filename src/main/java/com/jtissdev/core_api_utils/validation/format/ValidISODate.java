package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidISODateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidISODate {
	String message() default "Invalid ISO 8601 date format (expected yyyy-MM-dd)";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
