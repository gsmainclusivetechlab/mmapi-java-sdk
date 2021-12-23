package com.mobilemoney.merchantpayment.request;

import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.AuthorizationCodeRequest;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;

import java.util.UUID;

/***
 * Class MerchantPaymentRequest
 */
public class MerchantPaymentRequest extends ViewTransactionRequest {
	// Transaction Reference
	private Transaction transaction;
	
    // AuthorizationCodeRequest Reference
    private AuthorizationCodeRequest authorizationCodeRequest;

    // CreateTransactionRequest Reference
    private CreateTransactionRequest createTransactionRequest;

    /***
     * Default constructor
     *
     */
    public MerchantPaymentRequest() {
        this.authorizationCodeRequest = new AuthorizationCodeRequest();
        this.createTransactionRequest = new CreateTransactionRequest();
    }

    /***
     * Merchant initiates the payment and will be credited when the payer approves the request
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createMerchantTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createMerchantTransaction(this.transaction, this.callBackURL, this.notificationType, this.clientCorrelationId);
    }

    /***
     * Perform a merchant payment refund
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createRefundTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createRefundTransaction(this.transaction, this.callBackURL, this.notificationType, this.clientCorrelationId);
    }

    /***
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAuthorisationCode(Identifiers identifiers) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        return this.authorizationCodeRequest.createAuthorisationCode(identifiers, this.callBackURL, this.clientCorrelationId);
    }

    /***
     *
     * @param identifiers
     * @param authorisationCode
     * @return
     * @throws MobileMoneyException
     */
    public AuthorisationCode viewAuthorisationCode(Identifiers identifiers, final String authorisationCode) throws MobileMoneyException {
        return this.authorizationCodeRequest.viewAuthorisationCode(identifiers, authorisationCode);
    }

    /***
     * Set call back URL
     *
     * @param callBackURL
     * @return
     */
    public MerchantPaymentRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return setNotificationType(NotificationType.CALLBACK);
    }

    /***
     * Set notification type
     *
     * @param notificationType
     * @return
     */
    public MerchantPaymentRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    /***
     *
     * @param transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /***
     * 
     * @param reversal
     */
    public void setReversal(Reversal reversal) {
    	this.reversal = reversal;
    }
    
    /***
     *
     * @param authorisationCode
     */
    public void setAuthorisationCodeRequest(AuthorisationCode authorisationCode) {
        this.authorizationCodeRequest.setAuthorisationCodeRequest(authorisationCode);
    }

    /***
     *
     * @return
     */
    public String getClientCorrelationId() {
        return this.clientCorrelationId;
    }
}
