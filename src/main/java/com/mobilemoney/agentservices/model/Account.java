/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.agentservices.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Fees;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Account
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -4447633319305650372L;

    //A collection of key/value pairs that enable the account to be identified. Keys include MSISDN and Wallet Identifier.
    private List<AccountIdentifier> accountIdentifiers;

    //An array containing the details of each identity associated with an account.
    private List<Identity> identity;

    //A non-harmonised field that indicates the type of the account.
    private String accountType;

    //A collection of key/value pairs. These can be used to populate provider specific fields.
    private List<CustomData> customData = new ArrayList<>();

    //Returns all fees that are applicable to the object.
    private List<Fees> fees;

    //The entity that registered the account, for example, a mobile money agent.
    private String registeringEntity;

    //The date and time of the request as supplied by the client.
    private String requestDate;

    //Indicates a harmonised representation of the account status. This will be shown as available, unavailable or unregistered.
    private String accountStatus;

    //Field can be used to return a provider-specific status for the account.
    private String accountSubStatus;

    //Current outstanding balance on the account.
    private String currentBalance;

    //Indicates the balance that is able to be debited for an account. This balance is only provided on some API provider systems.
    private String availableBalance;

    //Indicates the portion of the balance that is reserved, i.e. intended to be debited. This balance is only provided on some API provider systems.
    private String reservedBalance;

    //Indicates the sum of uncleared funds in an account, i.e. the funds that are awaiting a credit confirmation.
    private String unclearedBalance;

    //Currency of the account.
    private String currency;

    //Returns all commission earned by the registering entity for the creation of the account.
    private List<Commission> commissionEarned;

    //Date and time when the object was created by the API Provider.
    private String creationDate;

    //Date and time when the object was modified by the API Provider.
    private String modificationDate;

    /**
     * *
     *
     * @return
     */
    public List<AccountIdentifier> getAccountIdentifiers() {
        return accountIdentifiers;
    }

    /**
     * *
     *
     * @param accountIdentifiers
     */
    public void setAccountIdentifiers(List<AccountIdentifier> accountIdentifiers) {
        this.accountIdentifiers = accountIdentifiers;
    }

    /**
     * *
     *
     * @return
     */
    public List<Identity> getIdentity() {
        return identity;
    }

    /**
     * *
     *
     * @param identity
     */
    public void setIdentity(List<Identity> identity) {
        this.identity = identity;
    }

    /**
     * *
     *
     * @return
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * *
     *
     * @param accountType
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * *
     *
     * @return
     */
    public List<CustomData> getCustomData() {
        return customData;
    }

    /**
     * *
     *
     * @param customData
     */
    public void setCustomData(List<CustomData> customData) {
        this.customData = customData;
    }

    /**
     * *
     *
     * @return
     */
    public List<Fees> getFees() {
        return fees;
    }

    /**
     * *
     *
     * @param fees
     */
    public void setFees(List<Fees> fees) {
        this.fees = fees;
    }

    /**
     * *
     *
     * @return
     */
    public String getRegisteringEntity() {
        return registeringEntity;
    }

    /**
     * *
     *
     * @param registeringEntity
     */
    public void setRegisteringEntity(String registeringEntity) {
        this.registeringEntity = registeringEntity;
    }

    /**
     * *
     *
     * @return
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * *
     *
     * @param requestDate
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * *
     *
     * @return
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * *
     *
     * @param accountStatus
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * *
     *
     * @return
     */
    public String getAccountSubStatus() {
        return accountSubStatus;
    }

    /**
     * *
     *
     * @param accountSubStatus
     */
    public void setAccountSubStatus(String accountSubStatus) {
        this.accountSubStatus = accountSubStatus;
    }

    /**
     * *
     *
     * @return
     */
    public String getCurrentBalance() {
        return currentBalance;
    }

    /**
     * *
     *
     * @param currentBalance
     */
    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * *
     *
     * @return
     */
    public String getAvailableBalance() {
        return availableBalance;
    }

    /**
     * *
     *
     * @param availableBalance
     */
    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    /**
     * *
     *
     * @return
     */
    public String getReservedBalance() {
        return reservedBalance;
    }

    /**
     * *
     *
     * @param reservedBalance
     */
    public void setReservedBalance(String reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    /**
     * *
     *
     * @return
     */
    public String getUnclearedBalance() {
        return unclearedBalance;
    }

    /**
     * *
     *
     * @param unclearedBalance
     */
    public void setUnclearedBalance(String unclearedBalance) {
        this.unclearedBalance = unclearedBalance;
    }

    /**
     * *
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * *
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * *
     *
     * @return
     */
    public List<Commission> getCommissionEarned() {
        return commissionEarned;
    }

    /**
     * *
     *
     * @param commissionEarned
     */
    public void setCommissionEarned(List<Commission> commissionEarned) {
        this.commissionEarned = commissionEarned;
    }

    /**
     * *
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * *
     *
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * *
     *
     * @return
     */
    public String getModificationDate() {
        return modificationDate;
    }

    /**
     * *
     *
     * @param modificationDate
     */
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * *
     * Returns JSON object
     *
     * @return
     */
    public String toJSON() {
        return JSONFormatter.toJSON(this);
    }
}
