package com.mobilemoney.common.request;

import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.common.model.Balance;
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
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.List;
import java.util.UUID;

/***
 * Class ViewTransactionRequest
 */
public class ViewTransactionRequest extends CommonRequest {
	// Reversal Reference
	public Reversal reversal;
	
    /***
     * Returns account balance of a specific account
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public Balance viewAccountBalance(Identifiers identifiers) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        return createRequest(HttpMethod.GET, getResourcePath(API.ACCOUNT_BALANCE_REQUEST, identifiers), Balance.class);
    }

    /***
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public List<Transaction> viewAccountTransactions(Identifiers identifiers) throws MobileMoneyException {
        return viewAccountTransactions(identifiers, null);
    }

    /***
     *
     * @param identifiers
     * @param filter
     * @return
     * @throws MobileMoneyException
     */
    public List<Transaction> viewAccountTransactions(Identifiers identifiers, final TransactionFilter filter) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }

        List<Transaction> transactions = null;
        StringBuilder resourcePath = new StringBuilder(getResourcePath(API.ACCOUNT_TRANSACTIONS, identifiers));

        if (filter != null) {
            applyTransactionFilter(resourcePath, filter);
        }

        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

        if (requestResponse.getPayLoad() instanceof String) {
            if (requestResponse.getResponseCode().equals(HttpStatusCode.OK)) {
                transactions = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), Transaction.class);
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
    public Transaction viewTransaction(final String transactionReference) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(transactionReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.RETRIEVE_TRANSACTION.replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, Transaction.class);
    }

    /***
     *
     * @param transactionReference
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createReversal(final String transactionReference) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (this.reversal == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }
        
        if (StringUtils.isNullOrEmpty(transactionReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        this.reversal.setType(TransactionType.REVERSAL);
        
        String resourcePath = API.CREATE_TRANSACTION_REVERSAL.replace(Constants.TRANSACTION_TYPE, "reversals").replace(Constants.TRANSACTION_REFERENCE, transactionReference);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, this.reversal.toJSON(), notificationType, callBackURL, AsyncResponse.class);
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
}
