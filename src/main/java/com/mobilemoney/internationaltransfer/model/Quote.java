package com.mobilemoney.internationaltransfer.model;

import com.mobilemoney.common.model.Fees;

import java.io.Serializable;
import java.util.List;

/***
 * Class Quote
 */
public class Quote implements Serializable {
    private static final long serialVersionUID = 1807505053847910863L;

    // Specific quote associated with the quotation
    private String quoteId;

    // Total amount as it will be received by the receiving end-user
    private String receivingAmount;

    // Currency of the quote
    private String receivingCurrency;

    // Requested quotation amount
    private String sendingAmount;

    // Currency of the requested quotation amount
    private String sendingCurrency;

    // Delivery method that is applicable to the quotation
    private String deliveryMethod;

    // Conversion rate
    private String fxRate;

    // Timestamp when the quote will expire
    private String quoteExpiryTime;

    // Name of the receiving service provider
    private String receivingServiceProvider;

    // Returns all fees that are applicable to the object
    private List<Fees> fees;

    /***
     *
     * @return
     */
    public String getQuoteId() {
        return quoteId;
    }

    /***
     *
     * @param quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /***
     *
     * @return
     */
    public String getReceivingAmount() {
        return receivingAmount;
    }

    /***
     *
     * @param receivingAmount
     */
    public void setReceivingAmount(String receivingAmount) {
        this.receivingAmount = receivingAmount;
    }

    /***
     *
     * @return
     */
    public String getReceivingCurrency() {
        return receivingCurrency;
    }

    /***
     *
     * @param receivingCurrency
     */
    public void setReceivingCurrency(String receivingCurrency) {
        this.receivingCurrency = receivingCurrency;
    }

    /***
     *
     * @return
     */
    public String getSendingAmount() {
        return sendingAmount;
    }

    /***
     *
     * @param sendingAmount
     */
    public void setSendingAmount(String sendingAmount) {
        this.sendingAmount = sendingAmount;
    }

    /***
     *
     * @return
     */
    public String getSendingCurrency() {
        return sendingCurrency;
    }

    /***
     *
     * @param sendingCurrency
     */
    public void setSendingCurrency(String sendingCurrency) {
        this.sendingCurrency = sendingCurrency;
    }

    /***
     *
     * @return
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /***
     *
     * @param deliveryMethod
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    /***
     *
     * @return
     */
    public String getFxRate() {
        return fxRate;
    }

    /***
     *
     * @param fxRate
     */
    public void setFxRate(String fxRate) {
        this.fxRate = fxRate;
    }

    /***
     *
     * @return
     */
    public String getQuoteExpiryTime() {
        return quoteExpiryTime;
    }

    /***
     *
     * @param quoteExpiryTime
     */
    public void setQuoteExpiryTime(String quoteExpiryTime) {
        this.quoteExpiryTime = quoteExpiryTime;
    }

    /***
     *
     * @return
     */
    public String getReceivingServiceProvider() {
        return receivingServiceProvider;
    }

    /***
     *
     * @param receivingServiceProvider
     */
    public void setReceivingServiceProvider(String receivingServiceProvider) {
        this.receivingServiceProvider = receivingServiceProvider;
    }

    /***
     *
     * @return
     */
    public List<Fees> getFees() {
        return fees;
    }

    /***
     *
     * @param fees
     */
    public void setFees(List<Fees> fees) {
        this.fees = fees;
    }
}
