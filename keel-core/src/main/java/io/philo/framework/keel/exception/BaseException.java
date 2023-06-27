package io.philo.framework.keel.exception;

/**
 * Base Exception is the parent of all exceptions
 *
 * @author fulan.zjf 2017年10月22日 上午12:00:39
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public BaseException(String errMessage) {
        super(errMessage);
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

}
