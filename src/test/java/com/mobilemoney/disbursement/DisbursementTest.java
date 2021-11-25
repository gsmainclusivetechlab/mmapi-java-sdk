package com.mobilemoney.disbursement;

import com.mobilemoney.common.model.AccountBalance;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.disbursement.model.DisbursementCompletedTransactionResponse;
import com.mobilemoney.disbursement.model.DisbursementRejectedTransactionResponse;
import com.mobilemoney.disbursement.model.DisbursementTransaction;
import com.mobilemoney.disbursement.model.DisbursementTransactionResponse;
import com.mobilemoney.disbursement.request.DisbursementRequest;
import com.mobilemoney.merchantpayment.model.TransactionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DisbursementTest {
    private static final String CONSUMER_KEY = "59vthmq3f6i15v6jmcjskfkmh";
    private static final String CONSUMER_SECRET = "ef8tl4gihlpfd7r8jpc1t1nda33q5kcnn32cj375lq6mg2nv7rb";
    private static final String API_KEY = "oVN89kXyTx1cKT3ZohH7h6foEmQmjqQm3OK2U8Ue";
    private static final String CALLBACK_URL = "https://0b11de7c-a436-4932-a947-aec37cb63408.mock.pstmn.io/mobilemoneymock/cb/transaction/type/merchantpay";

    @Test
    @DisplayName("Individual Disbursement Test Success")
    void createDisbursementTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createDisbursementTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Individual Disbursement Test Failure")
    void createDisbursementTransactionTestFailure() {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionFailedObject("200.00", "RWF"));

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createDisbursementTransaction());
    }

    @Test
    @DisplayName("Bulk Disbursement Test Success")
    void createBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject("200.00", "RWF");

        transactions.add(transaction);
        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Bulk Disbursement Test Failure")
    void createBatchTransactionTestFailure() {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionFailedObject("200.00", "RWF");

        transactions.add(transaction);
        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction());
    }

    @Test
    @DisplayName("View Current Status of a Batch Transaction")
    void viewBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject("200.00", "RWF");
        transactions.add(transaction);

        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        DisbursementTransactionResponse batchResponse = mmClient.addRequest(disbursementRequest).viewBatchTransaction(sdkResponse.getObjectReference());

        assertNotNull(batchResponse);
    }

    @Test
    @DisplayName("Update Batch Transaction Status")
    void updateBatchTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();
        List<PatchData> patchDataList = new ArrayList<>();

        Transaction transaction = getTransactionObject("200.00", "RWF");

        transactions.add(transaction);
        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));

        disbursementRequest = new DisbursementRequest();
        disbursementRequest.setPatchData(patchDataList);
        sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).updateBatchTransaction(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Completed Transactions of a Batch")
    void viewBatchCompletionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject("200.00", "RWF");

        transactions.add(transaction);
        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        List<DisbursementCompletedTransactionResponse> completedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchCompletions(sdkResponse.getObjectReference());

        assertNotNull(completedTransactions);
    }

    @Test
    @DisplayName("Get Rejected Transactions of a Batch")
    void viewBatchRejectionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        DisbursementTransaction disbursementTransaction = new DisbursementTransaction();
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction = getTransactionObject("200.00", "RWF");

        transactions.add(transaction);
        disbursementTransaction.setTransactions(transactions);
        disbursementRequest.setDisbursementTransaction(disbursementTransaction);

        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createBatchTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        List<DisbursementRejectedTransactionResponse> rejectedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchRejections(sdkResponse.getObjectReference());

        assertNotNull(rejectedTransactions);
    }

    @Test
    @DisplayName("Individual Disbursement Using Polling Test Success")
    void individualDisbursementUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).setNotificationType(NotificationType.POLLING).createDisbursementTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        TransactionResponse transactionResponse = mmClient.addRequest(disbursementRequest).viewTransaction(sdkResponse.getObjectReference());

        assertNotNull(transactionResponse);
    }

    @Test
    @DisplayName("Disbursement Reversal Test Success")
    void reversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();

        disbursementRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createDisbursementTransaction();

        sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        sdkResponse = mmClient.addRequest(new DisbursementRequest()).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void retrieveMissingResponse() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        DisbursementRequest disbursementRequest = new DisbursementRequest();
        String clientCorrelationId = disbursementRequest.getClientCorrelationId();

        disbursementRequest.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(CALLBACK_URL).createDisbursementTransaction();

        DisbursementTransactionResponse transaction = mmClient.addRequest(disbursementRequest).viewResponse(clientCorrelationId, DisbursementTransactionResponse.class);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        TransactionFilter filter = new TransactionFilter();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "2000"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<TransactionResponse> transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AccountBalance accountBalance = mmClient.addRequest(new DisbursementRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        identifierList.add(new IdentifierData("msisdn", "+123456789102345"));

        AccountBalance accountBalance = mmClient.addRequest(new DisbursementRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
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
     * @param amount
     * @param currency
     * @return
     */
    private Transaction getTransactionFailedObject(String amount, String currency) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCurrency(currency);

        return transaction;
    }
}
