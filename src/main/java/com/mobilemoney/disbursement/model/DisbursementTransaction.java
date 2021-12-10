package com.mobilemoney.disbursement.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Transaction;

import java.io.Serializable;
import java.util.List;

public class DisbursementTransaction implements Serializable {
    private static final long serialVersionUID = 817010003683705884L;

    // Status
    private String batchStatus;

    // Title for the batch
    private String batchTitle;

    // Description of the batch
    private String batchDescription;

    // Expected start time
    private String scheduledStartDate;

    // Date and time when the object was modified
    private String modificationDate;

    // The date and time of the request as supplied by the client
    private String requestDate;

    // Originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Collection of Transactions
    private List<Transaction> transactions;

    // Collection of key/value pairs
    private List<CustomData> customData;

    /***
     *
     * @return
     */
    public String getBatchStatus() {
        return batchStatus;
    }

    /***
     *
     * @param batchStatus
     */
    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }

    /***
     *
     * @return
     */
    public String getBatchTitle() {
        return batchTitle;
    }

    /***
     *
     * @param batchTitle
     */
    public void setBatchTitle(String batchTitle) {
        this.batchTitle = batchTitle;
    }

    /***
     *
     * @return
     */
    public String getBatchDescription() {
        return batchDescription;
    }

    /***
     *
     * @param batchdescription
     */
    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public String getScheduledStartDate() {
        return scheduledStartDate;
    }

    /***
     *
     * @param scheduledStartDate
     */
    public void setScheduledStartDate(String scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
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
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /***
     *
     * @param transactions
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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
