package com.mobilemoney.internationaltransfer.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.*;

import java.io.Serializable;
import java.util.List;

/***
 * Class Quotation
 */
public class Quotation implements Serializable {
    private static final long serialVersionUID = -7376752766319193673L;

    // Transaction Type
    private String type;

    // Sub-classification of the type of transaction
    private String subType;

    // Requested quotation amount
    private String requestAmount;

    // Currency of the requested quotation amount
    private String requestCurrency;

    // Delivery method chosen by the sending end user
    private String chosenDeliveryMethod;

    // Originating country of the quotation request
    private String originCountry;

    // Destination country of the quotation request
    private String receivingCountry;

    // Country of the sending service provider
    private String sendingServiceProviderCountry;

    // Date and time of the request as supplied by the client
    private String requestDate;

    // Recipient KYC details
    private KYC recipientKyc;

    // Sender KYC details
    private KYC senderKyc;

    // Details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Collection of key/value pairs
    private List<CustomData> customData;

    // Collection of key/value pairs
    private List<MetaData> metadata;

    // Collection of key/value pairs
    private List<CreditParty> creditParty;

    // Collection of key/value pairs
    private List<DebitParty> debitParty;

    /***
     * Constructor with arguments
     *
     * @param requestAmount
     * @param requestCurrency
     * @param creditParty
     * @param debitParty
     */
    public Quotation(final String requestAmount, final String requestCurrency, final List<CreditParty> creditParty, final List<DebitParty> debitParty) {
        this.requestAmount = requestAmount;
        this.requestCurrency = requestCurrency;
        this.creditParty = creditParty;
        this.debitParty = debitParty;
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
    public String getRequestAmount() {
        return requestAmount;
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
    public List<CreditParty> getCreditParty() {
        return creditParty;
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
