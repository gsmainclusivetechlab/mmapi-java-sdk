package com.mobilemoney.common.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.merchantpayment.constants.TransactionType;

/***
 * Class CreateTransactionRequest
 */
public class CreateTransactionRequest extends ResourceUtils {
    // Callback/polling
    private NotificationType notificationType = NotificationType.CALLBACK;

    /***
     * Merchant initiates the payment and will be credited when the payer approves the request
     *
     * @param transaction
     * @param callBackURL
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createMerchantTransaction(final Transaction transaction, final String callBackURL, final String clientCorrelationId) throws MobileMoneyException {
        if (transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.MERCHANT_PAY);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     *
     * @param transaction
     * @param callBackURL
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createTransferTransaction(final Transaction transaction, final String callBackURL, final String clientCorrelationId) throws MobileMoneyException {
        if (transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.TRANSFER);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Perform a merchant payment refund
     *
     * @param transaction
     * @param callBackURL
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createRefundTransaction(final Transaction transaction, final String callBackURL, final String clientCorrelationId) throws MobileMoneyException {
        if (transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.ADJUSTMENT);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }
}
