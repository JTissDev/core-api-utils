package com.jtissdev.core_api_utils.requestgen;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class PomInfoReader {

	public static Map<String, Object> readPomInfo() {
		Properties props = new Properties();
		try (InputStream in = PomInfoReader.class.getResourceAsStream("/META-INF/maven/project.properties")) {
			if (in != null) {
				props.load(in);
			}
		} catch (IOException e) {
			return Map.of("error", "Failed to read pom.properties: " + e.getMessage());
		}

		Map<String, Object> info = new LinkedHashMap<>();
		info.put("groupId", props.getProperty("groupId", "unknown"));
		info.put("artifactId", props.getProperty("artifactId", "unknown"));
		info.put("version", props.getProperty("version", "unknown"));
		info.put("name", props.getProperty("name", "unknown"));
		info.put("description", props.getProperty("description", ""));
		info.put("developers", props.getProperty("developers", ""));
		info.put("owners", props.getProperty("owners", ""));
		info.put("organization", props.getProperty("organization", ""));
		info.put("timestamp", java.time.Instant.now().toString());
		return info;
	}
}

