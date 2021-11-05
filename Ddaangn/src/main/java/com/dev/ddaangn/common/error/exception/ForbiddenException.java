package com.dev.ddaangn.common.error.exception;

import com.dev.ddaangn.common.error.ErrorMessage;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(ErrorMessage message) {
        super(message.message());
    }
}
