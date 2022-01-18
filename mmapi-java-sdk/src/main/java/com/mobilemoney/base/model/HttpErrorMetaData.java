package com.mobilemoney.base.model;

/***
 * Class HttpErrorMetaData
 */
public class HttpErrorMetaData {
    // Identifies the type of additional field
    private String key;

    // Identifies the value of the additional field
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
