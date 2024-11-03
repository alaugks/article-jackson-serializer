package com.article.jackson.annotation;

import com.article.jackson.exception.MappingRuntimeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class MappingTableMapReader {

    private final BeanProperty property;

    public MappingTableMapReader(BeanProperty property) {
        this.property = property;
    }

    public Map<String, ?> getMap() { // NOSONAR
        try {
            MappingTable annotation = property.getAnnotation(MappingTable.class);

            if (annotation == null) {
                throw new MappingRuntimeException("Annotation @MappingTable not set at property");
            }

            HashMap<String, ?> map = new ObjectMapper().readValue(
                    annotation.map(),
                    new TypeReference<>() {
                    }
            );

            if (!map.isEmpty()) {
                return map;
            }

            throw new MappingRuntimeException("MappingTable not defined");

        } catch (JsonProcessingException e) {
            throw new MappingRuntimeException(e);
        }
    }
}
