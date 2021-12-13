package com.mobilemoney.recurringpayment.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;
import com.mobilemoney.recurringpayment.model.DebitMandate;

import java.util.UUID;

/***
 * Class RecurringPaymentRequest
 */
public class RecurringPaymentRequest extends ViewTransactionRequest {
    // DebitMandate Reference
    private DebitMandate debitMandate;

    // Transaction Reference
    private Transaction transaction;

    // CreateTransactionRequest Reference
    private CreateTransactionRequest createTransactionRequest;

    /***
     * Default constructor
     *
     */
    public RecurringPaymentRequest() {
        this.createTransactionRequest = new CreateTransactionRequest();
    }

    /***
     * Merchant initiates the payment and will be credited when the payer approves the request
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createMerchantTransaction() throws MobileMoneyException {
        clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createMerchantTransaction(this.transaction, this.callBackURL, clientCorrelationId);
    }

    /***
     * Setup recurring payments
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAccountDebitMandate(Identifiers identifiers) throws MobileMoneyException {
        clientCorrelationId = UUID.randomUUID().toString();

        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        if (debitMandate == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.DEBIT_MANDATE_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = getResourcePath(API.CREATE_DEBIT_MANDATES, identifiers);

        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, debitMandate.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Returns debit mandate details of a given recurring payment transaction reference
     *
     * @param identifiers
     * @param debitMandateReference
     * @return
     * @throws MobileMoneyException
     */
    public DebitMandate viewAccountDebitMandate(Identifiers identifiers, final String debitMandateReference) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        if (StringUtils.isNullOrEmpty(debitMandateReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = getResourcePath(API.VIEW_DEBIT_MANDATES, identifiers).replace(Constants.DEBIT_MANDATE_REFERENCE, debitMandateReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, DebitMandate.class);
    }

    /***
     * Perform a recurring payment refund
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createRefundTransaction() throws MobileMoneyException {
        clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createRefundTransaction(this.transaction, this.callBackURL, clientCorrelationId);
    }

    /***
     * Set call back URL
     *
     * @param callBackURL
     * @return
     */
    public RecurringPaymentRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return this;
    }

    /***
     * Set notification type
     *
     * @param notificationType
     * @return
     */
    public RecurringPaymentRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    /***
     *
     * @param debitMandate
     */
    public void setDebitMandate(DebitMandate debitMandate) {
        this.debitMandate = debitMandate;
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
}
