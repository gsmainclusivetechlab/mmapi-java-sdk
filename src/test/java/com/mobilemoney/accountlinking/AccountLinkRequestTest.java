package com.mobilemoney.accountlinking;

import com.mobilemoney.accountlinking.model.AccountLink;
import com.mobilemoney.accountlinking.model.AccountLinkResponse;
import com.mobilemoney.accountlinking.request.AccountLinkRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AccountBalance;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CreditParty;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.DebitParty;
import com.mobilemoney.common.model.IdentifierData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.KYCSubject;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.ServiceStatusResponse;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.internationaltransfer.model.Identification;
import com.mobilemoney.internationaltransfer.model.KYC;
import com.mobilemoney.internationaltransfer.model.PostalAddress;
import com.mobilemoney.merchantpayment.model.TransactionResponse;
import com.mobilemoney.merchantpayment.request.PaymentRequest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

class AccountLinkRequestTest {
    static PropertiesLoader loader;

    @BeforeAll
    public static void init(){
        loader = new PropertiesLoader();
    }
    
    @Test
    @DisplayName("Create Account Link Success")
    void createAccountLinkTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setAccountLink(getAccountsLinkSuccessObject());
        
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "pending");
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        
    }
    
    @Test
    @DisplayName("Create Account Link Failed")
    void createAccountLinkTestFailed() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setAccountLink(getAccountsLinkFailedObject());
        
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        
        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList)));
    }
    
    @Test
    @DisplayName("Perform a Transfer for a Linked Account Success")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setTransaction(createAccountLinkTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).addCallBack(loader.get("CALLBACK_URL")).createTransferTransaction();

        sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();
        
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "completed");
        assertEquals(sdkResponse.getNotificationMethod(), "callback");

        TransactionResponse transaction = mmClient.addRequest(accountLinkRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
    }
    
    @Test
    @DisplayName("Perform a Transfer for a Linked Account Failed")
    void viewTransactionTestFailed() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setTransaction(createAccountLinkTransactionFailedObject());
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).addCallBack(loader.get("CALLBACK_URL")).createTransferTransaction();

        sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();
        
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "completed");
        assertEquals(sdkResponse.getNotificationMethod(), "callback");

        TransactionResponse transaction = mmClient.addRequest(accountLinkRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
    }
    
    @Test
    @DisplayName("Perform a Transfer using an Account Link via the Polling Method")
    void accountLinkRequestUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).setNotificationType(NotificationType.POLLING).createTransferTransaction();

        sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "completed");
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        
        TransactionResponse transactionResponse = mmClient.addRequest(accountLinkRequest).viewTransaction(sdkResponse.getObjectReference());

        assertNotNull(transactionResponse);
    }
    
    @Test
    @DisplayName("Perform a Transfer Reversal")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createTransferTransaction();

        sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Transaction transaction = new Transaction();
        transaction.setType("reversal");
        accountLinkRequest.setTransaction(transaction);
        sdkResponse =  mmClient.addRequest(accountLinkRequest).createReversal(txnRef);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "pending");
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
    }
    
    @Test
    @DisplayName("Obtain a Financial Service Provider Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AccountBalance accountBalance = mmClient.addRequest(new AccountLinkRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }
    
    @Test
    @DisplayName("Retrieve Transfers for a Financial Service Provider")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "2000"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<TransactionResponse> transactions = mmClient.addRequest(new AccountLinkRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
        
        assertNotNull(transactions);
    }
    
    @Test
    @DisplayName("Check for Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new AccountLinkRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }
    
    @Test
    @DisplayName("Retrieve a Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        accountLinkRequest.setAccountLink(getAccountsLinkSuccessObject());
        
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));

        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getStatus(), "pending");
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        
        String clientCorrelationId = accountLinkRequest.getClientCorrelationId();
        AccountLinkResponse accountLinkResponse = mmClient.addRequest(accountLinkRequest).viewResponse(clientCorrelationId, AccountLinkResponse.class);

        assertNotNull(accountLinkResponse);
    }
    
    /***
     *
     * @param amount
     * @param currency
     * @return
     */
    private Transaction getTransactionObject(String amount, String currency) {
        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();

        debitPartyList.add(new DebitParty("accountid", "2999"));
        creditPartyList.add(new CreditParty("accountid", "2999"));

        Transaction transaction = new Transaction();
        transaction.setDebitParty(debitPartyList);
        transaction.setCreditParty(creditPartyList);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);

        return transaction;
    }
    
    /***
     *
     * @return
     */
    private Transaction createAccountLinkTransactionObject() {
        Transaction transaction = new Transaction();
        KYC senderKyc = new KYC();
        KYCSubject kycSubject = new KYCSubject();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        PostalAddress postalAddress = new PostalAddress("GB");
        Identification identification = new Identification("nationalidcard");
        InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();
        List<Identification> identificationList = new ArrayList<>();

        identification.setIdNumber("1234567");
        identification.setIssuer("UKPA");
        identification.setIssuerPlace("GB");
        identification.setIssuerCountry("GB");
        identification.setIssueDate("2018-07-03T11:43:27.405Z");
        identification.setExpiryDate("2021-07-03T11:43:27.405Z");
        identification.setOtherIddescription("test");
        identificationList.add(identification);

        kycSubject.setTitle("Mr");
        kycSubject.setFirstName("Luke");
        kycSubject.setMiddleName("R");
        kycSubject.setLastName("Skywalker");
        kycSubject.setFullName("Luke R Skywalker");
        kycSubject.setNativeName("ABC");

        senderKyc.setNationality("GB");
        senderKyc.setBirthCountry("GB");
        senderKyc.setOccupation("Manager");
        senderKyc.setEmployerName("MFX");
        senderKyc.setContactPhone("+447125588999");
        senderKyc.setGender("m");
        senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
        senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
        senderKyc.setIdDocument(identificationList);
        senderKyc.setPostalAddress(postalAddress);
        senderKyc.setSubjectName(kycSubject);

        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

        debitPartyList.add(new DebitParty("walletid", "1"));
        creditPartyList.add(new CreditParty("msisdn", "+44012345678"));

        transaction.setAmount("100.00");
        transaction.setCurrency("GBP");
        transaction.setInternationalTransferInformation(transferInformation);
        transaction.setSenderKyc(senderKyc);
        transaction.setRequestingOrganisation(requestingOrganisation);
        transaction.setCreditParty(creditPartyList);
        transaction.setDebitParty(debitPartyList);

        return transaction;
    }
    
    /***
     *
     * @return
     */
    private Transaction createAccountLinkTransactionFailedObject() {
        Transaction transaction = new Transaction();
        KYC senderKyc = new KYC();
        KYCSubject kycSubject = new KYCSubject();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        PostalAddress postalAddress = new PostalAddress("GB");
        Identification identification = new Identification("nationalidcard");
        InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();
        List<Identification> identificationList = new ArrayList<>();

        identification.setIdNumber("1234567");
        identification.setIssuer("UKPA");
        identification.setIssuerPlace("GB");
        identification.setIssuerCountry("GB");
        identification.setIssueDate("2018-07-03T11:43:27.405Z");
        identification.setExpiryDate("2021-07-03T11:43:27.405Z");
        identification.setOtherIddescription("test");
        identificationList.add(identification);

        kycSubject.setTitle("Mr");
        kycSubject.setFirstName("Luke");
        kycSubject.setMiddleName("R");
        kycSubject.setLastName("Skywalker");
        kycSubject.setFullName("Luke R Skywalker");
        kycSubject.setNativeName("ABC");

        senderKyc.setNationality("GB");
        senderKyc.setBirthCountry("GB");
        senderKyc.setOccupation("Manager");
        senderKyc.setEmployerName("MFX");
        senderKyc.setContactPhone("+447125588999");
        senderKyc.setGender("m");
        senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
        senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
        senderKyc.setIdDocument(identificationList);
        senderKyc.setPostalAddress(postalAddress);
        senderKyc.setSubjectName(kycSubject);

        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

        debitPartyList.add(new DebitParty("walletid", "1"));
        creditPartyList.add(new CreditParty("msisdn", "+44012345678"));

        transaction.setAmount("0.00");
        transaction.setCurrency("GBP");
        transaction.setInternationalTransferInformation(transferInformation);
        transaction.setSenderKyc(senderKyc);
        transaction.setRequestingOrganisation(requestingOrganisation);
        transaction.setCreditParty(creditPartyList);
        transaction.setDebitParty(debitPartyList);

        return transaction;
    }
    
    /***
     *
     * @return
     */
    private AccountLink getAccountsLinkSuccessObject() {
        List<DebitParty> sourceAccountIdentifiers = new ArrayList<>();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        List<CustomData> customDataList = new ArrayList<>();
        
        sourceAccountIdentifiers.add(new DebitParty("accountid", "2999"));

        customDataList.add(new CustomData("keytest", "keyvalue"));
        
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        
        AccountLink accountLink = new AccountLink();
        accountLink.setSourceAccountIdentifiers(sourceAccountIdentifiers);
        accountLink.setMode("active");
        accountLink.setStatus("both");
        accountLink.setRequestingOrganisation(requestingOrganisation);
        accountLink.setRequestDate("2018-07-03T11:43:27.405Z");
        accountLink.setCustomData(customDataList);
        
        return accountLink;
    }

    /***
     *
     * @return
     */
    private AccountLink getAccountsLinkFailedObject() {
    	AccountLink accountLink = new AccountLink();

        return accountLink;
    }

}
