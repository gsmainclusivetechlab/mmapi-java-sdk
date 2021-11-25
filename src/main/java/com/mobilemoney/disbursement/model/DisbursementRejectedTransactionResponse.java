package com.mobilemoney.disbursement.model;

import com.mobilemoney.common.model.CreditParty;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.DebitParty;

import java.io.Serializable;
import java.util.List;

public class DisbursementRejectedTransactionResponse implements Serializable {
    private static final long serialVersionUID = -6128372005360390291L;

    // Unique reference for the transaction
    private String transactionReference;

    // Reference provided by the requesting organisation
    private String requestingOrganisationTransactionReference;

    // Reason for the transaction rejection
    private String rejectionReason;

    // Date and time of the rejection
    private String rejectionDate;

    // Collection of key/value pair
    private List<CreditParty> creditParty;

    // Collection of key/value pair
    private List<DebitParty> debitParty;

    // Collection of key/value pair
    private List<CustomData> customData;

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
    public String getRequestingOrganisationTransactionReference() {
        return requestingOrganisationTransactionReference;
    }

    /***
     *
     * @param requestingOrganisationTransactionReference
     */
    public void setRequestingOrganisationTransactionReference(String requestingOrganisationTransactionReference) {
        this.requestingOrganisationTransactionReference = requestingOrganisationTransactionReference;
    }

    /***
     *
     * @return
     */
    public String getRejectionReason() {
        return rejectionReason;
    }

    /***
     *
     * @param rejectionReason
     */
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    /***
     *
     * @return
     */
    public String getRejectionDate() {
        return rejectionDate;
    }

    /***
     *
     * @param rejectionDate
     */
    public void setRejectionDate(String rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    /***
     *
     * @return
     */
    public List<CreditParty> getCreditParty() {
        return creditParty;
    }

    /***
     *
     * @param creditParty
     */
    public void setCreditParty(List<CreditParty> creditParty) {
        this.creditParty = creditParty;
    }

    /***
     *
     * @return
     */
    public List<DebitParty> getDebitParty() {
        return debitParty;
    }

    /***
     *
     * @param debitParty
     */
    public void setDebitParty(List<DebitParty> debitParty) {
        this.debitParty = debitParty;
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
