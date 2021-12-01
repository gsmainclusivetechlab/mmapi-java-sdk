package com.mobilemoney.recurringpayment.model;

import java.io.Serializable;

/***
 * Class DebitMandateResponse
 */
public class DebitMandateResponse implements Serializable {
    private static final long serialVersionUID = -747208361254946085L;

    // Unique reference
    private String mandateReference;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    /***
     *
     * @return
     */
    public String getMandateReference() {
        return mandateReference;
    }

    /***
     *
     * @param mandateReference
     */
    public void setMandateReference(String mandateReference) {
        this.mandateReference = mandateReference;
    }

    /***
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /***
     *
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /***
     *
     * @return
     */
    public String getModificationDate() {
        return modificationDate;
    }

    /***
     *
     * @param modificationDate
     */
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }
}
