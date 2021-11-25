package com.mobilemoney.common.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.internationaltransfer.model.KYC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Class Transaction
 */
public class Transaction implements Serializable {
    private static final long serialVersionUID = -1261766858176271038L;

    // A reference provided by the requesting organisation that is to be associated with the transaction
    private String requestingOrganisationTransactionReference;

    // For reversals and refunds, this field indicates the transaction which is the subject of the reversal
    private String originalTransactionReference;

    // A non-harmonised sub-classification of the type of transaction
    private String subType;

    // The transaction amount
    private String amount;

    // Currency of the transaction amount
    private String currency;

    // Description of the transaction provided by the client
    private String descriptionText;

    // Indicates the transaction type
    private String type;

    // Indicates the geographic location from where the transaction was initiated
    private String geoCode;

    // A one-time code that can be supplied in the request
    private String oneTimeCode;

    // Identify the servicing identity for transactions
    private String servicingIdentity;

    // The date and time of the request as supplied by the client
    private String requestDate;

    // An object that details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Information specifically used for international transfers
    private InternationalTransferInformation internationalTransferInformation;

    // Recipient KYC details
    private KYC recipientKyc;

    // Sender KYC details
    private KYC senderKyc;

    // A collection of key/value pairs that enable the party to be identified
    private List<DebitParty> debitParty = new ArrayList<>();

    // A collection of key/value pairs that enable the party to be identified
    private List<CreditParty> creditParty = new ArrayList<>();

    // All fees that are applicable to the object
    private List<Fees> fees = new ArrayList<>();

    // This can be used to populate provider specific fields
    private List<CustomData> customData = new ArrayList<>();

    // This can be used to populate additional properties
    private List<MetaData> metadata = new ArrayList<>();

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
    public String getOriginalTransactionReference() {
        return originalTransactionReference;
    }

    /***
     *
     * @param originalTransactionReference
     */
    public void setOriginalTransactionReference(String originalTransactionReference) {
        this.originalTransactionReference = originalTransactionReference;
    }

    /***
     *
     * @return
     */
    public String getSubType() {
        return subType;
    }

    /***
     *
     * @param subType
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }

    /***
     *
     * @return
     */
    public String getAmount() {
        return amount;
    }

    /***
     *
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
    public String getDescriptionText() {
        return descriptionText;
    }

    /***
     *
     * @param descriptionText
     */
    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    /***
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /***
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /***
     *
     * @return
     */
    public String getGeoCode() {
        return geoCode;
    }

    /***
     *
     * @param geoCode
     */
    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }

    /***
     *
     * @return
     */
    public String getOneTimeCode() {
        return oneTimeCode;
    }

    /***
     *
     * @param oneTimeCode
     */
    public void setOneTimeCode(String oneTimeCode) {
        this.oneTimeCode = oneTimeCode;
    }

    /***
     *
     * @return
     */
    public String getServicingIdentity() {
        return servicingIdentity;
    }

    /***
     *
     * @param servicingIdentity
     */
    public void setServicingIdentity(String servicingIdentity) {
        this.servicingIdentity = servicingIdentity;
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
    public InternationalTransferInformation getInternationalTransferInformation() {
        return internationalTransferInformation;
    }

    /***
     *
     * @param internationalTransferInformation
     */
    public void setInternationalTransferInformation(InternationalTransferInformation internationalTransferInformation) {
        this.internationalTransferInformation = internationalTransferInformation;
    }

    /***
     *
     * @return
     */
    public KYC getRecipientKyc() {
        return recipientKyc;
    }

    /***
     *
     * @param recipientKyc
     */
    public void setRecipientKyc(KYC recipientKyc) {
        this.recipientKyc = recipientKyc;
    }

    /***
     *
     * @return
     */
    public KYC getSenderKyc() {
        return senderKyc;
    }

    /***
     *
     * @param senderKyc
     */
    public void setSenderKyc(KYC senderKyc) {
        this.senderKyc = senderKyc;
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
     *
     * @return
     */
    public List<MetaData> getMetadata() {
        return metadata;
    }

    /***
     *
     * @param metadata
     */
    public void setMetadata(List<MetaData> metadata) {
        this.metadata = metadata;
    }

    /***
     * Returns JSON object
     * @return
     */
    public String toJSON() {
        return JSONFormatter.toJSON(this);
    }
}
