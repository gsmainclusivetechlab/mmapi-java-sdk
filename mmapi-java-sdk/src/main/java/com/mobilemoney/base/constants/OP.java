package com.mobilemoney.base.constants;

public enum OP {
    REPLACE("replace"),
    ADD("add");

    private final String op;

    /**
     * @param op
     */
    OP(final String op) {
        this.op = op;
    }

    /* 
     * @return
     */
    public String getOP() {
        return op;
    }
}
