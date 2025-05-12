package com.jtissdev.core_api_utils.requestgen;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApiHelpStructureTest {

	/**
	 * Test class for ApiHelpStructure.
	 * <p>
	 * It tests the static methods provided by the ApiHelpStructure class, specifically verifying the correctness
	 * of their returned data structures for APIs. This focuses on the `standardHeaders` method.
	 */

	@Test
	void testStandardHeaders_ContainsExpectedKeysAndValues() {
		// Act
		Map<String, Object> headers = ApiHelpStructure.standardHeaders();

		// Assert
		assertNotNull(headers, "Headers map should not be null");
		assertEquals(3, headers.size(), "Headers map should contain exactly 3 entries");
		assertTrue(headers.containsKey("X-Help-Request"), "'X-Help-Request' should be present in the headers");
		assertTrue(headers.containsKey("X-Request-Type"), "'X-Request-Type' should be present in the headers");
		assertTrue(headers.containsKey("Accept"), "'Accept' should be present in the headers");
		assertEquals(true, headers.get("X-Help-Request"), "'X-Help-Request' value should be true");
		assertEquals("HELP", headers.get("X-Request-Type"), "'X-Request-Type' value should be 'HELP'");
		assertEquals("application/json", headers.get("Accept"), "'Accept' value should be 'application/json'");
	}

	@Test
	void testStandardHeaders_KeyCaseSensitivity() {
		// Act
		Map<String, Object> headers = ApiHelpStructure.standardHeaders();

		// Assert
		assertFalse(headers.containsKey("x-help-request"), "Headers should be case-sensitive and not contain 'x-help-request'");
		assertFalse(headers.containsKey("x-request-type"), "Headers should be case-sensitive and not contain 'x-request-type'");
		assertFalse(headers.containsKey("accept"), "Headers should be case-sensitive and not contain 'accept'");
	}

	@Test
	void testStandardHeaders_NoExtraEntries() {
		// Act
		Map<String, Object> headers = ApiHelpStructure.standardHeaders();

		// Assert
		assertEquals(3, headers.size(), "Headers should not contain extra entries");
		assertTrue(headers.keySet().containsAll(Map.of(
				"X-Help-Request", true,
				"X-Request-Type", "HELP",
				"Accept", "application/json"
		).keySet()), "Unexpected or extra keys are present in the headers");
	}

	@Test
	void testStandardResponseBody_CorrectStructureAndValues() {
		// Arrange
		String endpoint = "/api/test";
		String description = "Test API endpoint";
		Map<String, Object> exampleRequest = Map.of("method", "POST", "param", "value");

		// Act
		Map<String, Object> responseBody = ApiHelpStructure.standardResponseBody(endpoint, description, exampleRequest);

		// Assert
		assertNotNull(responseBody, "Response body map should not be null");
		assertEquals(endpoint, responseBody.get("endpoint"), "Endpoint should match the input value");
		assertEquals(description, responseBody.get("description"), "Description should match the input value");
		assertEquals(ApiHelpStructure.standardHeaders(), responseBody.get("headers"), "Headers should match the standard headers");
		assertEquals(exampleRequest, responseBody.get("request"), "Request should match the input example request");
		assertNotNull(responseBody.get("response"), "Response field should not be null");

		Map<String, Object> responseField = (Map<String, Object>) responseBody.get("response");
		assertEquals("200 OK", responseField.get("status"), "Response status should be '200 OK'");
		assertNotNull(responseField.get("body"), "Response body should not be null");

		Map<String, Object> responseBodyField = (Map<String, Object>) responseField.get("body");
		assertEquals("Example response body", responseBodyField.get("info"), "Response body 'info' field should have a default value");
	}

	@Test
	void testStandardResponseBody_NullParameters() {
		// Arrange
		String endpoint = null;
		String description = null;
		Map<String, Object> exampleRequest = null;

		// Act
		Map<String, Object> responseBody = ApiHelpStructure.standardResponseBody(endpoint, description, exampleRequest);

		// Assert
		assertNotNull(responseBody, "Response body map should not be null");
		assertEquals("default-endpoint", responseBody.get("endpoint"), "Default value should be 'default-endpoint' when input endpoint is null");
		assertEquals("No description provided", responseBody.get("description"), "Default value should be 'No description provided' when input description is null");
		assertEquals(ApiHelpStructure.standardHeaders(), responseBody.get("headers"), "Headers should match the standard headers");
		assertTrue(((Map<?, ?>) responseBody.get("request")).isEmpty(), "Request should be empty when input example request is null");
		assertNotNull(responseBody.get("response"), "Response field should not be null");

		Map<String, Object> responseField = (Map<String, Object>) responseBody.get("response");
		assertEquals("200 OK", responseField.get("status"), "Response status should be '200 OK'");
		assertNotNull(responseField.get("body"), "Response body should not be null");

		Map<String, Object> responseBodyField = (Map<String, Object>) responseField.get("body");
		assertEquals("Example response body", responseBodyField.get("info"), "Response body 'info' field should have a default value");
	}
}