package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class Fees
 */
public class Fees implements Serializable {
    private static final long serialVersionUID = -6331658374662105499L;

    // Defines the type of fee
    private String feeType;

    // Defines the amount of the fee
    private String feeAmount;

    // Defines the currency for the given fee
    private String feeCurrency;

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(String feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }
}
