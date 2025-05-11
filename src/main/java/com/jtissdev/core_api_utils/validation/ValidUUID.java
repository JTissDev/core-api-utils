package com.jtissdev.core_api_utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidUUIDValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUUID {
	String message() default "Invalid UUID format";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
