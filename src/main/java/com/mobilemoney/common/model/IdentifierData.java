package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class IdentifierData
 */
public class IdentifierData implements Serializable {
    private static final long serialVersionUID = 5380654924611159236L;

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
    public IdentifierData(final String key, final String value) {
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
