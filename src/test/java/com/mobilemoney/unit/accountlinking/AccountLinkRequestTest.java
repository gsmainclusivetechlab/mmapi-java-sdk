package com.mobilemoney.unit.accountlinking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mobilemoney.accountlinking.model.AccountLink;
import com.mobilemoney.accountlinking.request.AccountLinkRequest;
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

public class AccountLinkRequestTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        Balance expectedBalance = getBalanceObject();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedBalance).when(accountLinkRequestSpy).viewAccountBalance(identifiers);

        Balance actualBalance = accountLinkRequestSpy.viewAccountBalance(identifiers);

        assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedResponse).when(accountLinkRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = accountLinkRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response for Account Link")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = createAccountLinkTransactionObject();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedTransaction).when(accountLinkRequestSpy).viewResponse("clientCorrelationId",
                Transaction.class);

        Transaction actualTransaction = accountLinkRequestSpy.viewResponse("clientCorrelationId",
                Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("Create Account Link Success")
    void createAccountLinkTestSuccess() throws MobileMoneyException {
        AccountLink accountLink = getAccountsLinkObject();
        String jsonString = accountLink.toJSON();
        AccountLink accountLink1 = JSONFormatter.fromJSON(jsonString, AccountLink.class);

        assertNotNull(jsonString);
        assertNotNull(accountLink1);
        assertEquals(accountLink.getLinkReference(), accountLink1.getLinkReference());
    }

    @Test
    @DisplayName("Initiate AccountLink Transfer Success")
    void initiateAccountLinkTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        accountLinkRequest.setTransaction(createAccountLinkTransactionObject());

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkRequestSpy).createTransferTransaction();

        AsyncResponse actualSdkResponse = accountLinkRequestSpy.createTransferTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        accountLinkRequest.setTransaction(createAccountLinkTransactionObject());

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkRequestSpy).viewRequestState(SERVER_CORRELATION_ID);

        AsyncResponse actualSdkResponse = accountLinkRequestSpy.viewRequestState(SERVER_CORRELATION_ID);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = createAccountLinkTransactionObject();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedTransaction).when(accountLinkRequestSpy).viewTransaction("reference");

        Transaction actualTransaction = accountLinkRequestSpy.viewTransaction("reference");

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
    }

    @Test
    @DisplayName("AccountLinkRequest Transfer Reversal Success")
    void accountLinkRequestTransferReversal() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        Reversal reversal = new Reversal();

        reversal.setType("reversal");
        accountLinkRequest.setReversal(reversal);

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedSdkResponse).when(accountLinkRequestSpy)
                .createReversal("transactionReference");

        AsyncResponse actualSdkResponse = accountLinkRequestSpy.createReversal("transactionReference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Retrieve Transactions Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        List<Transaction> expectedList = getTransactionList();
        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedList).when(accountLinkRequestSpy).viewAccountTransactions(identifiers);

        List<Transaction> actualList = accountLinkRequestSpy.viewAccountTransactions(identifiers);

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
        AccountLink expectedResponse = getAccountsLinkObject();

        AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        AccountLinkRequest accountLinkRequestSpy = Mockito.spy(accountLinkRequest);

        Mockito.doReturn(expectedResponse).when(accountLinkRequestSpy).viewAccountLink(identifiers, "reference");

        AccountLink accountLinkResponse = accountLinkRequestSpy.viewAccountLink(identifiers, "reference");

        assertNotNull(expectedResponse);
        assertNotNull(accountLinkResponse);
        assertTrue(expectedResponse.getStatus().equals("both"));
        assertTrue(accountLinkResponse.getStatus().equals("both"));
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
    private AccountLink getAccountsLinkObject() {
        List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        List<CustomData> customDataList = new ArrayList<>();

        sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "2999"));

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
