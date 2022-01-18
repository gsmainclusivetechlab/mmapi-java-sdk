package com.mobilemoney.base.constants;

public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive ");

    private final String status;

    /**
     * @param status
     */
    Status(final String status) {
        this.status = status;
    }

    /* 
     * @return
     */
    public String getStatus() {
        return status;
    }
}
