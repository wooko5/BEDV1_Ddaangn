package com.dev.ddaangn.common.handler;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.InvalidArgumentException;
import com.dev.ddaangn.common.error.exception.InvalidMediaTypeException;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<ErrorMessage> invalidArgumentHandler(InvalidArgumentException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorMessage> invalidMediaTypeHandler(InvalidMediaTypeException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorMessage> notFoundHandler(NotFoundException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> internalServerHandler(NotFoundException exception) {
        return ApiResponse.fail(exception.getMessage());
    }
}
