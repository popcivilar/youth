package com.popcivilar.youth.youthbase.exception;

/**
 * @description 业务异常
 * @author
 * @time 2019年07月03日
 */
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
