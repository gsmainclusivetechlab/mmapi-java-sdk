package com.mobilemoney.common.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.internationaltransfer.model.KYCInformation;

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
    
    // Unique reference for the transaction
    private String transactionReference;

    // Status of the transaction
    private String transactionStatus;

    // Transaction receipt number
    private String transactionReceipt;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    // An object that details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Information specifically used for international transfers
    private InternationalTransferInformation internationalTransferInformation;

    // Recipient KYCInformation details
    private KYCInformation recipientKyc;

    // Sender KYCInformation details
    private KYCInformation senderKyc;

    // A collection of key/value pairs that enable the party to be identified
    private List<AccountIdentifier> debitParty;

    // A collection of key/value pairs that enable the party to be identified
    private List<AccountIdentifier> creditParty;

    // All fees that are applicable to the object
    private List<Fees> fees;

    // This can be used to populate provider specific fields
    private List<CustomData> customData;

    // This can be used to populate additional properties
    private List<MetaData> metadata;
    
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
   public String getTransactionStatus() {
       return transactionStatus;
   }

   /***
    *
    * @param transactionStatus
    */
   public void setTransactionStatus(String transactionStatus) {
       this.transactionStatus = transactionStatus;
   }

   /***
    *
    * @return
    */
   public String getTransactionReceipt() {
       return transactionReceipt;
   }

   /***
    *
    * @param transactionReceipt
    */
   public void setTransactionReceipt(String transactionReceipt) {
       this.transactionReceipt = transactionReceipt;
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
    public KYCInformation getRecipientKyc() {
        return recipientKyc;
    }

    /***
     *
     * @param recipientKyc
     */
    public void setRecipientKyc(KYCInformation recipientKyc) {
        this.recipientKyc = recipientKyc;
    }

    /***
     *
     * @return
     */
    public KYCInformation getSenderKyc() {
        return senderKyc;
    }

    /***
     *
     * @param senderKyc
     */
    public void setSenderKyc(KYCInformation senderKyc) {
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
    public List<AccountIdentifier> getDebitParty() {
        return debitParty;
    }

    /***
     *
     * @param accountIdentifier
     */
    public void setDebitParty(List<AccountIdentifier> debitParty) {
        this.debitParty = debitParty;
    }

    /***
     *
     * @return
     */
    public List<AccountIdentifier> getCreditParty() {
        return creditParty;
    }

    /***
     *
     * @param creditParty
     */
    public void setCreditParty(List<AccountIdentifier> creditParty) {
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
