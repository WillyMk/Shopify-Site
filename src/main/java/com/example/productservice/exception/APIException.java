package com.example.productservice.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public class APIException extends RuntimeException{
    private final ApiError apiError;
    public APIException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }

    public static APIException notFound(String message, final Object... args) {
        return new APIException(
                new ApiError(HttpStatus.NOT_FOUND, MessageFormat.format(message, args))
        );
    }

    public static APIException alreadyExists(String message, final Object... args) {
        return new APIException(
                new ApiError(HttpStatus.CONFLICT, MessageFormat.format(message, args))
        );
    }

    public static APIException nullPointer(String message, final Object... args) {
        return new APIException(
                new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, MessageFormat.format(message, args))
        );
    }

    @Override
    public String toString() {
        return "APIException{"
                + "apiError=" + apiError
                + '}';
    }
}
