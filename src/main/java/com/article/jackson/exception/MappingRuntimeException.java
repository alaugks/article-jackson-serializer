package com.article.jackson.exception;

public class MappingRuntimeException extends RuntimeException{
    public MappingRuntimeException(String message) {
        super(message);
    }

    public MappingRuntimeException(Throwable cause) {
        super(cause);
    }
}
