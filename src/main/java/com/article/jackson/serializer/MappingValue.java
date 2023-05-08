package com.article.jackson.serializer;

public class MappingValue<T> {
    T value;

    public MappingValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
