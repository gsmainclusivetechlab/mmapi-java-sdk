package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class RedemptionAccountIdentifier
 */
public class RedemptionAccountIdentifier implements Serializable {
    private static final long serialVersionUID = 8570153204235309266L;

    // Account identifier type
    private String key;

    // Account identifier type value
    private String value;

    /***
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /***
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /***
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /***
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
