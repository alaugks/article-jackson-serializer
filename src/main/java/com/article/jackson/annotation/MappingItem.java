package com.article.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface MappingItem {
	String fieldValueId();
	String value();
}
