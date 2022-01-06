package com.mobilemoney.billpayment.request;

import java.util.List;
import java.util.UUID;

import com.mobilemoney.base.HttpResponse;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.constants.HttpStatusCode;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.billpayment.model.Bill;
import com.mobilemoney.billpayment.model.BillPay;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Filter;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.CommonRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;
import com.mobilemoney.merchantpayment.constants.TransactionType;

/***
 * Class BillPaymentRequest
 *
 */
public class BillPaymentRequest extends CommonRequest {
	// Transaction Reference
	private Transaction transaction;

	// BillPay Reference
	private BillPay billPay;

	// View Transaction Request Reference
	private ViewTransactionRequest viewTransactionRequest;

	public BillPaymentRequest() {
		this.viewTransactionRequest = new ViewTransactionRequest();
	}

	/***
	 * Performs a bill payment transaction
	 *
	 * @return
	 * @throws MobileMoneyException
	 */
	public AsyncResponse createBillTransaction() throws MobileMoneyException {
		this.clientCorrelationId = UUID.randomUUID().toString();

		if (transaction == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
		}

		MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

		String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.BILL_PAY);
		return createRequest(HttpMethod.POST, resourcePath, this.transaction.toJSON(), notificationType, callBackURL,
				AsyncResponse.class);
	}

	/***
	 *
	 * @param identifiers
	 * @param billReference
	 * @return
	 * @throws MobileMoneyException
	 */
	public AsyncResponse createBillPayment(Identifiers identifiers, final String billReference)
			throws MobileMoneyException {
		this.clientCorrelationId = UUID.randomUUID().toString();

		if (identifiers == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
		}

		if (StringUtils.isNullOrEmpty(billReference)) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY,
							Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
		}

		if (billPay == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.BILL_PAY_OBJECT_INIT_ERROR).build());
		}

		MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);

		String resourcePath = getResourcePath(API.CREATE_BILL_PAYMENT, identifiers).replace(Constants.BILL_REFERENCE,
				billReference);
		return createRequest(HttpMethod.POST, resourcePath, this.billPay.toJSON(), notificationType, callBackURL,
				AsyncResponse.class);
	}

	/***
	 *
	 * @param identifiers
	 * @return
	 * @throws MobileMoneyException
	 */
	public List<Bill> viewAccountBills(Identifiers identifiers) throws MobileMoneyException {
		if (identifiers == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
		}

		return viewAccountBills(identifiers, null);
	}

	/***
	 *
	 * @param identifiers
	 * @param filter
	 * @return
	 * @throws MobileMoneyException
	 */
	public List<Bill> viewAccountBills(Identifiers identifiers, Filter filter) throws MobileMoneyException {
		if (identifiers == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
		}

		StringBuilder resourcePath = new StringBuilder(getResourcePath(API.VIEW_ACCOUNT_BILLS, identifiers));

		if (filter != null) {
			applyFilter(resourcePath, filter);
		}

		HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

		return convertResponseToList(requestResponse, Bill.class);
	}

	/***
	 *
	 * @param identifiers
	 * @param billReference
	 * @return
	 * @throws MobileMoneyException
	 */
	public List<BillPay> viewBillPayment(Identifiers identifiers, final String billReference)
			throws MobileMoneyException {
		if (identifiers == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
		}

		return viewBillPayment(identifiers, billReference, null);
	}

	/***
	 *
	 * @param identifiers
	 * @param billReference
	 * @param filter
	 * @return
	 * @throws MobileMoneyException
	 */
	public List<BillPay> viewBillPayment(Identifiers identifiers, final String billReference, Filter filter)
			throws MobileMoneyException {
		if (identifiers == null) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY,
							Constants.VALUE_NOT_SUPPLIED_ERROR_CODE)
									.errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
		}

		StringBuilder resourcePath = new StringBuilder(
				getResourcePath(API.VIEW_BILL_PAYMENTS, identifiers).replace(Constants.BILL_REFERENCE, billReference));

		if (filter != null) {
			applyFilter(resourcePath, filter);
		}

		HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath.toString());

		return convertResponseToList(requestResponse, BillPay.class);
	}

	/***
	 * 
	 * @param transactionReference
	 * @return
	 * @throws MobileMoneyException
	 */
	public Transaction viewTransaction(final String transactionReference) throws MobileMoneyException {
		return this.viewTransactionRequest.viewTransaction(transactionReference);
	}

	/***
	 * Set call back URL
	 *
	 * @param callBackURL
	 * @return
	 */
	public BillPaymentRequest addCallBack(final String callBackURL) {
		this.callBackURL = callBackURL;
		return setNotificationType(NotificationType.CALLBACK);
	}

	/***
	 * Add notification type
	 *
	 * @param notificationType
	 * @return
	 */
	public BillPaymentRequest setNotificationType(final NotificationType notificationType) {
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
	 * @param billPay
	 */
	public void setBillPayment(BillPay billPay) {
		this.billPay = billPay;
	}
	
	/***
	 * 
	 * @param billPayJsonString
	 */
	public void setBillPayment(final String billPayJsonString) {
		this.billPay = JSONFormatter.fromJSON(billPayJsonString, BillPay.class);
	}

	/***
	 * 
	 * @param <T>
	 * @param requestResponse
	 * @param claz
	 * @return
	 */
	private <T> List<T> convertResponseToList(HttpResponse requestResponse, Class<T> claz) {
		List<T> responseList = null;

		if (requestResponse.getPayLoad() instanceof String) {
			if (requestResponse.getResponseCode().equals(HttpStatusCode.OK)) {
				responseList = JSONFormatter.fromJSONList((String) requestResponse.getPayLoad(), claz);
			}
		}

		return responseList;
	}

	/***
	 * TODO: Common
	 * 
	 * @param resourcePath
	 * @param filter
	 * @return
	 */
	private StringBuilder applyFilter(StringBuilder resourcePath, Filter filter) {
		resourcePath.append("?");

		if (filter.getLimit() >= 0)
			resourcePath.append("limit=").append(filter.getLimit()).append("&");
		if (filter.getOffset() >= 0)
			resourcePath.append("offset=").append(filter.getOffset()).append("&");
		if (!StringUtils.isNullOrEmpty(filter.getFromDateTime()))
			resourcePath.append("fromDateTime=").append(filter.getFromDateTime()).append("&");
		if (!StringUtils.isNullOrEmpty(filter.getToDateTime()))
			resourcePath.append("toDateTime=").append(filter.getToDateTime()).append("&");

		return resourcePath;
	}
}
