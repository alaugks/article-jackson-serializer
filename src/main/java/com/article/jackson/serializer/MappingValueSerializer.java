package com.article.jackson.serializer;

import java.io.IOException;
import java.util.Map;

import com.article.jackson.annotation.MappingTableMapReader;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class MappingValueSerializer extends JsonSerializer<MappingValue<?>> implements ContextualSerializer {

	private final Map<String, Object> map;

	public MappingValueSerializer() {
		this(null);
	}

	public MappingValueSerializer(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public void serialize(MappingValue<?> field, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		String fieldValueId = this.map.entrySet().stream()
				.filter(e -> e.getValue().equals(field.getValue()))
				.map(Map.Entry::getKey)
				.findFirst()
				.orElse(null);

		jsonGenerator.writeString(fieldValueId);
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
		return new MappingValueSerializer(
				new MappingTableMapReader(property).getMap()
		);
	}
}
