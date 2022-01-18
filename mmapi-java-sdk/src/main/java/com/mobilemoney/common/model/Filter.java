package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class Filter
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = 3566940569130641892L;

    // Minimum creationDate for which records should be returned
    private String fromDateTime;

    // Maximum creationDate for which records should be returned
    private String toDateTime;

    // Supports pagination
    private int limit;

    // Supports pagination
    private int offset;

    /***
     *
     * @return
     */
    public String getFromDateTime() {
        return fromDateTime;
    }

    /***
     *
     * @param fromDateTime
     */
    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    /***
     *
     * @return
     */
    public String getToDateTime() {
        return toDateTime;
    }

    /***
     *
     * @param toDateTime
     */
    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    /***
     *
     * @return
     */
    public int getLimit() {
        return limit;
    }

    /***
     *
     * @param limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /***
     *
     * @return
     */
    public int getOffset() {
        return offset;
    }

    /***
     *
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
}
