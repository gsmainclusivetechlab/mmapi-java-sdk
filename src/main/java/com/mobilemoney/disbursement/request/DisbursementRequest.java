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
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.ViewTransactionRequest;
import com.mobilemoney.disbursement.model.DisbursementCompletedTransactionResponse;
import com.mobilemoney.disbursement.model.DisbursementRejectedTransactionResponse;
import com.mobilemoney.disbursement.model.DisbursementTransaction;
import com.mobilemoney.disbursement.model.DisbursementTransactionResponse;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.List;
import java.util.UUID;

public class DisbursementRequest extends ViewTransactionRequest {
    // DisbursementTransaction reference
    private DisbursementTransaction disbursementTransaction;

    // MerchantTransaction reference
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

        if (this.disbursementTransaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.DISBURSEMENT_TRANSACTION_OBJECT_INIT_ERROR).build());
        }

        String resourcePath = API.CREATE_BATCH_TRANSACTIONS;
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

        this.disbursementTransaction.getTransactions().forEach(transaction -> transaction.setType(TransactionType.TRANSFER));

        return createRequest(HttpMethod.POST, resourcePath, this.disbursementTransaction.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Returns current status of a batch transaction
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public DisbursementTransactionResponse viewBatchTransaction(final String batchId) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.VIEW_BATCH_TRANSACTION.replace(Constants.BATCH_ID, batchId);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, DisbursementTransactionResponse.class);
    }

    /***
     * Returns completed transactions for a specific batch
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public List<DisbursementCompletedTransactionResponse> viewBatchCompletions(final String batchId) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        List<DisbursementCompletedTransactionResponse> completedTransactions = null;
        String resourcePath = API.VIEW_BATCH_TRANSACTION_COMPLETED.replace(Constants.BATCH_ID, batchId);
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath);

        if (requestResponse.getPayLoad() instanceof String) {
            completedTransactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), DisbursementCompletedTransactionResponse.class);
        }
        return completedTransactions;
    }

    /***
     * Returns rejected transactions for a specific batch
     *
     * @param batchId
     * @return
     * @throws MobileMoneyException
     */
    public List<DisbursementRejectedTransactionResponse> viewBatchRejections(final String batchId) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(batchId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        List<DisbursementRejectedTransactionResponse> completedTransactions = null;
        String resourcePath = API.VIEW_BATCH_TRANSACTION_REJECTED.replace(Constants.BATCH_ID, batchId);
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath);

        if (requestResponse.getPayLoad() instanceof String) {
            completedTransactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), DisbursementRejectedTransactionResponse.class);
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
     * @param disbursementTransaction
     */
    public void setDisbursementTransaction(DisbursementTransaction disbursementTransaction) {
        this.disbursementTransaction = disbursementTransaction;
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
}
