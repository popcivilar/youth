package com.popcivilar.youth.youthbase.exception;

/**
 * 框架类异常
 */
public class FrameException extends RuntimeException {

    public FrameException(String keyMessage, Object[] args, Throwable cause) {
        super(keyMessage, cause);
    }
    public FrameException(String keyMessage, Object[] args) {
        super(keyMessage);
    }

    public FrameException(String keyMessage, Throwable cause) {
        super(keyMessage, cause);
    }

    public FrameException(String keyMessage) {
        super(keyMessage);
    }
}
