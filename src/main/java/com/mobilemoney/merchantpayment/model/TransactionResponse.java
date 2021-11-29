package com.mobilemoney.merchantpayment.model;

import com.mobilemoney.common.model.Transaction;

import java.io.Serializable;

/***
 * Class TransactionResponse
 */
public class TransactionResponse extends Transaction implements Serializable {
    private static final long serialVersionUID = 4715987598276011562L;

    // Unique reference for the transaction
    private String transactionReference;

    // Status of the transaction
    private String transactionStatus;

    // Transaction receipt number
    private String transactionReceipt;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    /***
     *
     * @return
     */
    public String getTransactionReference() {
        return transactionReference;
    }

    /***
     *
     * @param transactionReference
     */
    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    /***
     *
     * @return
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /***
     *
     * @param transactionStatus
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    /***
     *
     * @return
     */
    public String getTransactionReceipt() {
        return transactionReceipt;
    }

    /***
     *
     * @param transactionReceipt
     */
    public void setTransactionReceipt(String transactionReceipt) {
        this.transactionReceipt = transactionReceipt;
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
