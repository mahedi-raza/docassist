package com.docassist.commons.exception;

public class DocAssistException extends RuntimeException{

    private final int code;

    public DocAssistException(int code, String message) {
        super(message);
        this.code = code;
    }

    public DocAssistException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
