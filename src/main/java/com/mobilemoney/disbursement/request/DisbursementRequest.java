package com.mobilemoney.disbursement.request;

import com.mobilemoney.base.HttpResponse;
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
import com.mobilemoney.common.model.Filter;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.ViewTransactionRequest;
import com.mobilemoney.disbursement.model.BatchCompletion;
import com.mobilemoney.disbursement.model.BatchRejection;
import com.mobilemoney.disbursement.model.BatchTransaction;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.List;
import java.util.UUID;

/***
 * 
 * Class DisbursementRequest
 *
 */
public class DisbursementRequest extends ViewTransactionRequest {
    // BatchTransaction reference
    private BatchTransaction batchTransaction;

    // Transaction reference
    private Transaction transaction;

    // PatchData reference
    private List<PatchData> patchData;

    /***
     * Initiates Individual Disbursement
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createDisbursementTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (this.transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.DISBURSEMENT);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, this.transaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Initiates bulk disbursement
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createBatchTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (this.batchTransaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.DISBURSEMENT_TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.CREATE_BATCH_TRANSACTIONS;
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

        this.batchTransaction.getTransactions().forEach(transaction -> transaction.setType(TransactionType.TRANSFER));

        return createRequest(HttpMethod.POST, resourcePath, this.batchTransaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Returns current status of a batch transaction
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public BatchTransaction viewBatchTransaction(final String batchId) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.VIEW_BATCH_TRANSACTION.replace(Constants.BATCH_ID, batchId);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, BatchTransaction.class);
    }

    /***
     * Returns completed transactions for a specific batch without filter
     * 
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public List<BatchCompletion> viewBatchCompletions(final String batchId) throws MobileMoneyException {
    	return this.viewBatchCompletions(batchId, null);
    }
    
    /***
     * Returns completed transactions for a specific batch with filter
     *
     * @param batchId
     * @param filter
     * @return
     * @throws MobileMoneyException
     */
    public List<BatchCompletion> viewBatchCompletions(final String batchId, final Filter filter) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        List<BatchCompletion> completedTransactions = null;
        StringBuilder resourcePath = new StringBuilder(API.VIEW_BATCH_TRANSACTION_COMPLETED.replace(Constants.BATCH_ID, batchId));
        
        if (filter != null) {
        	applyFilter(resourcePath, filter);
        }
        
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

        if (requestResponse.getPayLoad() instanceof String) {
            completedTransactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), BatchCompletion.class);
        }
        return completedTransactions;
    }

    /***
     * Returns rejected transactions for a specific batch without filter
     * 
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public List<BatchRejection> viewBatchRejections(final String batchId) throws MobileMoneyException {
    	return this.viewBatchRejections(batchId, null);
    }
    
    /***
     * Returns rejected transactions for a specific batch with filter
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public List<BatchRejection> viewBatchRejections(final String batchId, final Filter filter) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        List<BatchRejection> completedTransactions = null;
        StringBuilder resourcePath = new StringBuilder(API.VIEW_BATCH_TRANSACTION_REJECTED.replace(Constants.BATCH_ID, batchId));
        
        if (filter != null) {
        	applyFilter(resourcePath, filter);
        }
        
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

        if (requestResponse.getPayLoad() instanceof String) {
            completedTransactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), BatchRejection.class);
        }
        return completedTransactions;
    }

    /***
     * Updates a specific transaction batch
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse updateBatchTransaction(final String batchId) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.UPDATE_BATCH_TRANSACTION.replace(Constants.BATCH_ID, batchId);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.PATCH, resourcePath, JSONFormatter.toJSONArray(this.patchData), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Set call back URL
     *
     * @param callBackURL
     * @return
     */
    public DisbursementRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return this;
    }

    /***
     * Add notification type
     *
     * @param notificationType
     * @return
     */
    public DisbursementRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
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
     * @param batchTransaction
     */
    public void setBatchTransaction(BatchTransaction batchTransaction) {
        this.batchTransaction = batchTransaction;
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
     * @return
     */
    public List<PatchData> getPatchData() {
        return patchData;
    }

    /***
     *
     * @param patchData
     */
    public void setPatchData(List<PatchData> patchData) {
        this.patchData = patchData;
    }

    /***
     * 
     * @param resourcePath
     * @param filter
     * @return
     */
    private StringBuilder applyFilter(StringBuilder resourcePath, Filter filter) {
    	if (filter.getLimit() >= 0) resourcePath.append("limit=").append(filter.getLimit()).append("&");
        if (filter.getOffset() >= 0) resourcePath.append("offset=").append(filter.getOffset()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getFromDateTime())) resourcePath.append("fromDateTime=").append(filter.getFromDateTime()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getToDateTime())) resourcePath.append("toDateTime=").append(filter.getToDateTime()).append("&");
        
        return resourcePath;
    }
}
