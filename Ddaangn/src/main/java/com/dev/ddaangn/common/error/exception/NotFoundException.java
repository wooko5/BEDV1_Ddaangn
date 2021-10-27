package com.dev.ddaangn.common.error.exception;

import com.dev.ddaangn.common.error.ErrorMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(ErrorMessage message) {
        super(message.message());
    }
}
