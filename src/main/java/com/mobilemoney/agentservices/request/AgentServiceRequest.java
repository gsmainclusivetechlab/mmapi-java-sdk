/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.agentservices.request;

import com.mobilemoney.agentservices.model.Account;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.AuthorizationCodeRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;
import com.mobilemoney.merchantpayment.constants.TransactionType;

import java.util.List;
import java.util.UUID;

/**
 * Class AgentServiceRequest
 */
public class AgentServiceRequest extends ViewTransactionRequest {

    // Transaction reference
    private Transaction transaction;

    // AuthorizationCodeRequest reference
    private AuthorizationCodeRequest authorizationCodeRequest;

    //RequestAccount reference
    private Account account;

    // PatchData reference
    private List<PatchData> patchData;

    /**
     * *
     * Default constructor
     *
     */
    public AgentServiceRequest() {
        this.authorizationCodeRequest = new AuthorizationCodeRequest();
    }

    /**
     * *
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createWithdrawalTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (this.transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.WITHDRAWAL);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, this.transaction.toJSON(), this.notificationType, this.callBackURL, AsyncResponse.class);
    }

    /**
     * *
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAuthorisationCode(Identifiers identifiers) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        return this.authorizationCodeRequest.createAuthorisationCode(identifiers, this.callBackURL, this.clientCorrelationId);
    }

    /**
     * *
     *
     * @param identifiers
     * @param authorisationCode
     * @return
     * @throws MobileMoneyException
     */
    public AuthorisationCode viewAuthorisationCode(Identifiers identifiers, final String authorisationCode) throws MobileMoneyException {
    	if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        return this.authorizationCodeRequest.viewAuthorisationCode(identifiers, authorisationCode);
    }

    /**
     * *
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AccountHolderName viewAccountName(Identifiers identifiers) throws MobileMoneyException {
    	if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        return this.authorizationCodeRequest.viewAccountName(identifiers);
    }

    /**
     * *
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createDepositTransaction() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (this.transaction == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.TRANSACTION_OBJECT_INIT_ERROR).build());
        }
        String resourcePath = API.TRANSACTION_TYPE.replace(Constants.TRANSACTION_TYPE, TransactionType.DEPOSIT);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, this.transaction.toJSON(), this.notificationType, this.callBackURL, AsyncResponse.class);
    }

    /**
     * *
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAccount() throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (this.account == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.ACCOUNT_OBJECT_INIT_ERROR).build());
        }
        String resourcePath = API.VIEW_ACCOUNT_IDENTITY_TYPE.replace(Constants.IDENTITY_TYPE, Constants.INDIVIDUAL);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, this.account.toJSON(), this.notificationType, this.callBackURL, AsyncResponse.class);
    }

    /**
     * *
     *
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public Account viewAccount(Identifiers identifiers) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        return createRequest(HttpMethod.GET, getResourcePath(API.VIEW_ACCOUNT_IDENTIFIER, identifiers), Account.class);
    }

    /**
     * *
     *
     * @param identifiers
     * @param identityId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse updateAccountIdentity(Identifiers identifiers, final String identityId) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        if (this.patchData == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.PATCH_DATA_OBJECT_INIT_ERROR).build());
        }
        if (StringUtils.isNullOrEmpty(identityId)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }
        String resourcePath = getResourcePath(API.VIEW_ACCOUNT_IDENTITY_ID, identifiers).replace(Constants.IDENTITY_ID, identityId);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.PATCH, resourcePath, JSONFormatter.toJSONArray(this.patchData), notificationType, callBackURL, AsyncResponse.class);
    }

    /**
     * *
     *
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * *
     *
     * @return
     */
    public List<PatchData> getPatchData() {
        return patchData;
    }

    /**
     * *
     *
     * @param patchData
     */
    public void setPatchData(List<PatchData> patchData) {
        this.patchData = patchData;
    }

    /**
     * *
     *
     * @param authorisationCode
     */
    public void setAuthorisationCodeRequest(AuthorisationCode authorisationCode) {
        this.authorizationCodeRequest.setAuthorisationCodeRequest(authorisationCode);
    }

    /**
     * *
     *
     * @param transaction
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * *
     * Set call back URL
     *
     * @param callBackURL
     * @return
     */
    public AgentServiceRequest addCallBack(final String callBackURL) {
        this.callBackURL = callBackURL;
        return setNotificationType(NotificationType.CALLBACK);
    }

    /**
     * *
     * Set notification type
     *
     * @param notificationType
     * @return
     */
    public AgentServiceRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    /**
     * *
     *
     * @param reversal
     */
    public void setReversal(Reversal reversal) {
        this.reversal = reversal;
    }

    /**
     * *
     *
     * @return
     */
    public String getClientCorrelationId() {
        return this.clientCorrelationId;
    }
}
