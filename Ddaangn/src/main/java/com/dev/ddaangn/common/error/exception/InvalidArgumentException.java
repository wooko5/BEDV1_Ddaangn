package com.dev.ddaangn.common.error.exception;

import com.dev.ddaangn.common.error.ErrorMessage;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(ErrorMessage message) {
        super(message.message());
    }
}
