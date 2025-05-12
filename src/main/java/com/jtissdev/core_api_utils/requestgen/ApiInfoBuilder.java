package com.jtissdev.core_api_utils.requestgen;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiInfoBuilder {

	public static Map<String, Object> buildInfo(String serviceName, String version) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("service", serviceName);
		info.put("version", version);
		info.put("timestamp", Instant.now().toString());
		return info;
	}

	public static Map<String, Object> fullInfo(String serviceName, String version, String description, String developerEmail, String ownerEmail) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("service", serviceName);
		info.put("version", version);
		info.put("description", description);
		info.put("developerContact", developerEmail);
		info.put("projectOwnerContact", ownerEmail);
		info.put("timestamp", Instant.now().toString());
		return info;
	}
}
