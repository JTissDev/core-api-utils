package com.jtissdev.commons.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour la classe PagedApiResponse.
 */
class PagedApiResponseTest {

    @Test
    void testSuccessMethodWithoutMessage() {
        // Arrange
        List<String> testData = Arrays.asList("item1", "item2", "item3");
        int page = 0;
        int size = 10;
        long totalElements = 25L;
        int totalPages = 3;

        // Act
        PagedApiResponse<String> response = PagedApiResponse.success(
                testData, page, size, totalElements, totalPages);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(testData, response.getData());
        assertNull(response.getMessage());
        assertEquals(page, response.getPage());
        assertEquals(size, response.getSize());
        assertEquals(totalElements, response.getTotalElements());
        assertEquals(totalPages, response.getTotalPages());
    }

    @Test
    void testSuccessMethodWithMessage() {
        // Arrange
        List<String> testData = Arrays.asList("item1", "item2", "item3");
        String message = "Success Message";
        int page = 0;
        int size = 10;
        long totalElements = 25L;
        int totalPages = 3;

        // Act
        PagedApiResponse<String> response = PagedApiResponse.success(
                testData, message, page, size, totalElements, totalPages);

        // Assert
        assertTrue(response.isSuccess());
        assertEquals(testData, response.getData());
        assertEquals(message, response.getMessage());
        assertEquals(page, response.getPage());
        assertEquals(size, response.getSize());
        assertEquals(totalElements, response.getTotalElements());
        assertEquals(totalPages, response.getTotalPages());
    }

    @Test
    void testSetters() {
        // Arrange
        PagedApiResponse<String> response = new PagedApiResponse<>();
        int page = 1;
        int size = 20;
        long totalElements = 100L;
        int totalPages = 5;

        // Act
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);

        // Assert
        assertEquals(page, response.getPage());
        assertEquals(size, response.getSize());
        assertEquals(totalElements, response.getTotalElements());
        assertEquals(totalPages, response.getTotalPages());
    }
}
