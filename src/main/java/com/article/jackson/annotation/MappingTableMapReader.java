package com.article.jackson.annotation;

import java.util.Map;

import com.article.jackson.exception.MappingTableRuntimeException;
import com.fasterxml.jackson.databind.BeanProperty;

public class MappingTableMapReader {

	private final BeanProperty property;

	public MappingTableMapReader(BeanProperty property) {
		this.property = property;
	}

	public Map<String, Object> getMap() {

		MappingTable annotation = property.getAnnotation(MappingTable.class);

		if (annotation == null) {
			throw new MappingTableRuntimeException(
					String.format(
							"Annotation @MappingTable not set at property %s",
							this.property.getMember().getFullName()
					)
			);
		}

		return annotation.map().getMap();
	}
}
