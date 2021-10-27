package com.dev.ddaangn.common.error.exception;

import com.dev.ddaangn.common.error.ErrorMessage;
import org.springframework.web.HttpMediaTypeException;

public class InvalidMediaTypeException extends HttpMediaTypeException {
    public InvalidMediaTypeException(ErrorMessage message) {
        super(message.message());
    }
}
