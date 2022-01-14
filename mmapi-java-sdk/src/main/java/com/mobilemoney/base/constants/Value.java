package com.mobilemoney.base.constants;

public enum Value {
    VERIFIED("verified"),
    APPROVED("approved");

    private final String value;

    /**
     * @param value
     */
    Value(final String value) {
        this.value = value;
    }

    /* 
     * @return
     */
    public String getValue() {
        return value;
    }
}
