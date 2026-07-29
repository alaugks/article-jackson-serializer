package com.article.jackson.serializer;

import java.util.Map;

import com.article.jackson.annotation.MappingTableMapReader;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.BeanProperty;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class MappingValueSerializer extends ValueSerializer<MappingValue<?>> {

	private final Map<String, Object> map;

	public MappingValueSerializer() {
		this(null);
	}

	public MappingValueSerializer(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public void serialize(MappingValue<?> field, JsonGenerator jsonGenerator, SerializationContext serializationContext) {
		String fieldValueId = this.map.entrySet().stream()
				.filter(e -> e.getValue().equals(field.getValue()))
				.map(Map.Entry::getKey)
				.findFirst()
				.orElse(null);

		jsonGenerator.writeString(fieldValueId);
	}

	@Override
	public ValueSerializer<?> createContextual(SerializationContext prov, BeanProperty property) {
		return new MappingValueSerializer(
				new MappingTableMapReader(property).getMap()
		);
	}
}
