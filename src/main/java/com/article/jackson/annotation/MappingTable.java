package com.article.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.article.jackson.dto.Maps;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MappingTable {
	Maps map();
}
