package com.mobilemoney.common.model;

import com.mobilemoney.base.util.JSONFormatter;

import java.io.Serializable;
import java.util.List;

/***
 * Class AuthorisationCode
 */
public class AuthorisationCode implements Serializable {
	private static final long serialVersionUID = 2991054219375511874L;

	// Amount associated with the authorisation code
	private String amount;

	// Amount currency
	private String currency;

	// Amount type (exact amount/maximum amount)
	private String amountType;

	// Date and time of the request
	private String requestDate;

	// The code that will be presented to the other party for redemption
	private String authorisationCode;

	// State of the authorisation code
	private String codeState;

	// Date and time when the object was created
	private String creationDate;

	// Date and time when the object was modified
	private String modificationDate;

	// Expiry time in seconds
	private int codeLifetime;

	// Indicates whether funds should be reserved against the payers account where
	// the payer is the requester
	private boolean holdFundsIndicator;

	// An object that details the originating organisation of the request
	private RequestingOrganisation requestingOrganisation;

	// A collection of key/value pairs that enable the redemption account to be
	// identified
	private List<RedemptionAccountIdentifier> redemptionAccountIdentifiers;

	// Indicates the channel(s) that the code can be redeemed against
	private List<RedemptionChannel> redemptionChannels;

	// Indicates the Transaction Types(s) that the code can be redeemed against
	private List<RedemptionTransactionType> redemptionTransactionTypes;

	// A collection of key/value pairs
	private List<CustomData> customData;

	// A collection of key/value pairs
	private List<MetaData> metadata;

	/***
	 * Default constructor
	 * 
	 */
	public AuthorisationCode() {
		this.codeLifetime = 1;
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
	public String getAmountType() {
		return amountType;
	}

	/***
	 *
	 * @param amountType
	 */
	public void setAmountType(String amountType) {
		this.amountType = amountType;
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
	public String getAuthorisationCode() {
		return authorisationCode;
	}

	/***
	 *
	 * @param authorisationCode
	 */
	public void setAuthorisationCode(String authorisationCode) {
		this.authorisationCode = authorisationCode;
	}

	/***
	 *
	 * @return
	 */
	public String getCodeState() {
		return codeState;
	}

	/***
	 *
	 * @param codeState
	 */
	public void setCodeState(String codeState) {
		this.codeState = codeState;
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
	public int getCodeLifetime() {
		return codeLifetime;
	}

	/***
	 *
	 * @param codeLifetime
	 */
	public void setCodeLifetime(int codeLifetime) {
		this.codeLifetime = codeLifetime;
	}

	/***
	 *
	 * @return
	 */
	public boolean isHoldFundsIndicator() {
		return holdFundsIndicator;
	}

	/***
	 *
	 * @param holdFundsIndicator
	 */
	public void setHoldFundsIndicator(boolean holdFundsIndicator) {
		this.holdFundsIndicator = holdFundsIndicator;
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
	public List<RedemptionAccountIdentifier> getRedemptionAccountIdentifiers() {
		return redemptionAccountIdentifiers;
	}

	/***
	 *
	 * @param redemptionAccountIdentifiers
	 */
	public void setRedemptionAccountIdentifiers(List<RedemptionAccountIdentifier> redemptionAccountIdentifiers) {
		this.redemptionAccountIdentifiers = redemptionAccountIdentifiers;
	}

	/***
	 *
	 * @return
	 */
	public List<RedemptionChannel> getRedemptionChannels() {
		return redemptionChannels;
	}

	/***
	 *
	 * @param redemptionChannels
	 */
	public void setRedemptionChannels(List<RedemptionChannel> redemptionChannels) {
		this.redemptionChannels = redemptionChannels;
	}

	/***
	 *
	 * @return
	 */
	public List<RedemptionTransactionType> getRedemptionTransactionTypes() {
		return redemptionTransactionTypes;
	}

	/***
	 *
	 * @param redemptionTransactionTypes
	 */
	public void setRedemptionTransactionTypes(List<RedemptionTransactionType> redemptionTransactionTypes) {
		this.redemptionTransactionTypes = redemptionTransactionTypes;
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
	 * 
	 * @return
	 */
	public String toJSON() {
		return JSONFormatter.toJSON(this);
	}
}
