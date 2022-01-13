package com.mobilemoney.integration.disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.disbursement.model.BatchCompletions;
import com.mobilemoney.disbursement.model.BatchRejections;
import com.mobilemoney.disbursement.model.BatchTransaction;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisbursementTest {
	private static PropertiesLoader loader;

    @BeforeAll
    public static void setUp(){
        loader = new PropertiesLoader();
    }
	
    @Test
    @DisplayName("Individual Disbursement Transaction Test Success")
    void createDisbursementTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Individual Disbursement Transaction With Json Input Test Success")
    void createDisbursementTransactionWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
        disbursementRequest.setTransaction(transactionObjectString);
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Individual Disbursement Test Failure")
    void createDisbursementTransactionTestFailure() {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction());
    }

    @Test
    @DisplayName("Bulk Disbursement Transaction Test Success")
    void createBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Bulk Disbursement Transaction With Json Input Test Success")
    void createBatchTransactionWithJsonInputTestSuccess() throws MobileMoneyException {
    	MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        List<Transaction> transactions = new ArrayList<>();

        String batchTransactionJsonString = "{\"transactions\": [{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}]}";
        disbursementRequest.setBatchTransaction(batchTransactionJsonString);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Bulk Disbursement Test Failure")
    void createBatchTransactionTestFailure() {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionFailedObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction());
    }

    @Test
    @DisplayName("View Current Status of a Batch Transaction")
    void viewBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject();
        transactions.add(transaction);

        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        BatchTransaction batchResponse = mmClient.addRequest(disbursementRequest).viewBatchTransaction(sdkResponse.getObjectReference());

        assertNotNull(batchResponse);
        assertNotNull(batchResponse.getBatchId());
        assertNotNull(batchResponse.getApprovalDate());
        assertNotNull(batchResponse.getCompletionDate());
        assertTrue(Arrays.asList("created", "approved", "completed").contains(batchResponse.getBatchStatus()));
    }

    @Test
    @DisplayName("Update Batch Transaction Status Test Success")
    void updateBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();
        List<PatchData> patchDataList = new ArrayList<>();

        Transaction transaction = getTransactionObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));
        disbursementRequest = new DisbursementRequest();
        disbursementRequest.setPatchData(patchDataList);
        sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).updateBatchTransaction(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Update Batch Transaction Status With Json Input Test Success")
    void updateBatchTransactionWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        String patchDataJsonInput = "[{\"op\":\"replace\",\"path\":\"/batchStatus\",\"value\":\"approved\"}]";
        disbursementRequest = new DisbursementRequest();
        disbursementRequest.setPatchData(patchDataJsonInput);
        sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).updateBatchTransaction(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Get Completed Transactions of a Batch")
    void viewBatchCompletionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        BatchCompletions completedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchCompletions(sdkResponse.getObjectReference());

        assertNotNull(completedTransactions);
        assertNotNull(completedTransactions.getBatchCompletions());
    }

    @Test
    @DisplayName("Get Rejected Transactions of a Batch")
    void viewBatchRejectionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        BatchTransaction batchTransaction = new BatchTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject();

        transactions.add(transaction);
        batchTransaction.setTransactions(transactions);
        disbursementRequest.setBatchTransaction(batchTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        BatchRejections rejectedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchRejections(sdkResponse.getObjectReference());

        assertNotNull(rejectedTransactions);
        assertNotNull(rejectedTransactions.getBatchRejections());
    }

    @Test
    @DisplayName("Individual Disbursement Using Polling Test Success")
    void individualDisbursementUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).setNotificationType(NotificationType.POLLING).createDisbursementTransaction();
        
        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        Transaction transactionResponse = mmClient.addRequest(disbursementRequest).viewTransaction(sdkResponse.getObjectReference());

        assertNotNull(transactionResponse);
        assertNotNull(transactionResponse.getTransactionReference());
        assertNotNull(transactionResponse.getTransactionStatus());
        assertNotNull(transactionResponse.getAmount());
        assertNotNull(transactionResponse.getCurrency());
        assertNotNull(transactionResponse.getCreditParty());
        assertNotNull(transactionResponse.getDebitParty());
        assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transactionResponse.getType()));
        assertTrue(transactionResponse.getCreditParty().size() > 0);
        assertTrue(transactionResponse.getDebitParty().size() > 0);
    }

    @Test
    @DisplayName("Disbursement Reversal Test Success")
    void reversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        disbursementRequest.setReversal(reversal);
        sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Disbursement Reversal With Json Input Test Success")
    void reversalWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        String reversalJsonString = "{\"type\": \"reversal\"}";
        disbursementRequest.setReversal(reversalJsonString);
        sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void retrieveMissingResponse() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();

        String clientCorrelationId = disbursementRequest.getClientCorrelationId();
        BatchTransaction transaction = mmClient.addRequest(disbursementRequest).viewResponse(clientCorrelationId, BatchTransaction.class);

        assertNotNull(transaction);
        assertNotNull(transaction.getBatchId());
        assertNotNull(transaction.getApprovalDate());
        assertNotNull(transaction.getCompletionDate());
        assertTrue(Arrays.asList("created", "approved", "completed").contains(transaction.getBatchStatus()));
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("msisdn", "+44012345678"));
        filter.setLimit(10);
        filter.setOffset(0);

        Transactions transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
        assertNotNull(transactions.getTransactions());
        if (transactions.getTransactions().size() > 0) {
            assertNotNull(transactions.getTransactions().get(0).getTransactionReference());
            assertNotNull(transactions.getTransactions().get(0).getTransactionStatus());
            assertNotNull(transactions.getTransactions().get(0).getAmount());
            assertNotNull(transactions.getTransactions().get(0).getCurrency());
            assertNotNull(transactions.getTransactions().get(0).getCreditParty());
            assertNotNull(transactions.getTransactions().get(0).getDebitParty());
            assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transactions.getTransactions().get(0).getType()));
            assertTrue(transactions.getTransactions().get(0).getCreditParty().size() > 0);
            assertTrue(transactions.getTransactions().get(0).getDebitParty().size() > 0);
        }
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new DisbursementRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new DisbursementRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    /***
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

    /***
     *
     * @return
     */
    private Transaction getTransactionFailedObject() {
        Transaction transaction = new Transaction();
        transaction.setAmount("00.00");
        transaction.setCurrency("USD");

        return transaction;
    }
}
