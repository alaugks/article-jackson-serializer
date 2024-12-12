package com.article.jackson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

import com.article.jackson.annotation.MappingTableMapReader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.type.SimpleType;

public class MappingValueDeserializer extends JsonDeserializer<MappingValue<?>> implements ContextualDeserializer {

	private final String[] supportedTypes = {"String", "Boolean"};

	private final Map<String, ?> map;

	private final Type type;

	public MappingValueDeserializer() {
		this(null, null);
	}

	public MappingValueDeserializer(Map<String, ?> map, Type type) {
		this.map = map;
		this.type = type;
	}

	@Override
	public MappingValue<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		String fieldValue = jsonParser.getText();
		String simpleNameType = ((SimpleType) this.type).getBindings().getTypeParameters().get(0).getRawClass().getSimpleName();

		if (Arrays.stream(supportedTypes).noneMatch(simpleNameType::equalsIgnoreCase)) {
			throw new IOException(String.format("Type \"%s\" not supported", simpleNameType));
		}

		return new MappingValue<>(this.map.entrySet().stream()
				.filter(e -> e.getKey().equals(fieldValue))
				.map(Map.Entry::getValue)
				.findFirst()
				.orElse(null));
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
		return new MappingValueDeserializer(
				new MappingTableMapReader(property).getMap(),
				property.getType()
		);
	}
}
