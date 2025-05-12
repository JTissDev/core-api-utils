package com.jtissdev.core_api_utils.validation.format;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

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
