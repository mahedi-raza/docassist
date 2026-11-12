package com.docassist.commons.exception;

import com.docassist.commons.constants.ResponseCode;

public class ValidationException extends DocAssistException {

    public ValidationException(String message) {
        super(ResponseCode.REQUEST_VALIDATION_FAILED, message);
    }

    public ValidationException(String message, Throwable cause) {
        super(ResponseCode.REQUEST_VALIDATION_FAILED, message, cause);
    }
}
