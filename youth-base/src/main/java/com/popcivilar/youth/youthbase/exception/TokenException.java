package com.popcivilar.youth.youthbase.exception;

/**
 * @description Token异常
 * @author
 * @time 2019年07月03日
 */
public class TokenException extends RuntimeException {

    public TokenException(String keyMessage, Object[] args, Throwable cause) {
        super(keyMessage, cause);
    }
    public TokenException(String keyMessage, Object[] args) {
        super(keyMessage);
    }

    public TokenException(String keyMessage, Throwable cause) {
        super(keyMessage, cause);
    }

    public TokenException(String keyMessage) {
        super(keyMessage);
    }
}
