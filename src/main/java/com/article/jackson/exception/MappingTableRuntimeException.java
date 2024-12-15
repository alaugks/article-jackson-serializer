package com.article.jackson.exception;

public class MappingTableRuntimeException extends RuntimeException {
	public MappingTableRuntimeException(String message) {
		super(message);
	}

	public MappingTableRuntimeException(Throwable cause) {
		super(cause);
	}
}
