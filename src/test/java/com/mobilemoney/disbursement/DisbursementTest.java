package com.mobilemoney.disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.disbursement.model.BatchCompletion;
import com.mobilemoney.disbursement.model.BatchRejection;
import com.mobilemoney.disbursement.model.BatchTransaction;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DisbursementTest {
	private static PropertiesLoader loader;

    @BeforeAll
    public static void init(){
        loader = new PropertiesLoader();
    }
	
    @Test
    @DisplayName("Individual Disbursement Test Success")
    void createDisbursementTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(loader.get("CALLBACK_URL")).createDisbursementTransaction();
        
        assertNotNull(sdkResponse);
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
    @DisplayName("Bulk Disbursement Test Success")
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
    }

    @Test
    @DisplayName("Update Batch Transaction Status")
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
        List<BatchCompletion> completedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchCompletions(sdkResponse.getObjectReference());

        assertNotNull(completedTransactions);
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
        List<BatchRejection> rejectedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchRejections(sdkResponse.getObjectReference());

        assertNotNull(rejectedTransactions);
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
        sdkResponse = mmClient.addRequest(disbursementRequest).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
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
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<Transaction> transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
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
