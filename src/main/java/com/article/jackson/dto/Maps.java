package com.article.jackson.dto;

import java.util.Map;

public enum Maps {

	SALUTATION(Map.of("1", "MALE", "2", "FEMALE", "6", "DIVERS")),

	MARKETING_INFORMATION(Map.of("1", true, "2", false));

	private final Map<String, Object> map;

	Maps(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}
}
