package com.mobilemoney.unit.recurringpayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.Transactions;
import com.mobilemoney.common.request.CreateTransactionRequest;
import com.mobilemoney.recurringpayment.model.DebitMandate;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

public class RecurringPaymentTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Merchant Transaction Client Correlation Id Test Success")
    void merchantTransactionCorrelationIdTestSuccess()
            throws NoSuchFieldException, SecurityException, MobileMoneyException {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        recurringPaymentRequest.setTransaction(getTransactionObject());

        CreateTransactionRequest transactionReqMock = Mockito.mock(CreateTransactionRequest.class);
        FieldSetter.setField(recurringPaymentRequest,
                recurringPaymentRequest.getClass().getDeclaredField("createTransactionRequest"), transactionReqMock);

        recurringPaymentRequest.createMerchantTransaction();
        assertNotNull(recurringPaymentRequest.getClientCorrelationId());
    }

    @Test
    @DisplayName("Create Debit Mandate With Null Identifier Test Success")
    void createAccountDebitMandateNullIdentifierTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        assertThrows(MobileMoneyException.class, () -> recurringPaymentRequest.createAccountDebitMandate(null));
    }

    @Test
    @DisplayName("Create Debit Mandate With No Debit Mandate Test Success")
    void createAccountDebitMandateNoDebitMandateTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        assertThrows(MobileMoneyException.class,
                () -> recurringPaymentRequest.createAccountDebitMandate(new Identifiers(identifierList)));
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
    @DisplayName("Create Merchant Transaction Test Success")
    void merchantTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject());

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(recurringPaymentRequestSpy).createMerchantTransaction();

        AsyncResponse actualSdkResponse = recurringPaymentRequestSpy.createMerchantTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Debit Mandate Payload Test Success")
    void debitMandatePayLoadTestSuccess() {
        DebitMandate debitMandate = getAccountDebitMandateObject();
        String jsonString = debitMandate.toJSON();
        DebitMandate debitMandate1 = JSONFormatter.fromJSON(jsonString, DebitMandate.class);

        assertNotNull(jsonString);
        assertNotNull(debitMandate1);
        assertEquals(debitMandate.getAmountLimit(), debitMandate1.getAmountLimit());
    }

    @Test
    @DisplayName("Json String To Debit Mandate Object Test Success")
    void jsonToDebitMandateObjectTestSuccess() {
        String debitMandateObjectString = "{\"currency\": \"USD\",\"startDate\": \"2018-07-03T10:43:27.405Z\",\"endDate\": \"2028-07-03T10:43:27.405Z\",\"requestDate\": \"2018-07-03T10:43:27.405Z\",\"frequencyType\": \"sixmonths\",\"amountLimit\": \"1000.00\",\"numberOfPayments\": 2,\"payee\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";
        DebitMandate debitMandate = JSONFormatter.fromJSON(debitMandateObjectString, DebitMandate.class);

        assertNotNull(debitMandate);
        assertEquals(debitMandate.getAmountLimit(), "1000.00");
    }

    @Test
    @DisplayName("Json String To Debit Mandate Object Test Failure")
    void jsonToDebitMandateObjectTestFailure() {
        String debitMandateObjectString = "{\"currency\": \"USD\",\"startDate\": \"2018-07-03T10:43:27.405Z\",\"endDate\": \"2028-07-03T10:43:27.405Z\",\"requestDate\": \"2018-07-03T10:43:27.405Z\",\"frequencyType\": \"sixmonths\",\"amountLimit\": \"1000.00\",\"numberOfPayments\": 2,\"payee\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";
        DebitMandate debitMandate = JSONFormatter.fromJSON(debitMandateObjectString, DebitMandate.class);

        assertNotNull(debitMandate);
        assertNotEquals(debitMandate.getAmountLimit(), "1001.00");
    }

    @Test
    @DisplayName("Create Debit Mandate Test Success")
    void createAccountDebitMandateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(recurringPaymentRequestSpy).createAccountDebitMandate(identifiers);

        AsyncResponse actualSdkResponse = recurringPaymentRequestSpy.createAccountDebitMandate(identifiers);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("View Debit Mandate With No Identifier Test Success")
    void viewAccountDebitMandateNoIdentifierTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> recurringPaymentRequest.viewAccountDebitMandate(null, ""));
    }

    @Test
    @DisplayName("View Debit Mandate With No Debit Mandate Reference Test Success")
    void viewAccountDebitMandateNoDebitMandateReferenceTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        assertThrows(MobileMoneyException.class,
                () -> recurringPaymentRequest.viewAccountDebitMandate(identifiers, ""));
    }

    @Test
    @DisplayName("View Debit Mandate Test Success")
    void viewAccountDebitMandateTestSuccess() throws MobileMoneyException {
        DebitMandate expectedResult = getAccountDebitMandateObject();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedResult).when(recurringPaymentRequestSpy).viewAccountDebitMandate(identifiers,
                "reference");

        DebitMandate actualResult = recurringPaymentRequestSpy.viewAccountDebitMandate(identifiers, "reference");

        assertNotNull(expectedResult);
        assertNotNull(actualResult);
        assertEquals(expectedResult.getAmountLimit(), actualResult.getAmountLimit());
        assertEquals(expectedResult.getCurrency(), actualResult.getCurrency());
    }

    @Test
    @DisplayName("View Transaction With No Reference Test Success")
    void viewTransactionWithNoReferenceTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> recurringPaymentRequest.viewTransaction(""));
    }

    @Test
    @DisplayName("View Transaction Test Success")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(recurringPaymentRequestSpy).viewTransaction("reference");

        Transaction actualTransaction = recurringPaymentRequestSpy.viewTransaction("reference");

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
    }

    @Test
    @DisplayName("View Account Transactions With No Identifier Test Success")
    void viewAccountTransactionsWithNoIdentifierTestSuccess() {
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> recurringPaymentRequest.viewAccountTransactions(null));
    }

    @Test
    @DisplayName("View Account Transactions Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
    	Transactions expectedList = getTransactionList();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedList).when(recurringPaymentRequestSpy).viewAccountTransactions(identifiers);

        Transactions actualList = recurringPaymentRequestSpy.viewAccountTransactions(identifiers);

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
    @DisplayName("Transaction Refund Test Success")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject());

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(recurringPaymentRequestSpy).createRefundTransaction();
        AsyncResponse actualSdkResponse = recurringPaymentRequestSpy.createRefundTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Transaction Reversal Test Success")
    void createReversalTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        Reversal reversal = new Reversal();

        reversal.setType("reversal");
        recurringPaymentRequest.setReversal(reversal);

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(recurringPaymentRequestSpy).createReversal("transactionReference");

        AsyncResponse actualSdkResponse = recurringPaymentRequestSpy.createReversal("transactionReference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response for Transaction")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(recurringPaymentRequestSpy).viewResponse("clientCorrelationId",
                Transaction.class);

        Transaction actualTransaction = recurringPaymentRequestSpy.viewResponse("clientCorrelationId",
                Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("View Request State Test Success")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(recurringPaymentRequestSpy).viewRequestState(SERVER_CORRELATION_ID);

        AsyncResponse actualSdkResponse = recurringPaymentRequestSpy.viewRequestState(SERVER_CORRELATION_ID);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Get Account Balance Test Success")
    void viewAccountBalanceTestSuccess() throws MobileMoneyException {
        Balance expectedBalance = getBalanceObject();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedBalance).when(recurringPaymentRequestSpy).viewAccountBalance(identifiers);

        Balance actualBalance = recurringPaymentRequestSpy.viewAccountBalance(identifiers);

        assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
    }

    @Test
    @DisplayName("Check Service Availability Test Success")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        RecurringPaymentRequest recurringPaymentRequestSpy = Mockito.spy(recurringPaymentRequest);

        Mockito.doReturn(expectedResponse).when(recurringPaymentRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = recurringPaymentRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
    }
	
	@Test
	@DisplayName("Check if URL is valid Test Success")
	void validateURLTestSuccess() {
		assertTrue(ResourceUtils.isValidURL("https://sample.com"));
		assertTrue(ResourceUtils.isValidURL("http://sample"));
	}
	
	@Test
	@DisplayName("Check if URL is valid Test Fail")
	void validateURLTestFail() {
		assertFalse(ResourceUtils.isValidURL("https:/sample.com"));
		assertFalse(ResourceUtils.isValidURL("https:sample.com"));
		assertFalse(ResourceUtils.isValidURL("https//sample.com"));
		assertFalse(ResourceUtils.isValidURL("htt://sample.com"));
		assertFalse(ResourceUtils.isValidURL("//sample.com"));
		assertFalse(ResourceUtils.isValidURL("https:"));
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
    private DebitMandate getAccountDebitMandateObject() {
        DebitMandate debitMandate = new DebitMandate();
        List<AccountIdentifier> payee = new ArrayList<>();
        List<CustomData> customData = new ArrayList<>();

        payee.add(new AccountIdentifier("walletid", "1"));
        customData.add(new CustomData("keytest", "keyvalue"));

        debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
        debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
        debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
        debitMandate.setCurrency("USD");
        debitMandate.setAmountLimit("1000.00");
        debitMandate.setNumberOfPayments(2);
        debitMandate.setFrequencyType("sixmonths");
        debitMandate.setPayee(payee);
        debitMandate.setCustomData(customData);

        return debitMandate;
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
