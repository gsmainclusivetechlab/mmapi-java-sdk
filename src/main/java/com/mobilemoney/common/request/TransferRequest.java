package com.mobilemoney.common.request;

import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.UUID;

/***
 * Class InternationalTransferRequest
 */
public class TransferRequest extends ViewTransactionRequest {
    // Quotation instance
    protected Quotation quotation;

    /***
     * Request an international transfer quotation
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createQuotation() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();

        if (this.quotation == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.QUOTATION_OBJECT_INIT_ERROR).build());
        }

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
    public Quotation viewQuotation(final String quotationReference) throws MobileMoneyException {
        if (StringUtils.isNullOrEmpty(quotationReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }

        String resourcePath = API.VIEW_QUOTATION.replace(Constants.QUOTATION_REFERENCE, quotationReference);
        return createRequest(HttpMethod.GET, resourcePath, null, notificationType, callBackURL, Quotation.class);
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
