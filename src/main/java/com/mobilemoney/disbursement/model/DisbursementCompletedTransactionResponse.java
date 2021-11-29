package com.mobilemoney.disbursement.model;

import com.mobilemoney.common.model.CreditParty;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.DebitParty;

import java.io.Serializable;
import java.util.List;

public class DisbursementCompletedTransactionResponse implements Serializable {
    private static final long serialVersionUID = -8385778188635063959L;

    // Unique reference for the transaction
    private String transactionReference;

    // Reference provided by the requesting organisation
    private String requestingOrganisationTransactionReference;

    // Date and time indicating when the transaction was completed
    private String completionDate;

    // URL to the resource
    private String link;

    // Collection of key/value pairs
    private List<CreditParty> creditParty;

    // Collection of key/value pairs
    private List<DebitParty> debitParty;

    // Collection of key/value pairs
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
    public String getLink() {
        return link;
    }

    /***
     *
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
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
