package com.mobilemoney.p2ptransfer.request;

import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.AuthorizationCodeRequest;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.common.request.TransferRequest;

import java.util.UUID;

/***
 * Class TransferRequest
 */
public class P2PTransferRequest extends TransferRequest {
	// Transaction Reference
	private Transaction transaction;

	// AuthorizationCodeRequest Reference
	private AuthorizationCodeRequest authorizationCodeRequest;

	// CreateTransactionRequest Reference
	private CreateTransactionRequest createTransactionRequest;

	/***
	 * Default constructor
	 */
	public P2PTransferRequest() {
		this.authorizationCodeRequest = new AuthorizationCodeRequest();
		this.createTransactionRequest = new CreateTransactionRequest();
	}

	/***
	 *
	 * @param identifiers
	 * @return
	 * @throws MobileMoneyException
	 */
	public AccountHolderName viewAccountName(Identifiers identifiers) throws MobileMoneyException {
		return this.authorizationCodeRequest.viewAccountName(identifiers);
	}

	/***
	 *
	 * @return
	 * @throws MobileMoneyException
	 */
	public AsyncResponse createTransferTransaction() throws MobileMoneyException {
		this.clientCorrelationId = UUID.randomUUID().toString();
		return this.createTransactionRequest.createTransferTransaction(this.transaction, this.callBackURL,
				this.notificationType, this.clientCorrelationId);
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
	 * @param callBackURL
	 * @return
	 */
	public P2PTransferRequest addCallBack(final String callBackURL) {
		this.callBackURL = callBackURL;
		return setNotificationType(NotificationType.CALLBACK);
	}

	/***
	 * Add notification type
	 *
	 * @param notificationType
	 * @return
	 */
	public P2PTransferRequest setNotificationType(final NotificationType notificationType) {
		this.notificationType = notificationType;
		return this;
	}
}
