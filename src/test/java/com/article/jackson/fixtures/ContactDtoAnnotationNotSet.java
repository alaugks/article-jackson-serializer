package com.article.jackson.fixtures;

import com.article.jackson.serializer.MappingValue;
import com.article.jackson.serializer.MappingValueDeserializer;
import com.article.jackson.serializer.MappingValueSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class ContactDtoAnnotationNotSet {
	@JsonSerialize(using = MappingValueSerializer.class)
	@JsonDeserialize(using = MappingValueDeserializer.class)
	private MappingValue<Integer> property;
}
