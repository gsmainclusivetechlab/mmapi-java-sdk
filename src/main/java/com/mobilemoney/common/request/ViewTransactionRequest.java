package com.mobilemoney.common.request;

import com.mobilemoney.common.model.AccountBalance;
import com.mobilemoney.base.HttpResponse;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.constants.HttpStatusCode;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.merchantpayment.constants.TransactionType;
import com.mobilemoney.merchantpayment.model.TransactionResponse;

import java.util.List;

public class ViewTransactionRequest extends CommonRequest {

    /***
     * Default constructor
     */
    public ViewTransactionRequest(final String clientCorrelationId) { this.clientCorrelationId = clientCorrelationId; }

    /***
     * Returns account balance of a specific account
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AccountBalance viewAccountBalance(Identifiers identifiers) throws MobileMoneyException {
        return createRequest(HttpMethod.GET, getResourcePath(API.ACCOUNT_BALANCE_REQUEST, identifiers), AccountBalance.class);
    }

    /***
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public List<TransactionResponse> viewAccountTransactions(Identifiers identifiers) throws MobileMoneyException {
        return viewAccountTransactions(identifiers, null);
    }

    /***
     *
     * @param identifiers
     * @param filter
     * @return
     * @throws MobileMoneyException
     */
    public List<TransactionResponse> viewAccountTransactions(Identifiers identifiers, final TransactionFilter filter) throws MobileMoneyException {
        List<TransactionResponse> transactions = null;
        StringBuilder resourcePath = new StringBuilder(getResourcePath(API.ACCOUNT_TRANSACTIONS, identifiers));

        if (filter != null) {
            applyTransactionFilter(resourcePath, filter);
        }

        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

        if (requestResponse.getPayLoad() instanceof String) {
            if (requestResponse.getResponseCode().equals(HttpStatusCode.OK)) {
                transactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), TransactionResponse.class);
            }
        }
        return transactions;
    }

    /***
     * Returns transaction details of a given transaction reference
     *
     * @return
     * @throws MobileMoneyException
     */
    public TransactionResponse viewTransaction(final String transactionReference) throws MobileMoneyException {
        String resourcePath = API.RETRIEVE_TRANSACTION.replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, TransactionResponse.class);
    }

    /***
     *
     * @param transactionReference
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createReversal(final String transactionReference) throws MobileMoneyException {
        String resourcePath = API.CREATE_TRANSACTION_REVERSAL.replace(Constants.TRANSACTION_TYPE, "reversals").replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, new Type(TransactionType.REVERSAL).toJSON(), notificationType, callBackURL, AsyncResponse.class);
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
     * @param resourcePath
     * @param filter
     * @return
     */
    private StringBuilder applyTransactionFilter(StringBuilder resourcePath, TransactionFilter filter) {
        resourcePath.append("?");

        if (filter.getLimit() >= 0) resourcePath.append("limit=").append(filter.getLimit()).append("&");
        if (filter.getOffset() >= 0) resourcePath.append("offset=").append(filter.getOffset()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getFromDateTime())) resourcePath.append("fromDateTime=").append(filter.getFromDateTime()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getToDateTime())) resourcePath.append("toDateTime=").append(filter.getToDateTime()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getTransactionStatus())) resourcePath.append("transactionStatus=").append(filter.getTransactionStatus()).append("&");
        if (!StringUtils.isNullOrEmpty(filter.getTransactionType())) resourcePath.append("transactionType=").append(filter.getTransactionType()).append("&");

        return resourcePath;
    }

    /***
     * Type: Utility class
     *
     */
    private static class Type {
        // Transaction type
        private String type;

        /***
         * Constructor with single parameter
         *
         * @param type
         */
        public Type(String type) {
            this.type = type;
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
}
