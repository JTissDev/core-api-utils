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
				"request", exampleRequest
		);
	}
}
