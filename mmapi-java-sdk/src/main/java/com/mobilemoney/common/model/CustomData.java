package com.mobilemoney.common.model;

import java.io.Serializable;

public class CustomData implements Serializable {
    private static final long serialVersionUID = 8199254511016196900L;

    // Identifies the type of additional field
    private String key;

    // Identifies the value of the additional field
    private String value;

    /***
     * Constructor with arguments
     *
     * @param key
     * @param value
     */
    public CustomData(final String key, final String value) {
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
