package com.jtissdev.core-api-utils.dto;

public class ApiResponse<T> {
	private boolean success;
	private T data;
	private String message;

	public ApiResponse() {}

	public ApiResponse(boolean success, T data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, data, null);
	}

	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<>(false, null, message);
	}

	// Getters & setters omitted pour concision
}