package com.mobilemoney.internationaltransfer.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.TransferRequest;
import com.mobilemoney.merchantpayment.constants.TransactionType;
import com.mobilemoney.internationaltransfer.model.InternationalTransferResponse;

import java.util.UUID;

/***
 * Class TransferRequest
 */
public class InternationalTransferRequest extends TransferRequest {
    // Transaction instance
    private Transaction transaction;

    /***
     * Default constructor
     */
    public InternationalTransferRequest() {
        super(UUID.randomUUID().toString());
    }

    /***
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createInternationalTransaction() throws MobileMoneyException {
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.INT_TRANSFER);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

        return createRequest(HttpMethod.POST, resourcePath, this.transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     *
     * @param transactionReference
     * @return
     * @throws MobileMoneyException
     */
    public InternationalTransferResponse viewInternationalTransaction(final String transactionReference) throws MobileMoneyException {
        String resourcePath = API.RETRIEVE_TRANSACTION.replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, InternationalTransferResponse.class);
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
     * @return
     */
    public String getClientCorrelationId() {
        return clientCorrelationId;
    }

    /***
     *
     * @param callBackURL
     * @return
     */
    public InternationalTransferRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return this;
    }

    /***
     * Add notification type
     *
     * @param notificationType
     * @return
     */
    public InternationalTransferRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }
}
