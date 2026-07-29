package com.article.jackson.serializer;

import java.util.Map;

import com.article.jackson.annotation.MappingTableMapReader;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

public class MappingValueDeserializer extends ValueDeserializer<MappingValue<?>> {

	private final Map<String, Object> map;

	public MappingValueDeserializer() {
		this(null);
	}

	public MappingValueDeserializer(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public MappingValue<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
		String fieldValue = jsonParser.getString();

		return new MappingValue<>(this.map.entrySet().stream()
				.filter(e -> e.getKey().equals(fieldValue))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElse(null));
	}

	@Override
	public ValueDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
		return new MappingValueDeserializer(
				new MappingTableMapReader(property).getMap()
		);
	}
}
