package com.article.jackson.serializer;

import java.io.IOException;
import java.util.Map;

import com.article.jackson.annotation.MappingTableMapReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

public class MappingValueDeserializer extends JsonDeserializer<MappingValue<?>> implements ContextualDeserializer {

	private final Map<String, Object> map;

	public MappingValueDeserializer() {
		this(null);
	}

	public MappingValueDeserializer(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public MappingValue<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String fieldValue = jsonParser.getText();

		return new MappingValue<>(this.map.entrySet().stream()
				.filter(e -> e.getKey().equals(fieldValue))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElse(null));
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
		return new MappingValueDeserializer(
				new MappingTableMapReader(property).getMap()
		);
	}
}
