package com.mobilemoney.internationaltransfer.model;

import com.mobilemoney.common.model.*;

import java.io.Serializable;
import java.util.List;

/***
 * Class QuotationResponse
 */
public class QuotationResponse implements Serializable {
    private static final long serialVersionUID = 3732405938927914762L;

    // Reference for the quotation
    private String quotationReference;

    // Transaction Type
    private String type;

    // Sub-classification of the type of transaction
    private String subType;

    // Creation state of the Quotation
    private String quotationStatus;

    // Requested quotation amount
    private String requestAmount;

    // Currency of the requested quotation amount
    private String requestCurrency;

    // Delivery Method that is possible for the intended recipient
    private String availableDeliveryMethod;

    // Delivery method chosen by the sending end user
    private String chosenDeliveryMethod;

    // Originating country of the quotation request
    private String originCountry;

    // Destination country of the quotation request
    private String receivingCountry;

    // Reason for blocking the quotation
    private String recipientBlockingReason;

    // Reason for blocking the quotation
    private String senderBlockingReason;

    // Country of the sending service provider
    private String sendingServiceProviderCountry;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    // Date and time of the request as supplied by the client
    private String requestDate;

    // Details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Recipient KYC details
    private KYC recipientKyc;

    // Sender KYC details
    private KYC senderKyc;

    // Collection of quotes
    private List<Quote> quotes;

    // Collection of key/value pair
    private List<CreditParty> creditParty;

    // Collection of key/value pair
    private List<DebitParty> debitParty;

    // Collection of key/value pair
    private List<CustomData> customData;

    // Collection of key/value pair
    private List<MetaData> metadata;

    /***
     *
     * @return
     */
    public String getQuotationReference() {
        return quotationReference;
    }

    /***
     *
     * @param quotationReference
     */
    public void setQuotationReference(String quotationReference) {
        this.quotationReference = quotationReference;
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
    public String getQuotationStatus() {
        return quotationStatus;
    }

    /***
     *
     * @param quotationStatus
     */
    public void setQuotationStatus(String quotationStatus) {
        this.quotationStatus = quotationStatus;
    }

    /***
     *
     * @return
     */
    public String getRequestAmount() {
        return requestAmount;
    }

    /***
     *
     * @param requestAmount
     */
    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    /***
     *
     * @return
     */
    public String getRequestCurrency() {
        return requestCurrency;
    }

    /***
     *
     * @param requestCurrency
     */
    public void setRequestCurrency(String requestCurrency) {
        this.requestCurrency = requestCurrency;
    }

    /***
     *
     * @return
     */
    public String getAvailableDeliveryMethod() {
        return availableDeliveryMethod;
    }

    /***
     *
     * @param availableDeliveryMethod
     */
    public void setAvailableDeliveryMethod(String availableDeliveryMethod) {
        this.availableDeliveryMethod = availableDeliveryMethod;
    }

    /***
     *
     * @return
     */
    public String getChosenDeliveryMethod() {
        return chosenDeliveryMethod;
    }

    /***
     *
     * @param chosenDeliveryMethod
     */
    public void setChosenDeliveryMethod(String chosenDeliveryMethod) {
        this.chosenDeliveryMethod = chosenDeliveryMethod;
    }

    /***
     *
     * @return
     */
    public String getOriginCountry() {
        return originCountry;
    }

    /***
     *
     * @param originCountry
     */
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    /***
     *
     * @return
     */
    public String getReceivingCountry() {
        return receivingCountry;
    }

    /***
     *
     * @param receivingCountry
     */
    public void setReceivingCountry(String receivingCountry) {
        this.receivingCountry = receivingCountry;
    }

    /***
     *
     * @return
     */
    public String getRecipientBlockingReason() {
        return recipientBlockingReason;
    }

    /***
     *
     * @param recipientBlockingReason
     */
    public void setRecipientBlockingReason(String recipientBlockingReason) {
        this.recipientBlockingReason = recipientBlockingReason;
    }

    /***
     *
     * @return
     */
    public String getSenderBlockingReason() {
        return senderBlockingReason;
    }

    /***
     *
     * @param senderBlockingReason
     */
    public void setSenderBlockingReason(String senderBlockingReason) {
        this.senderBlockingReason = senderBlockingReason;
    }

    /***
     *
     * @return
     */
    public String getSendingServiceProviderCountry() {
        return sendingServiceProviderCountry;
    }

    /***
     *
     * @param sendingServiceProviderCountry
     */
    public void setSendingServiceProviderCountry(String sendingServiceProviderCountry) {
        this.sendingServiceProviderCountry = sendingServiceProviderCountry;
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
    public List<Quote> getQuotes() {
        return quotes;
    }

    /***
     *
     * @param quotes
     */
    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
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
}
