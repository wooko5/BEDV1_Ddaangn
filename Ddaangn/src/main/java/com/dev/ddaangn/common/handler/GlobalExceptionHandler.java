package com.dev.ddaangn.common.handler;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.InvalidArgumentException;
import com.dev.ddaangn.common.error.exception.InvalidMediaTypeException;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorMessage> handleInvalidArgumentException(InvalidArgumentException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorMessage> handleInvalidMediaTypeException(InvalidMediaTypeException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    public ApiResponse<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return ApiResponse.fail(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorMessage> handleNotFoundException(NotFoundException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse<String> handleUnauthorized(Exception exception) {
        return ApiResponse.fail(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> internalServerHandler(NotFoundException exception) {
        return ApiResponse.fail(exception.getMessage());
    }
}
