package com.mobilemoney.common.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.merchantpayment.constants.TransactionType;

/***
 * Class CreateTransactionRequest
 */
public class CreateTransactionRequest extends ResourceUtils {
    // Unique Id
    public String clientCorrelationId;

    // Callback/polling
    private NotificationType notificationType = NotificationType.CALLBACK;

    /***
     * Constructor
     *
     * @param clientCorrelationId
     */
    public CreateTransactionRequest(final String clientCorrelationId) {
        this.clientCorrelationId = clientCorrelationId;
    }

    /***
     * Merchant initiates the payment and will be credited when the payer approves the request
     *
     * @param transaction
     * @param callBackURL
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createMerchantTransaction(final Transaction transaction, final String callBackURL) throws MobileMoneyException {
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.MERCHANT_PAY);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     *
     * @param transaction
     * @param callBackURL
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createTransferTransaction(final Transaction transaction, final String callBackURL) throws MobileMoneyException {
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.TRANSFER);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Perform a merchant payment refund
     *
     * @param transaction
     * @param callBackURL
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createRefundTransaction(final Transaction transaction, final String callBackURL) throws MobileMoneyException {
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.ADJUSTMENT);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }
}
