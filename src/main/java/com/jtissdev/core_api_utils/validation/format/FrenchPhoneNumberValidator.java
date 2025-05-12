package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FrenchPhoneNumberValidator implements ConstraintValidator<FrenchPhoneNumber, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.matches("^(\\+33|0)[1-9](\\d{8})$");
	}
}
