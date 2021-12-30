/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.accountlinking.request;

import java.util.stream.Collectors;

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.util.StringUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.common.request.ViewTransactionRequest;
import java.util.UUID;

/**
 * Class AccountLinkingRequest
 */
public class AccountLinkingRequest extends ViewTransactionRequest{
    // Transaction Reference
    private Transaction transaction;
    
    // Link Reference
    private Link link;
    
    // CreateTransactionRequest Reference
    private CreateTransactionRequest createTransactionRequest;
    
    /***
     * Default constructor
     */
    public AccountLinkingRequest() {
        createTransactionRequest = new CreateTransactionRequest();
    }
    
    /***
     *
     * @param link
     */
    public void setLink(Link link) {
        this.link = link;
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
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createAccountLink(Identifiers identifiers) throws MobileMoneyException {
        this.clientCorrelationId = UUID.randomUUID().toString();
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        if (link == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.ACCOUNT_LINK_OBJECT_INIT_ERROR).build());
        }
        String resourcePath = getResourcePath(API.CREATE_ACCOUNT_LINKS, identifiers);
        MobileMoneyContext.getContext().getHTTPHeaders().put(Constants.CORRELATION_ID, this.clientCorrelationId);
        return createRequest(HttpMethod.POST, resourcePath, link.toJSON(), notificationType, callBackURL, AsyncResponse.class);
    }
    
    /***
     *
     * @param identifiers
     * @param linkReference
     * @return
     * @throws MobileMoneyException
     */
    public Link viewAccountLink(Identifiers identifiers, String linkReference) throws MobileMoneyException {
        if (identifiers == null) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.VALIDATION_ERROR_CATEGORY, Constants.VALUE_NOT_SUPPLIED_ERROR_CODE).errorDescription(Constants.IDENTIFIER_OBJECT_INIT_ERROR).build());
        }
        if (StringUtils.isNullOrEmpty(linkReference)) {
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY, Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
        }
    	String resourcePath = API.VIEW_ACCOUNT_LINK.replace(Constants.ACCOUNT_ID, identifiers.getIdentifiers().stream().map(identifier -> identifier.getKey().concat("@").concat(identifier.getValue())).collect(Collectors.joining("$"))).replace(Constants.LINK_REFERENCE, linkReference);
        return createRequest(HttpMethod.GET, resourcePath, Link.class);
    }
    
    /***
     *
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse createTransferTransaction() throws MobileMoneyException {
    	this.clientCorrelationId = UUID.randomUUID().toString();
        return this.createTransactionRequest.createTransferTransaction(this.transaction, this.callBackURL, this.notificationType, this.clientCorrelationId);
    }
    
    /***
    *
    * @param callBackURL
    * @return
    */
   public AccountLinkingRequest addCallBack(final String callBackURL) {
       this.callBackURL = callBackURL;
       return setNotificationType(NotificationType.CALLBACK);
   }
   
   /***
     * Add notification type
     *
     * @param notificationType
     * @return
     */
    public AccountLinkingRequest setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }
    
    /***
     * 
     * @param reversal
     */
    public void setReversal(Reversal reversal) {
    	this.reversal = reversal;
    }
}
