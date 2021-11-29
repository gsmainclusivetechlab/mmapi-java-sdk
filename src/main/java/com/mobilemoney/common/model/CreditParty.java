package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class CreditParty
 */
public class CreditParty implements Serializable {
    private static final long serialVersionUID = -2851776281080790724L;

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
    public CreditParty(final String key, final String value) {
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
