package com.mobilemoney.base.constants;

public enum Mode {
    PUSH("push"),
    PULL("pull"),
    BOTH("both");

    private final String mode;

    /**
     * @param mode
     */
    Mode(final String mode) {
        this.mode = mode;
    }

    /* 
     * @return
     */
    public String getMode() {
        return mode;
    }
}
