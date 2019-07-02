package com.popcivilar.youth.youthbase.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String keyMessage, Object[] args, Throwable cause) {
        super(keyMessage, cause);
    }
    public BusinessException(String keyMessage, Object[] args) {
        super(keyMessage);
    }

    public BusinessException(String keyMessage, Throwable cause) {
        super(keyMessage, cause);
    }

    public BusinessException(String keyMessage) {
        super(keyMessage);
    }
}
