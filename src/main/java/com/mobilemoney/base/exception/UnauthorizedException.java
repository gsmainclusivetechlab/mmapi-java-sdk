package com.mobilemoney.base.exception;

public class UnauthorizedException extends BaseException {
    /***
     * Default constructor
     */
    public UnauthorizedException() {
        super();
    }

    /***
     *
     * @param message
     */
    public UnauthorizedException(String message) {
        super(message);
    }

    /***
     *
     * @param message
     * @param exception
     */
    public UnauthorizedException(String message, Throwable exception) {
        super(message, exception);
    }
}
