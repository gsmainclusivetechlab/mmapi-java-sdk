package com.mobilemoney.merchantpayment.request;

import com.mobilemoney.common.model.AuthorisationCodeRequest;
import com.mobilemoney.common.model.AuthorisationCodeResponse;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.AuthorizationCodeRequest;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;

import java.util.UUID;

/***
 * Class PaymentRequest
 */
public class PaymentRequest extends ViewTransactionRequest {
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
    public PaymentRequest() {
        super(UUID.randomUUID().toString());
        this.authorizationCodeRequest = new AuthorizationCodeRequest();
        this.createTransactionRequest = new CreateTransactionRequest(this.clientCorrelationId);
    }

    /***
     * Merchant initiates the payment and will be credited when the payer approves the request
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createMerchantTransaction() throws MobileMoneyException {
        return this.createTransactionRequest.createMerchantTransaction(this.transaction, this.callBackURL);
    }

    /***
     * Perform a merchant payment refund
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createRefundTransaction() throws MobileMoneyException {
        return this.createTransactionRequest.createRefundTransaction(this.transaction, this.callBackURL);
    }

    /***
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAuthorisationCode(Identifiers identifiers) throws MobileMoneyException {
        return this.authorizationCodeRequest.createAuthorisationCode(identifiers, this.clientCorrelationId);
    }

    /***
     *
     * @param identifiers
     * @param authorisationCode
     * @return
     * @throws MobileMoneyException
     */
    public AuthorisationCodeResponse viewAuthorisationCode(Identifiers identifiers, final String authorisationCode) throws MobileMoneyException {
        return this.authorizationCodeRequest.viewAuthorisationCode(identifiers, authorisationCode);
    }

    /***
     * Set call back URL
     *
     * @param callBackURL
     * @return
     */
    public PaymentRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return this;
    }

    /***
     * Set notification type
     *
     * @param notificationType
     * @return
     */
    public PaymentRequest setNotificationType(final NotificationType notificationType) {
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
     * @param authorisationCodeRequest
     */
    public void setAuthorisationCodeRequest(AuthorisationCodeRequest authorisationCodeRequest) {
        this.authorizationCodeRequest.setAuthorisationCodeRequest(authorisationCodeRequest);
    }

    /***
     *
     * @return
     */
    public String getClientCorrelationId() {
        return clientCorrelationId;
    }
}
