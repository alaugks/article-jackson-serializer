package com.article.jackson.annotation;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.article.jackson.exception.MappingTableRuntimeException;
import com.fasterxml.jackson.databind.BeanProperty;

public class MappingTableMapReader {

	private final BeanProperty property;

	public MappingTableMapReader(BeanProperty property) {
		this.property = property;
	}

	public Map<String, String> getMap() {
		MappingTable annotation = property.getAnnotation(MappingTable.class);

		if (annotation == null) {
			throw new MappingTableRuntimeException("Annotation @MappingTable not set at property");
		}

		return Arrays.stream(annotation.items())
				.collect(
						Collectors.toMap(
								MappingItem::fieldValueId,
								MappingItem::value
						)
				);
	}
}
