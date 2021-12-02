package com.mobilemoney.recurringpayment.model;

import java.io.Serializable;

/***
 * Class Party
 */
public class Party implements Serializable {
    private static final long serialVersionUID = -9035400426874700L;

    // Identifies the type
    private String key;

    // Identifies the value
    private String value;

    /***
     * Constructor with arguments
     *
     * @param key
     * @param value
     */
    public Party(final String key, final String value) {
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
