package com.mobilemoney.base.exception;

public class BaseException extends Exception {
    /***
     * Default constructor
     */
    public BaseException() { }

    /**
     *
     * @param msg
     */
    public BaseException(String msg) {
        super(msg);
    }

    /***
     *
     * @param msg
     * @param exception
     */
    public BaseException(String msg, Throwable exception) {
        super(msg, exception);
    }
}
