package com.article.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

import com.article.jackson.exception.MappingRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingTableMapReader {

	private final BeanProperty property;

	public MappingTableMapReader(BeanProperty property) {
		this.property = property;
	}

	public Map<String, ?> getMap() {
		try {
			MappingTable annotation = property.getAnnotation(MappingTable.class);

			if (annotation == null) {
				throw new MappingRuntimeException("Annotation @MappingTable not set at property");
			}

			return new ObjectMapper().readValue(
					annotation.map(),
					new TypeReference<>() {
					}
			);
		}
		catch (JsonProcessingException e) {
			throw new MappingRuntimeException("Error JSON processing: " + e.getMessage());
		}
	}
}
