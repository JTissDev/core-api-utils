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

	@Test
	void testFullInfo_ReturnsValidMapWithCorrectKeys() {
		// Arrange
		String serviceName = "FullService";
		String version = "1.0.0";
		String description = "FullInfo Test Description";
		String developerEmail = "dev@example.com";
		String ownerEmail = "owner@example.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertNotNull(result);
		assertEquals(6, result.size());
		assertTrue(result.containsKey("service"));
		assertTrue(result.containsKey("version"));
		assertTrue(result.containsKey("description"));
		assertTrue(result.containsKey("developerContact"));
		assertTrue(result.containsKey("projectOwnerContact"));
		assertTrue(result.containsKey("timestamp"));
	}

	@Test
	void testFullInfo_ReturnsCorrectServiceNameInMap() {
		// Arrange
		String serviceName = "ServiceTest";
		String version = "1.2.3";
		String description = "Service test description";
		String developerEmail = "developer@test.com";
		String ownerEmail = "owner@test.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertEquals("ServiceTest", result.get("service"));
	}

	@Test
	void testFullInfo_ReturnsCorrectVersionInMap() {
		// Arrange
		String serviceName = "VersionTest";
		String version = "2.0.1";
		String description = "Version test description";
		String developerEmail = "dev@version.com";
		String ownerEmail = "owner@version.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertEquals("2.0.1", result.get("version"));
	}

	@Test
	void testFullInfo_ReturnsCorrectDescriptionInMap() {
		// Arrange
		String serviceName = "DescriptionTest";
		String version = "3.3.3";
		String description = "Test description";
		String developerEmail = "dev@description.com";
		String ownerEmail = "owner@description.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertEquals("Test description", result.get("description"));
	}

	@Test
	void testFullInfo_ReturnsCorrectDeveloperContactInMap() {
		// Arrange
		String serviceName = "DevContactTest";
		String version = "4.4.4";
		String description = "Developer contact test";
		String developerEmail = "developer@test.com";
		String ownerEmail = "owner@test.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertEquals("developer@test.com", result.get("developerContact"));
	}

	@Test
	void testFullInfo_ReturnsCorrectProjectOwnerContactInMap() {
		// Arrange
		String serviceName = "OwnerContactTest";
		String version = "5.5.5";
		String description = "Owner contact test";
		String developerEmail = "dev@test.com";
		String ownerEmail = "owner@test.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

		// Assert
		assertEquals("owner@test.com", result.get("projectOwnerContact"));
	}

	@Test
	void testFullInfo_TimestampIsGeneratedAndValidISO8601Format() {
		// Arrange
		String serviceName = "TimestampFullTest";
		String version = "6.6.6";
		String description = "Timestamp test for fullInfo";
		String developerEmail = "timestamp@fulltest.com";
		String ownerEmail = "owner@fulltest.com";

		// Act
		Map<String, Object> result = ApiInfoBuilder.fullInfo(serviceName, version, description, developerEmail, ownerEmail);

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