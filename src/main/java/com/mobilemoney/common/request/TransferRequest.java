package com.mobilemoney.common.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.internationaltransfer.model.QuotationResponse;
import com.mobilemoney.merchantpayment.constants.TransactionType;

/***
 * Class InternationalTransferRequest
 */
public class TransferRequest extends ViewTransactionRequest {
    // Quotation instance
    protected Quotation quotation;

    /***
     * Default constructor
     * @param clientCorrelationId
     */
    public TransferRequest(final String clientCorrelationId) {
        super(clientCorrelationId);
    }

    /***
     * Request an international transfer quotation
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createQuotation() throws MobileMoneyException {
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        this.quotation.setType(TransactionType.INT_TRANSFER);

        return createRequest(HttpMethod.POST, API.QUOTATION_REQUEST, this.quotation.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }

    /***
     * Returns a quotation
     *
     * @param quotationReference
     * @return
     * @throws MobileMoneyException
     */
    public QuotationResponse viewQuotation(final String quotationReference) throws MobileMoneyException {
        String resourcePath = API.VIEW_QUOTATION.replace(Constants.QUOTATION_REFERENCE, quotationReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, QuotationResponse.class);
    }

    /***
     * Set quotation object
     *
     * @param quotation
     */
    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }
}
