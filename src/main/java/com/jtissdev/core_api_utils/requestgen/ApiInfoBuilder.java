package com.jtissdev.core_api_utils.requestgen;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiInfoBuilder {

	/**
	 * Construit des informations de base sur le service avec le nom et la version fournis
	 */
	public static Map<String, Object> buildInfo(String serviceName, String version) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("service", serviceName);
		info.put("version", version);
		info.put("timestamp", Instant.now().toString());
		return info;
	}

	/**
	 * Construit des informations de base sur le service en utilisant les informations du POM
	 */
	public static Map<String, Object> buildInfo(String serviceName, Map<String, Object> pomInfo) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("service", serviceName);
		info.put("version", pomInfo.getOrDefault("version", "unknown"));
		info.put("groupId", pomInfo.getOrDefault("groupId", "unknown"));
		info.put("artifactId", pomInfo.getOrDefault("artifactId", "unknown"));
		info.put("name", pomInfo.getOrDefault("name", "unknown"));
		info.put("timestamp", Instant.now().toString());
		return info;
	}

	/**
	 * Construit des informations complètes sur le service avec tous les détails fournis
	 */
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
	
	/**
	 * Construit des informations complètes sur le service en utilisant les informations du POM
	 */
	public static Map<String, Object> fullInfo(String serviceName, Map<String, Object> pomInfo) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("service", serviceName);
		info.put("version", pomInfo.getOrDefault("version", "unknown"));
		info.put("groupId", pomInfo.getOrDefault("groupId", "unknown"));
		info.put("artifactId", pomInfo.getOrDefault("artifactId", "unknown"));
		info.put("name", pomInfo.getOrDefault("name", "unknown"));
		info.put("description", pomInfo.getOrDefault("description", ""));
		info.put("developers", pomInfo.getOrDefault("developers", ""));
		info.put("owners", pomInfo.getOrDefault("owners", ""));
		info.put("organization", pomInfo.getOrDefault("organization", ""));
		info.put("timestamp", Instant.now().toString());
		return info;
	}
}
