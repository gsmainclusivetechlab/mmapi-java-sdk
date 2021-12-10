package com.mobilemoney.disbursement.model;

import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.RequestingOrganisation;

import java.io.Serializable;
import java.util.List;

public class DisbursementTransactionResponse implements Serializable {
    private static final long serialVersionUID = -7320177389253554669L;

    // Identifier for the batch
    private String batchId;

    // Status of the batch
    private String batchStatus;

    // Indicates when the batch was approved
    private String approvalDate;

    // Indicates when the batch was completed
    private String completionDate;

    // Title for the batch
    private String batchTitle;

    // Description of the batch
    private String batchDescription;

    // Indicates whether the batch is currently undergoing processing by the API Provider
    private String processingFlag;

    // Expected start time
    private String scheduledStartDate;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    // Date and time of the request as supplied by the client
    private String requestDate;

    // Number of records that have been successful completed
    private int completedCount;

    // Number of records that have been rejected
    private int rejectionCount;

    // Number of records that have been parsed successfully
    private int parsingSuccessCount;

    // An object that details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Collection of key/value pairs
    private List<CustomData> customData;

    /***
     *
     * @return
     */
    public String getBatchId() {
        return batchId;
    }

    /***
     *
     * @param batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

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
    public String getApprovalDate() {
        return approvalDate;
    }

    /***
     *
     * @param approvalDate
     */
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    /***
     *
     * @return
     */
    public String getCompletionDate() {
        return completionDate;
    }

    /***
     *
     * @param completionDate
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
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

    /***
     *
     * @return
     */
    public String getProcessingFlag() {
        return processingFlag;
    }

    /***
     *
     * @param processingFlag
     */
    public void setProcessingFlag(String processingFlag) {
        this.processingFlag = processingFlag;
    }

    /***
     *
     * @return
     */
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
    public int getCompletedCount() {
        return completedCount;
    }

    /***
     *
     * @param completedCount
     */
    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    /***
     *
     * @return
     */
    public int getRejectionCount() {
        return rejectionCount;
    }

    /***
     *
     * @param rejectionCount
     */
    public void setRejectionCount(int rejectionCount) {
        this.rejectionCount = rejectionCount;
    }

    /***
     *
     * @return
     */
    public int getParsingSuccessCount() {
        return parsingSuccessCount;
    }

    /***
     *
     * @param parsingSuccessCount
     */
    public void setParsingSuccessCount(int parsingSuccessCount) {
        this.parsingSuccessCount = parsingSuccessCount;
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
}
