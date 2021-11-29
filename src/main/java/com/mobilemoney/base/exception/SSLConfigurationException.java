package com.mobilemoney.base.exception;

public class SSLConfigurationException extends BaseException {
    /***
     *
     * @param message
     */
    public SSLConfigurationException(String message) {
        super(message);
    }

    /***
     *
     * @param message
     * @param exception
     */
    public SSLConfigurationException(String message, Throwable exception) {
        super(message, exception);
    }
}
