package com.mobilemoney.unit.accountlinking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.base.constants.Mode;
import com.mobilemoney.base.constants.Status;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;

public class AccountLinkingTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        Balance expectedBalance = getBalanceObject();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedBalance).when(accountLinkingRequestSpy).viewAccountBalance(identifiers);

        Balance actualBalance = accountLinkingRequestSpy.viewAccountBalance(identifiers);

        assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedResponse).when(accountLinkingRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = accountLinkingRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response for Account Link")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = createAccountLinkTransactionObject();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedTransaction).when(accountLinkingRequestSpy).viewResponse("clientCorrelationId",
                Transaction.class);

        Transaction actualTransaction = accountLinkingRequestSpy.viewResponse("clientCorrelationId",
                Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("Create Account Link With Payload Success")
    void createAccountLinkPayloadTestSuccess() throws MobileMoneyException {
        Link link = getLinkObject();
        String jsonString = link.toJSON();
        Link link1 = JSONFormatter.fromJSON(jsonString, Link.class);

        assertNotNull(jsonString);
        assertNotNull(link1);
        assertEquals(link.getLinkReference(), link1.getLinkReference());
    }

    @Test
    @DisplayName("Json String To Link Object Test Success")
    void jsonToLinkObjectTestSuccess() {
        String linkObjectString = "{\"sourceAccountIdentifiers\": [{\"key\": \"accountid\",\"value\": \"2999\"}],\"mode\": \"both\",\"status\": \"active\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";
        Link link = JSONFormatter.fromJSON(linkObjectString, Link.class);

        assertNotNull(link);
        assertEquals(link.getMode(), "both");
    }

    @Test
    @DisplayName("Json String To Link Object Test Failure")
    void jsonToLinkObjectTestFailure() {
        String linkObjectString = "{\"sourceAccountIdentifiers\": [{\"key\": \"accountid\",\"value\": \"2999\"}],\"mode\": \"both\",\"status\": \"active\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";
        Link link = JSONFormatter.fromJSON(linkObjectString, Link.class);

        assertNotNull(link);
        assertNotEquals(link.getMode(), "blank");
    }

    @Test
    @DisplayName("Create Account Link Success")
    void createAccountLinkTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();

        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkingRequestSpy).createAccountLink(identifiers);

        AsyncResponse actualSdkResponse = accountLinkingRequestSpy.createAccountLink(identifiers);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Create Account Link Test Failure")
    void createAccountLinkTestFailure() {
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        assertThrows(MobileMoneyException.class, () -> accountLinkingRequest.createAccountLink(null));
    }

    @Test
    @DisplayName("Initiate AccountLink Transfer Success")
    void initiateAccountLinkTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        accountLinkingRequest.setTransaction(createAccountLinkTransactionObject());

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkingRequestSpy).createTransferTransaction();

        AsyncResponse actualSdkResponse = accountLinkingRequestSpy.createTransferTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        accountLinkingRequest.setTransaction(createAccountLinkTransactionObject());

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkingRequestSpy).viewRequestState(SERVER_CORRELATION_ID);

        AsyncResponse actualSdkResponse = accountLinkingRequestSpy.viewRequestState(SERVER_CORRELATION_ID);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = createAccountLinkTransactionObject();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedTransaction).when(accountLinkingRequestSpy).viewTransaction("reference");

        Transaction actualTransaction = accountLinkingRequestSpy.viewTransaction("reference");

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
    }

    @Test
    @DisplayName("AccountLinkRequest Transfer Reversal Success")
    void accountLinkRequestTransferReversal() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        Reversal reversal = new Reversal();

        reversal.setType("reversal");
        accountLinkingRequest.setReversal(reversal);

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkingRequestSpy)
                .createReversal("transactionReference");

        AsyncResponse actualSdkResponse = accountLinkingRequestSpy.createReversal("transactionReference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Retrieve Transactions Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        List<Transaction> expectedList = getTransactionList();
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedList).when(accountLinkingRequestSpy).viewAccountTransactions(identifiers);

        List<Transaction> actualList = accountLinkingRequestSpy.viewAccountTransactions(identifiers);

        assertNotNull(expectedList);
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());
        assertTrue(expectedList.size() == 2);
        assertTrue(actualList.size() == 2);
        assertEquals(expectedList.get(0).getAmount(), actualList.get(0).getAmount());
    }

    @Test
    @DisplayName("Get Details of a Specific Account Link")
    void viewAccountLinkTestSuccess() throws MobileMoneyException {
        Link expectedResponse = getLinkObject();

        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkingRequest accountLinkingRequestSpy = Mockito.spy(accountLinkingRequest);

        Mockito.doReturn(expectedResponse).when(accountLinkingRequestSpy).viewAccountLink(identifiers, "reference");

        Link linkResponse = accountLinkingRequestSpy.viewAccountLink(identifiers, "reference");

        assertNotNull(expectedResponse);
        assertNotNull(linkResponse);
        assertTrue(expectedResponse.getStatus().equals(linkResponse.getStatus()));
    }

    @Test
    @DisplayName("Get Details of a Specific Account Link Test Failure")
    void viewAccountLinkTestFailure() {
        AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

        assertThrows(MobileMoneyException.class, () -> accountLinkingRequest.viewAccountLink(null, null));
    }

    /**
     * *
     *
     * @return
     */
    private Balance getBalanceObject() {
        Balance balance = new Balance();
        balance.setAccountStatus("available");
        balance.setAvailableBalance("100.00");
        return balance;
    }

    /**
     * *
     *
     * @return
     */
    private ServiceAvailability getServiceAvailabilityObject() {
        ServiceAvailability serviceAvailability = new ServiceAvailability();
        serviceAvailability.setServiceStatus("available");
        return serviceAvailability;
    }

    /**
     * *
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

    /**
     * *
     *
     * @return
     */
    private Link getLinkObject() {
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

    /**
     * *
     *
     * @return
     */
    private AsyncResponse getAsyncResponse() {
        AsyncResponse asyncResponse = new AsyncResponse();

        asyncResponse.setServerCorrelationId(SERVER_CORRELATION_ID);
        asyncResponse.setStatus("pending");

        return asyncResponse;
    }

    /**
     * *
     *
     * @return
     */
    private List<Transaction> getTransactionList() {
        List<Transaction> transactions = new ArrayList<>();
        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();

        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        creditPartyList.add(new AccountIdentifier("walletid", "1"));

        Transaction transaction1 = new Transaction();
        transaction1.setDebitParty(debitPartyList);
        transaction1.setCreditParty(creditPartyList);
        transaction1.setAmount("16.00");
        transaction1.setCurrency("USD");

        Transaction transaction2 = new Transaction();
        transaction2.setDebitParty(debitPartyList);
        transaction2.setCreditParty(creditPartyList);
        transaction2.setAmount("17.00");
        transaction2.setCurrency("USD");

        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }

}
