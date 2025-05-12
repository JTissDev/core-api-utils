package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ValidISODateValidator implements ConstraintValidator<ValidISODate, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) return true;
		try {
			LocalDate.parse(value); // yyyy-MM-dd
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
