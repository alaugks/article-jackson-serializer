package com.article.jackson.fixtures;

import com.article.jackson.annotation.MappingTable;
import com.article.jackson.serializer.MappingValue;
import com.article.jackson.serializer.MappingValueDeserializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
public class ContactDtoTypeNotSupported {
    @MappingTable(map = "{\"1\": \"bar\"}")
    @JsonDeserialize(using = MappingValueDeserializer.class)
    @JsonProperty("1")
    private MappingValue<Integer> property;

    public int getProperty() {
        return property.getValue();
    }

    public void setProperty(int property) {
        this.property = new MappingValue<>(property);
    }
}
