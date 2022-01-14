package com.mobilemoney.internationaltransfer.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.TransferRequest;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.UUID;

/***
 * Class InternationalTransferRequest
 */
public class InternationalTransferRequest extends TransferRequest {
    // Transaction instance
    private Transaction transaction;

    /***
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createInternationalTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }

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
    public Transaction viewInternationalTransaction(final String transactionReference) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(transactionReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.RETRIEVE_TRANSACTION.replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, Transaction.class);
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
	 * @param transactionJsonString
	 */
	public void setTransaction(final String transactionJsonString) {
		this.transaction = JSONFormatter.fromJSON(transactionJsonString, Transaction.class);
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
     * @param reversalJsonString
     */
    public void setReversal(final String reversalJsonString) {
    	this.reversal = JSONFormatter.fromJSON(reversalJsonString, Reversal.class);
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
        return setNotificationType(NotificationType.CALLBACK);
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
