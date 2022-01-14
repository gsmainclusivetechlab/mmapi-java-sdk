package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class TransactionFilter
 */
public class TransactionFilter extends Filter implements Serializable {
    private static final long serialVersionUID = 4121333625358975252L;

    // Status of the transactions to be returned
    private String transactionStatus;

    // Type of the transactions to be returned
    private String transactionType;

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
    public String getTransactionType() {
        return transactionType;
    }

    /***
     *
     * @param transactionType
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
