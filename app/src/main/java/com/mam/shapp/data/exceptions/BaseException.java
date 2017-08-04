package com.mam.shapp.data.exceptions;

public class BaseException extends RuntimeException {

    private String code;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        super(code);
        this.code = code;
        this.message = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public String getMessageException() {
        return message;
    }
}
