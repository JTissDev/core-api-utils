package com.jtissdev.core_api_utils.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

	/**
	 * Class Description:
	 * ApiResponse is a generic class to encapsulate the structured response of an API.
	 * It contains a success flag, data of type T, and an optional message.
	 * <p>
	 * Method being tested:
	 * - static <T> ApiResponse<T> success(T data)
	 * Creates an ApiResponse object with success set to true and includes the provided data.
	 */

	@Test
	void testSuccessMethodWithNonNullData() {
		// Arrange
		String testData = "Test Data";

		// Act
		ApiResponse<String> response = ApiResponse.success(testData);

		// Assert
		assertTrue(response.isSuccess());
		assertEquals(testData, response.getData());
		assertNull(response.getMessage());
	}

	@Test
	void testSuccessMethodWithNullData() {
		// Arrange
		String testData = null;

		// Act
		ApiResponse<String> response = ApiResponse.success(testData);

		// Assert
		assertTrue(response.isSuccess());
		assertNull(response.getData());
		assertNull(response.getMessage());
	}
}