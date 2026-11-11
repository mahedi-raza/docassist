package com.docassist.commons.exception;

import com.docassist.commons.enums.ErrorCode;

public class DocAssistException extends RuntimeException{

    private final ErrorCode errorCode;

    public DocAssistException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
