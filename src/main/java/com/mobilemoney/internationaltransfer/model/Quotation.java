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

    // Reference for the quotation
    private String quotationReference;
    
    // Creation state of the Quotation
    private String quotationStatus;
    
    // Transaction Type
    private String type;

    // Sub-classification of the type of transaction
    private String subType;

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

    // Country of the sending service provider
    private String sendingServiceProviderCountry;

    // Reason for blocking the quotation
    private String recipientBlockingReason;

    // Reason for blocking the quotation
    private String senderBlockingReason;
    
    // Date and time of the request as supplied by the client
    private String requestDate;
    
    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    // Recipient KYCInformation details
    private KYCInformation recipientKyc;

    // Sender KYCInformation details
    private KYCInformation senderKyc;

    // Details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // Collection of quotes
    private List<Quote> quotes;
    
    // Collection of key/value pairs
    private List<CustomData> customData;

    // Collection of key/value pairs
    private List<MetaData> metadata;

    // Collection of key/value pairs
    private List<AccountIdentifier> creditParty;

    // Collection of key/value pairs
    private List<AccountIdentifier> debitParty;

    /***
     * Constructor with arguments
     *
     * @param requestAmount
     * @param requestCurrency
     * @param creditParty
     * @param accountIdentifier
     */
    public Quotation(final String requestAmount, final String requestCurrency, final List<AccountIdentifier> creditParty, final List<AccountIdentifier> debitParty) {
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
    public List<AccountIdentifier> getCreditParty() {
        return creditParty;
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
