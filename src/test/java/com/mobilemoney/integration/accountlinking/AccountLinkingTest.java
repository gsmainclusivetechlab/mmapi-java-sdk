package com.mobilemoney.integration.accountlinking;

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.base.constants.Mode;
import com.mobilemoney.base.constants.Status;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.internationaltransfer.model.Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

class AccountLinkingTest {
    static PropertiesLoader loader;

    @BeforeAll
    public static void init(){
        loader = new PropertiesLoader();
    }
    
    @Test
    @DisplayName("Create Account Link Success")
    void createAccountLinkTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setLink(getLinkSuccessObject());
        
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createAccountLink(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        
    }
    
    @Test
    @DisplayName("Create Account Link Failed")
    void createAccountLinkTestFailed() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setLink(getLinkFailedObject());
        
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        
        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(accountLinkingRequest).createAccountLink(new Identifiers(identifierList)));
    }
    
    @Test
    @DisplayName("Perform a Transfer for a Linked Account Success")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setTransaction(createAccountLinkTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack(loader.get("CALLBACK_URL")).createTransferTransaction();
        
        sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();
        
        Transaction transaction = mmClient.addRequest(accountLinkingRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionReference());
        assertNotNull(transaction.getTransactionStatus());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCurrency());
        assertNotNull(transaction.getCreditParty());
        assertNotNull(transaction.getDebitParty());
        assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transaction.getType()));
        assertTrue(transaction.getCreditParty().size() > 0);
        assertTrue(transaction.getDebitParty().size() > 0);
    }
    
    @Test
    @DisplayName("Perform a Transfer for a Linked Account Failed")
    void viewTransactionTestFailed() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setTransaction(createAccountLinkTransactionFailedObject());
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack(loader.get("CALLBACK_URL")).createTransferTransaction();
        
        sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();
        
        Transaction transaction = mmClient.addRequest(accountLinkingRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionReference());
        assertNotNull(transaction.getTransactionStatus());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCurrency());
        assertNotNull(transaction.getCreditParty());
        assertNotNull(transaction.getDebitParty());
        assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transaction.getType()));
        assertTrue(transaction.getCreditParty().size() > 0);
        assertTrue(transaction.getDebitParty().size() > 0);
    }
    
    @Test
    @DisplayName("Perform a Transfer using an Account Link via the Polling Method")
    void accountLinkRequestUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).setNotificationType(NotificationType.POLLING).createTransferTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        
        sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        Transaction transaction = mmClient.addRequest(accountLinkingRequest).viewTransaction(sdkResponse.getObjectReference());
    }
    
    @Test
    @DisplayName("Perform a Transfer Reversal")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        
        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createTransferTransaction();

        sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        accountLinkingRequest.setReversal(reversal);
        sdkResponse =  mmClient.addRequest(accountLinkingRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
    }
    
    @Test
    @DisplayName("Obtain a Financial Service Provider Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        Balance balance = mmClient.addRequest(new AccountLinkingRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }
    
    @Test
    @DisplayName("Retrieve Transfers for a Financial Service Provider")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "2000"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<Transaction> transactions = mmClient.addRequest(new AccountLinkingRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
        
        assertNotNull(transactions);
        if (transactions.size() > 0) {
        	assertNotNull(transactions.get(0).getTransactionReference());
            assertNotNull(transactions.get(0).getTransactionStatus());
            assertNotNull(transactions.get(0).getAmount());
            assertNotNull(transactions.get(0).getCurrency());
            assertNotNull(transactions.get(0).getCreditParty());
            assertNotNull(transactions.get(0).getDebitParty());
            assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transactions.get(0).getType()));
            assertTrue(transactions.get(0).getCreditParty().size() > 0);
            assertTrue(transactions.get(0).getDebitParty().size() > 0);
        }
    }
    
    @Test
    @DisplayName("Check for Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new AccountLinkingRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }
    
    @Test
    @DisplayName("Retrieve a Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        accountLinkingRequest.setLink(getLinkSuccessObject());
        
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack(loader.get("CALLBACK_URL")).createAccountLink(new Identifiers(identifierList));

        String clientCorrelationId = accountLinkingRequest.getClientCorrelationId();
        Link linkResponse = mmClient.addRequest(accountLinkingRequest).viewResponse(clientCorrelationId, Link.class);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertNotNull(linkResponse);
    }
    
    @Test
    @DisplayName("Get Details of a Specific Account Link")
    void viewAccountLinkTestSuccess() throws MobileMoneyException {
    	MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
    	AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

    	accountLinkingRequest.setLink(getLinkSuccessObject());
        
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createAccountLink(new Identifiers(identifierList));

        sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String linkRef = sdkResponse.getObjectReference();

        Link linkResponse = mmClient.addRequest(accountLinkingRequest).viewAccountLink(new Identifiers(identifierList), linkRef);

        assertNotNull(linkResponse);
        assertNotNull(linkResponse.getLinkReference());
        assertNotNull(linkResponse.getMode());
        assertNotNull(linkResponse.getStatus());
        assertNotNull(linkResponse.getSourceAccountIdentifiers());        
        if (linkResponse.getSourceAccountIdentifiers().size() > 0) {
        	assertNotNull(linkResponse.getSourceAccountIdentifiers().get(0).getKey());
        	assertNotNull(linkResponse.getSourceAccountIdentifiers().get(0).getValue());
        }
    }
    
    /***
     *
     * @param amount
     * @param currency
     * @return
     */
    private Transaction getTransactionObject(String amount, String currency) {
        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();

        debitPartyList.add(new AccountIdentifier("accountid", "2999"));
        creditPartyList.add(new AccountIdentifier("accountid", "2999"));

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
        KYCInformation senderKyc = new KYCInformation();
        Name kycSubject = new Name();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        Address address = new Address("GB");
        IdDocument idDocument = new IdDocument("nationalidcard");
        InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();
        List<IdDocument> identificationList = new ArrayList<>();

        idDocument.setIdNumber("1234567");
        idDocument.setIssuer("UKPA");
        idDocument.setIssuerPlace("GB");
        idDocument.setIssuerCountry("GB");
        idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
        idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
        idDocument.setOtherIddescription("test");
        identificationList.add(idDocument);

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
        senderKyc.setPostalAddress(address);
        senderKyc.setSubjectName(kycSubject);

        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

        debitPartyList.add(new AccountIdentifier("walletid", "1"));
        creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));

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
        KYCInformation senderKyc = new KYCInformation();
        Name kycSubject = new Name();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        Address address = new Address("GB");
        IdDocument idDocument = new IdDocument("nationalidcard");
        InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();
        List<IdDocument> identificationList = new ArrayList<>();

        idDocument.setIdNumber("1234567");
        idDocument.setIssuer("UKPA");
        idDocument.setIssuerPlace("GB");
        idDocument.setIssuerCountry("GB");
        idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
        idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
        idDocument.setOtherIddescription("test");
        identificationList.add(idDocument);

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
        senderKyc.setPostalAddress(address);
        senderKyc.setSubjectName(kycSubject);

        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

        debitPartyList.add(new AccountIdentifier("walletid", "1"));
        creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));

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
    private Link getLinkSuccessObject() {
        List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        List<CustomData> customDataList = new ArrayList<>();
        
        sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "2999"));

        customDataList.add(new CustomData("keytest", "keyvalue"));
        
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        
        Link link = new Link();
        link.setSourceAccountIdentifiers(sourceAccountIdentifiers);
        link.setMode(Mode.BOTH.getMode());
        link.setStatus(Status.ACTIVE.getStatus());
        link.setRequestingOrganisation(requestingOrganisation);
        link.setRequestDate("2018-07-03T11:43:27.405Z");
        link.setCustomData(customDataList);
        
        return link;
    }

    /***
     *
     * @return
     */
    private Link getLinkFailedObject() {
    	Link link = new Link();

        return link;
    }

}
