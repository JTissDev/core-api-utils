package com.jtissdev.core_api_utils.exception;

import com.jtissdev.core_api_utils.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse<Object>> handleApiException(ApiException ex) {
		return ResponseEntity
				       .badRequest()
				       .body(ApiResponse.error("[" + ex.getCode() + "] " + ex.getMessage()));
	}
}
