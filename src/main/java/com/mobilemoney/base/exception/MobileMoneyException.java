package com.mobilemoney.base.exception;

import com.mobilemoney.base.model.HttpErrorResponse;

/***
 * Class MobileMoneyException
 */
public class MobileMoneyException extends Exception {
private HttpErrorResponse error;
    /***
     * Default constructor
     *
     */
    public MobileMoneyException() {
        super();
    }

    /***
     * Constructing exception with detailed message
     *
     * @param message
     */
    public MobileMoneyException(String message) {
        super(message);
    }

    /***
     * Constructing exception with detailed message and cause
     *
     * @param message
     * @param cause
     */
    public MobileMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    /***
     *
     * @param error
     */
    public MobileMoneyException(HttpErrorResponse error) {
        this.error=error;
    }

    /***
     *
     * @return
     */
    public HttpErrorResponse getError() {
        return error;
    }
}
