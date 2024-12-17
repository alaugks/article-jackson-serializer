package com.article.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

public class MarketingInformationMap implements MapInterface {
	@Override
	public Map<String, Object> getMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("1", true);
		map.put("2", false);
		return map;
	}
}
