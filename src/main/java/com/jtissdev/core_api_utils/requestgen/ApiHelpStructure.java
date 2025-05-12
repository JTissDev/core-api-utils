package com.jtissdev.core_api_utils.requestgen;

import java.util.Map;

public class ApiHelpStructure {

	public static Map<String, Object> standardHeaders() {
		return Map.of(
				"X-Help-Request", true,
				"X-Request-Type", "HELP",
				"Accept", "application/json"
		);
	}

	public static Map<String, Object> standardResponseBody(String endpoint, String description, Map<String, Object> exampleRequest) {
		return Map.of(
				"endpoint", endpoint,
				"description", description,
				"headers", standardHeaders(),
				"request", exampleRequest,
				"response", Map.of(
						"status", "200 OK",
						"body", Map.of("info", "Example response body")
				)
		);
	}

	public static Map<String, Object> basicGet(String endpoint, String description) {
		return standardResponseBody(endpoint, description, Map.of("method", "GET"));
	}

	public static Map<String, Object> basicPost(String endpoint, String description) {
		return standardResponseBody(endpoint, description, Map.of("method", "POST"));
	}

	public static Map<String, Object> basicPut(String endpoint, String description) {
		return standardResponseBody(endpoint, description, Map.of("method", "PUT"));
	}

	public static Map<String, Object> basicDelete(String endpoint, String description) {
		return standardResponseBody(endpoint, description, Map.of("method", "DELETE"));
	}
}

