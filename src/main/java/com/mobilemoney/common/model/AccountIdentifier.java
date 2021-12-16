package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class AccountIdentifier
 */
public class AccountIdentifier implements Serializable {
    private static final long serialVersionUID = -6178355438770692536L;

    // Provides the account identifier type
    private String key;

    // Provides the account identifier type value
    private String value;

    /***
     * Constructor with arguments
     *
     * @param key
     * @param value
     */
    public AccountIdentifier(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    /***
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /***
     *
     * @return
     */
    public String getValue() {
        return value;
    }
}
