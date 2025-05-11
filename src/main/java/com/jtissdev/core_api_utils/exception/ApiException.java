package com.jtissdev.core_api_utils.exception;

public class ApiExtension extends RuntimeException {
	private final String code;

	public ApiException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}