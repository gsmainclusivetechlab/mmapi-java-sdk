package com.mobilemoney.recurringpayment.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.RequestingOrganisation;

import java.io.Serializable;
import java.util.List;

/***
 * Class DebitMandate
 */
public class DebitMandate implements Serializable {
    private static final long serialVersionUID = 8422240682718455643L;

    // Status of the Debit Mandate
    private String mandateStatus;

    // Currency of the amount limit
    private String currency;

    // Date on which the mandate starts
    private String startDate;

    // Date on which the mandate ends
    private String endDate;

    // Date and time of the request as supplied by the client
    private String requestDate;

    // Frequency for which payments will be taken from the payers account
    private String frequencyType;

    // Maximum amount that can be taken by the Payee
    private String amountLimit;

    // Number of consecutive payments
    private int numberOfPayments;

    // Details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // A collection of key/value pairs
    private List<Party> payee;

    // A collection of key/value pairs
    private List<CustomData> customData;

    /***
     *
     * @return
     */
    public String getMandateStatus() {
        return mandateStatus;
    }

    /***
     *
     * @param mandateStatus
     */
    public void setMandateStatus(String mandateStatus) {
        this.mandateStatus = mandateStatus;
    }

    /***
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /***
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /***
     *
     * @return
     */
    public String getStartDate() {
        return startDate;
    }

    /***
     *
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /***
     *
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /***
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /***
     *
     * @return
     */
    public String getRequestDate() {
        return requestDate;
    }

    /***
     *
     * @param requestDate
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /***
     *
     * @return
     */
    public String getFrequencyType() {
        return frequencyType;
    }

    /***
     *
     * @param frequencyType
     */
    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    /***
     *
     * @return
     */
    public String getAmountLimit() {
        return amountLimit;
    }

    /***
     *
     * @param amountLimit
     */
    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
    }

    /***
     *
     * @return
     */
    public int getNumberOfPayments() {
        return numberOfPayments;
    }

    /***
     *
     * @param numberOfPayments
     */
    public void setNumberOfPayments(int numberOfPayments) {
        this.numberOfPayments = numberOfPayments;
    }

    /***
     *
     * @return
     */
    public RequestingOrganisation getRequestingOrganisation() {
        return requestingOrganisation;
    }

    /***
     *
     * @param requestingOrganisation
     */
    public void setRequestingOrganisation(RequestingOrganisation requestingOrganisation) {
        this.requestingOrganisation = requestingOrganisation;
    }

    /***
     *
     * @return
     */
    public List<Party> getPayee() {
        return payee;
    }

    /***
     *
     * @param payee
     */
    public void setPayee(List<Party> payee) {
        this.payee = payee;
    }

    /***
     *
     * @return
     */
    public List<CustomData> getCustomData() {
        return customData;
    }

    /***
     *
     * @param customData
     */
    public void setCustomData(List<CustomData> customData) {
        this.customData = customData;
    }

    /***
     * Returns JSON object
     * @return
     */
    public String toJSON() {
        return JSONFormatter.toJSON(this);
    }
}
