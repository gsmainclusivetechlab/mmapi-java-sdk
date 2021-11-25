package com.mobilemoney.common.model;

import java.io.Serializable;

public class PatchData implements Serializable {
    private static final long serialVersionUID = 8229582845419661764L;

    // Indicates the Patch operation to be performed
    private String op;

    // Specify the field to be updated or added
    private String path;

    // Specify the value of the field to be updated or added
    private String value;

    /***
     * Constructor with arguments
     *
     * @param op
     * @param path
     * @param value
     */
    public PatchData(final String op, final String path, final String value) {
        this.op = op;
        this.path = path;
        this.value =value;
    }

    /***
     *
     * @return
     */
    public String getOp() {
        return op;
    }

    /***
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /***
     *
     * @return
     */
    public String getValue() {
        return value;
    }
}
