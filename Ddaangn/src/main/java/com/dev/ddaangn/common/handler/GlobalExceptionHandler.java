package com.dev.ddaangn.common.handler;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.InvalidMediaTypeException;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ApiResponse<String> handleInternalServerErrorException(Exception exception) {
        return ApiResponse.fail(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiResponse<String> handleMethodArgumentNotValidException(Exception exception) {
        return ApiResponse.fail(exception.getMessage());
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
}
