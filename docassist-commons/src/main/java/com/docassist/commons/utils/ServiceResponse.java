package com.docassist.commons.utils;

public class ServiceResponse<T> {

    private int code;
    private boolean success;
    private String message;
    private T data;

    public ServiceResponse(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
