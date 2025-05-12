package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidIBANValidator implements ConstraintValidator<ValidIBAN, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.replaceAll("\\s", "").matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$");
	}
}
