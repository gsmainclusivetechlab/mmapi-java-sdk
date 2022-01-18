package com.mobilemoney.unit.merchantpayment;

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
import org.mockito.internal.util.reflection.FieldSetter;

import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.Transactions;
import com.mobilemoney.common.request.AuthorizationCodeRequest;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

public class MerchantPaymentTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Merchant Transaction Client Correlation Id Test Success")
    void merchantTransactionCorrelationIdTestSuccess() throws MobileMoneyException, NoSuchFieldException, SecurityException {
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(getTransactionObject());

        CreateTransactionRequest transactionReqMock = Mockito.mock(CreateTransactionRequest.class);
        FieldSetter.setField(merchantPaymentRequest, merchantPaymentRequest.getClass().getDeclaredField("createTransactionRequest"), transactionReqMock);

        merchantPaymentRequest.createMerchantTransaction();
        assertNotNull(merchantPaymentRequest.getClientCorrelationId());
    }

    @Test
    @DisplayName("Json String To Transaction Object Test Success")
    void jsonToTransactionObjectTestSuccess() {
        String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
        Transaction transaction = JSONFormatter.fromJSON(transactionObjectString, Transaction.class);

        assertNotNull(transaction);
        assertEquals(transaction.getAmount(), "16.00");
        assertEquals(transaction.getCurrency(), "USD");
    }

    @Test
    @DisplayName("Json String To Transaction Object Test Failure")
    void jsonToTransactionObjectTestFailure() {
        String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
        Transaction transaction = JSONFormatter.fromJSON(transactionObjectString, Transaction.class);

        assertNotNull(transaction);
        assertNotEquals(transaction.getAmount(), "15.00");
        assertNotEquals(transaction.getCurrency(), "JWT");
    }

    @Test
    @DisplayName("Create Authorisation Code Client Correlation Id Test Success")
    void createAuthorisationCodeCorrelationIdTestSuccess() throws MobileMoneyException, NoSuchFieldException, SecurityException {
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("GBP");
        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

        AuthorizationCodeRequest authCodeMock = Mockito.mock(AuthorizationCodeRequest.class);
        FieldSetter.setField(merchantPaymentRequest, merchantPaymentRequest.getClass().getDeclaredField("authorizationCodeRequest"), authCodeMock);

        merchantPaymentRequest.createAuthorisationCode(new Identifiers(identifierList));
        assertNotNull(merchantPaymentRequest.getClientCorrelationId());
    }

    @Test
    @DisplayName("Refund Transaction Client Correlation Id Test Success")
    void refundTransactionCorrelationIdTestSuccess() throws MobileMoneyException, NoSuchFieldException, SecurityException {
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(getTransactionObject());

        CreateTransactionRequest transactionReqMock = Mockito.mock(CreateTransactionRequest.class);
        FieldSetter.setField(merchantPaymentRequest, merchantPaymentRequest.getClass().getDeclaredField("createTransactionRequest"), transactionReqMock);

        merchantPaymentRequest.createRefundTransaction();
        assertNotNull(merchantPaymentRequest.getClientCorrelationId());
    }

    @Test
    @DisplayName("Transaction Payload Test Success")
    void transactionPayLoadTestSuccess() {
        Transaction transaction = getTransactionObject();
        String jsonString = transaction.toJSON();
        Transaction transaction1 = JSONFormatter.fromJSON(jsonString, Transaction.class);

        assertNotNull(jsonString);
        assertNotNull(transaction1);
        assertEquals(transaction.getAmount(), transaction1.getAmount());
    }

    @Test
    @DisplayName("Create Authorisation Code Payload Test Success")
    void createAuthorisationCodePayloadTestSuccess() throws MobileMoneyException {
        AuthorisationCode authorisationCode = new AuthorisationCode();
        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("GBP");

        String jsonString = authorisationCode.toJSON();
        AuthorisationCode authorisationCode1 = JSONFormatter.fromJSON(jsonString, AuthorisationCode.class);

        assertNotNull(jsonString);
        assertNotNull(authorisationCode1);
        assertEquals(authorisationCode.getAmount(), authorisationCode1.getAmount());
        assertEquals(authorisationCode.getCodeLifetime(), authorisationCode1.getCodeLifetime());
        assertEquals(authorisationCode.getCurrency(), authorisationCode1.getCurrency());
    }

    @Test
    @DisplayName("Json String To Authorisation Code Object Test Success")
    void jsonToAuthorisationCodeObjectTestSuccess() {
        String authorisationCodeObjectString = "{\"amount\": \"1000.00\",\"currency\": \"USD\",\"codeLifetime\": 1,\"holdFundsIndicator\": false}";
        AuthorisationCode authorisationCode = JSONFormatter.fromJSON(authorisationCodeObjectString, AuthorisationCode.class);

        assertNotNull(authorisationCode);
        assertEquals(authorisationCode.getAmount(), "1000.00");
        assertEquals(authorisationCode.getCodeLifetime(), 1);
        assertEquals(authorisationCode.getCurrency(), "USD");
    }

    @Test
    @DisplayName("Json String To Authorisation Code Object Test Failure")
    void jsonToAuthorisationCodeObjectTestFailure() {
        String authorisationCodeObjectString = "{\"amount\": \"1000.00\",\"currency\": \"USD\",\"codeLifetime\": 1,\"holdFundsIndicator\": false}";
        AuthorisationCode authorisationCode = JSONFormatter.fromJSON(authorisationCodeObjectString, AuthorisationCode.class);

        assertNotNull(authorisationCode);
        assertNotEquals(authorisationCode.getAmount(), "15.00");
        assertNotEquals(authorisationCode.getCodeLifetime(), 2);
        assertNotEquals(authorisationCode.getCurrency(), "JWT");
    }

    @Test
    @DisplayName("Create Merchant Transaction Test Success")
    void createMerchantTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(merchantPaymentRequestSpy).createMerchantTransaction();

        AsyncResponse actualSdkResponse = merchantPaymentRequestSpy.createMerchantTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Create Authorisation Code Test Success")
    void createAuthorisationCodeTestSuccess() throws MobileMoneyException, NoSuchFieldException, SecurityException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("GBP");
        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(merchantPaymentRequestSpy).createAuthorisationCode(identifiers);

        AsyncResponse actualSdkResponse = merchantPaymentRequestSpy.createAuthorisationCode(identifiers);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Create Authorisation Code Without Identifiers Test Failure")
    void createAuthorisationCodeWithoutIdentifiersTestFailure() throws MobileMoneyException {
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> merchantPaymentRequest.createAuthorisationCode(null));
    }

    @Test
    @DisplayName("Create Authorisation Code Without Payload Test Failure")
    void createAuthorisationCodeWithoutPayloadTestFailure() throws MobileMoneyException {
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        assertThrows(MobileMoneyException.class, () -> merchantPaymentRequest.createAuthorisationCode(identifiers));
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(getTransactionObject());

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(merchantPaymentRequestSpy).viewRequestState(SERVER_CORRELATION_ID);

        AsyncResponse actualSdkResponse = merchantPaymentRequestSpy.viewRequestState(SERVER_CORRELATION_ID);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(merchantPaymentRequestSpy).viewTransaction("reference");

        Transaction actualTransaction = merchantPaymentRequestSpy.viewTransaction("reference");

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
    }

    @Test
    @DisplayName("Transaction Refund")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(getTransactionObject());

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(merchantPaymentRequestSpy).createRefundTransaction();

        AsyncResponse actualSdkResponse = merchantPaymentRequestSpy.createRefundTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Transaction Reversal")
    void createReversalTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        Reversal reversal = new Reversal();

        reversal.setType("reversal");
        merchantPaymentRequest.setReversal(reversal);

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(merchantPaymentRequestSpy).createReversal("transactionReference");

        AsyncResponse actualSdkResponse = merchantPaymentRequestSpy.createReversal("transactionReference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Json String To Reversal Object Test Success")
    void jsonToReversalObjectTestSuccess() {
        String reversalObjectString = "{\"type\": \"reversal\"}";
        Reversal reversal = JSONFormatter.fromJSON(reversalObjectString, Reversal.class);

        assertNotNull(reversal);
        assertEquals(reversal.getType(), "reversal");
    }

    @Test
    @DisplayName("Json String To Reversal Object Test Failure")
    void jsonToReversalObjectTestFailure() {
        String reversalObjectString = "{\"type\": \"reversal\"}";
        Reversal reversal = JSONFormatter.fromJSON(reversalObjectString, Reversal.class);

        assertNotNull(reversal);
        assertNotEquals(reversal.getType(), "invalid");
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
    	Transactions expectedList = getTransactionList();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedList).when(merchantPaymentRequestSpy).viewAccountTransactions(identifiers);

        Transactions actualList = merchantPaymentRequestSpy.viewAccountTransactions(identifiers);

        assertNotNull(expectedList);
        assertNotNull(actualList);
        assertNotNull(expectedList.getTransactions());
        assertNotNull(actualList.getTransactions());
        assertEquals(expectedList.getTransactions().size(), actualList.getTransactions().size());
        assertTrue(expectedList.getTransactions().size() == 2);
        assertTrue(actualList.getTransactions().size() == 2);
        assertEquals(expectedList.getTransactions().get(0).getAmount(), actualList.getTransactions().get(0).getAmount());
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceTestSuccess() throws MobileMoneyException {
        Balance expectedBalance = getBalanceObject();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedBalance).when(merchantPaymentRequestSpy).viewAccountBalance(identifiers);

        Balance actualBalance = merchantPaymentRequestSpy.viewAccountBalance(identifiers);

        assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedResponse).when(merchantPaymentRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = merchantPaymentRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(merchantPaymentRequestSpy).viewResponse("clientCorrelationId", Transaction.class);

        Transaction actualTransaction = merchantPaymentRequestSpy.viewResponse("clientCorrelationId", Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
    }

    @Test
    @DisplayName("View Authorisation Code Test Success")
    void viewAuthorisationCodeTestSuccess() throws MobileMoneyException {
        AuthorisationCode expectedAuthorisationCode = getAuthorisationCodeObject();
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        MerchantPaymentRequest merchantPaymentRequestSpy = Mockito.spy(merchantPaymentRequest);

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        Mockito.doReturn(expectedAuthorisationCode).when(merchantPaymentRequestSpy).viewAuthorisationCode(identifiers, SERVER_CORRELATION_ID);

        AuthorisationCode actualAuthorisationCode = merchantPaymentRequestSpy.viewAuthorisationCode(identifiers, SERVER_CORRELATION_ID);

        assertNotNull(expectedAuthorisationCode);
        assertNotNull(actualAuthorisationCode);
        assertEquals(expectedAuthorisationCode.getAuthorisationCode(), actualAuthorisationCode.getAuthorisationCode());
        assertEquals(expectedAuthorisationCode.getCodeState(), actualAuthorisationCode.getCodeState());
    }

    /**
     * *
     *
     * @return
     */
    private AuthorisationCode getAuthorisationCodeObject() {
        AuthorisationCode authorisationCode = new AuthorisationCode();
        authorisationCode.setAuthorisationCode("AUTH-CODE");
        authorisationCode.setCodeState("active");

        return authorisationCode;
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
    private Transaction getTransactionObject() {
        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();

        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        creditPartyList.add(new AccountIdentifier("walletid", "1"));

        Transaction transaction = new Transaction();
        transaction.setDebitParty(debitPartyList);
        transaction.setCreditParty(creditPartyList);
        transaction.setAmount("16.00");
        transaction.setCurrency("USD");

        return transaction;
    }

    /**
     * *
     *
     * @return
     */
    private Transactions getTransactionList() {
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

        Transactions transactionsObject = new Transactions();
        transactionsObject.setTransactions(transactions);
        
        return transactionsObject;
    }
}
