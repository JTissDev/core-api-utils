package com.jtissdev.commons.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour la classe ApiResponse.
 */
class ApiResponseTest {

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

    @Test
    void testSuccessMethodWithMessage() {
        // Arrange
        String testData = "Test Data";
        String message = "Success Message";

        // Act
        ApiResponse<String> response = ApiResponse.success(testData, message);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(testData, response.getData());
        assertEquals(message, response.getMessage());
    }

    @Test
    void testErrorMethod() {
        // Arrange
        String errorMessage = "Error Message";

        // Act
        ApiResponse<String> response = ApiResponse.error(errorMessage);

        // Assert
        assertFalse(response.isSuccess());
        assertNull(response.getData());
        assertEquals(errorMessage, response.getMessage());
    }

    @Test
    void testSetters() {
        // Arrange
        ApiResponse<String> response = new ApiResponse<>();
        String testData = "Test Data";
        String message = "Test Message";

        // Act
        response.setSuccess(true);
        response.setData(testData);
        response.setMessage(message);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(testData, response.getData());
        assertEquals(message, response.getMessage());
    }
}
