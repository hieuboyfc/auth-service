package com.zimji.auth.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessException extends RuntimeException {

    HttpStatus status;
    String code;
    String message;
    Object[] args;

    public BusinessException() {
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String code, String message, Object... args) {
        super(message);
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public BusinessException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code.toString();
        this.message = message;
    }

    public BusinessException(String code, String message, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = message;
    }

}
