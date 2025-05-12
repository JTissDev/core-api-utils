package com.jtissdev.core_api_utils.requestgen;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for the buildInfo method in the ApiInfoBuilder class.
class ApiInfoBuilderTest {

	@Test
	void testBuildInfo_ReturnsValidMapWithCorrectKeys() {
		// Arrange
		String serviceName = "TestService";
		String version = "1.0.0";

		// Act
		Map<String, Object> result = ApiInfoBuilder.buildInfo(serviceName, version);

		// Assert
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.containsKey("service"));
		assertTrue(result.containsKey("version"));
		assertTrue(result.containsKey("timestamp"));
	}

	@Test
	void testBuildInfo_ReturnsCorrectServiceNameInMap() {
		// Arrange
		String serviceName = "MyService";
		String version = "2.5.3";

		// Act
		Map<String, Object> result = ApiInfoBuilder.buildInfo(serviceName, version);

		// Assert
		assertEquals("MyService", result.get("service"));
	}

	@Test
	void testBuildInfo_ReturnsCorrectVersionInMap() {
		// Arrange
		String serviceName = "AnotherService";
		String version = "3.0";

		// Act
		Map<String, Object> result = ApiInfoBuilder.buildInfo(serviceName, version);

		// Assert
		assertEquals("3.0", result.get("version"));
	}

	@Test
	void testBuildInfo_TimestampIsGeneratedAndValidISO8601Format() {
		// Arrange
		String serviceName = "TimestampService";
		String version = "4.7.1";

		// Act
		Map<String, Object> result = ApiInfoBuilder.buildInfo(serviceName, version);

		// Assert
		assertNotNull(result.get("timestamp"));
		assertTrue(result.get("timestamp") instanceof String);

		String timestamp = (String) result.get("timestamp");
		try {
			Instant.parse(timestamp); // Validate if the timestamp is valid ISO 8601
		} catch (DateTimeParseException e) {
			fail("Timestamp is not in a valid ISO 8601 format");
		}
	}
}