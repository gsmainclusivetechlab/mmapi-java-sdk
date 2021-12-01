package com.mobilemoney.p2ptransfer.request;

import com.mobilemoney.common.model.AccountNameResponse;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
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
    public AccountNameResponse viewAccountName(Identifiers identifiers) throws MobileMoneyException {
        return this.authorizationCodeRequest.viewAccountName(identifiers);
    }

    /***
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createTransferTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createTransferTransaction(this.transaction, this.callBackURL, this.clientCorrelationId);
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
     * @param callBackURL
     * @return
     */
    public P2PTransferRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return this;
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
