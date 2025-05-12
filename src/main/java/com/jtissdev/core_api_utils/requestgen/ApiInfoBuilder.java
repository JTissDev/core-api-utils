package com.jtissdev.core_api_utils.requestgen;

import java.time.Instant;
import java.util.Map;

public class ApiInfoBuilder {

	public static Map<String, Object> buildInfo(String serviceName, String version) {
		return Map.of(
				"service", serviceName,
				"version", version,
				"timestamp", Instant.now().toString()
		);
	}
}
