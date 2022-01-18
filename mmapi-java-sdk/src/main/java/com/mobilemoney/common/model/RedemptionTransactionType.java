package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class RedemptionTransactionType
 */
public class RedemptionTransactionType implements Serializable {
    private static final long serialVersionUID = -3625578685345507791L;

    // Harmonised Transaction Type
    private String transactionType;

    // Non-harmonised sub-classification transaction type
    private String transactionSubtype;

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

    /***
     *
     * @return
     */
    public String getTransactionSubtype() {
        return transactionSubtype;
    }

    /***
     *
     * @param transactionSubtype
     */
    public void setTransactionSubtype(String transactionSubtype) {
        this.transactionSubtype = transactionSubtype;
    }
}
