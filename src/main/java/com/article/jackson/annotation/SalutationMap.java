package com.article.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

public class SalutationMap implements MapInterface {
	@Override
	public Map<String, Object> getMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("1", "MALE");
		map.put("2", "FEMALE");
		map.put("6", "DIVERS");
		return map;
	}
}
