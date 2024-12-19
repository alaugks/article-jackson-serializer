package com.article.jackson.annotation;

import java.util.Map;

import com.article.jackson.exception.MappingTableRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MappingTableMapReader {

	private final BeanProperty property;

	public MappingTableMapReader(BeanProperty property) {
		this.property = property;
	}

	public Map<String, Object> getMap() {
		try {
			MappingTable annotation = property.getAnnotation(MappingTable.class);

			if (annotation == null) {
				throw new MappingTableRuntimeException(
						String.format(
								"Annotation @MappingTable not set at property %s",
								this.property.getMember().getFullName()
						)
				);
			}

			return new ObjectMapper().readValue(
					annotation.map(),
					new TypeReference<>() {
					}
			);
		}
		catch (JsonProcessingException e) {
			throw new MappingTableRuntimeException("Error JSON processing: " + e.getMessage());
		}
	}
}
