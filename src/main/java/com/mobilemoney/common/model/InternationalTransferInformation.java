package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class InternationalTransferInformation
 */
public class InternationalTransferInformation implements Serializable {
    private static final long serialVersionUID = 6236046853014570747L;

    // Reference for the quotation
    private String quotationReference;

    // Specific quote associated with the quotation
    private String quoteId;

    // Originating country of the transaction
    private String originCountry;

    // Recipient delivery method
    private String deliveryMethod;

    // Destination Country of the international transfer
    private String receivingCountry;

    // Relationship between the sender and the receiver
    private String relationshipSender;

    // Description of the reason for the international remittance
    private String remittancePurpose;

    // Country of the sending service provider
    private String sendingServiceProviderCountry;

    // Reason for blocking the transaction
    private String recipientBlockingReason;
    
    // Reason for blocking the transaction
    private String senderBlockingReason;
    
    /***
     * Constructor with arguments
     *
     * @param originCountry
     */
    public InternationalTransferInformation(final String originCountry) {
        this.originCountry = originCountry;
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
    public String getQuoteId() {
        return quoteId;
    }

    /***
     *
     * @param quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
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
     * @return
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /***
     *
     * @param deliveryMethod
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
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
    public String getRelationshipSender() {
        return relationshipSender;
    }

    /***
     *
     * @param relationshipSender
     */
    public void setRelationshipSender(String relationshipSender) {
        this.relationshipSender = relationshipSender;
    }

    /***
     *
     * @return
     */
    public String getRemittancePurpose() {
        return remittancePurpose;
    }

    /***
     *
     * @param remittancePurpose
     */
    public void setRemittancePurpose(String remittancePurpose) {
        this.remittancePurpose = remittancePurpose;
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
    
}
