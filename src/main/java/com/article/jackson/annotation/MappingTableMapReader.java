package com.article.jackson.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class MappingTableMapReader {

    private final BeanProperty property;

    public MappingTableMapReader(BeanProperty property) {
        this.property = property;
    }

    public HashMap<String, ?> getMap() {
        try {
            MappingTable annotation = property.getAnnotation(MappingTable.class);

            if (annotation == null) {
                throw new RuntimeException("Annotation @MappingTable not set at property");
            }

            HashMap<String, ?> map = new ObjectMapper().readValue(
                    annotation.map(),
                    new TypeReference<>() {
                    }
            );

            if (map.size() > 0) {
                return map;
            }

            throw new RuntimeException("MappingTable not defined");

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
