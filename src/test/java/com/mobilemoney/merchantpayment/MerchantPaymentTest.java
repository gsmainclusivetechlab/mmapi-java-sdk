package com.mobilemoney.merchantpayment;

import static org.junit.jupiter.api.Assertions.*;

import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.*;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MerchantPaymentTest {
    private static final String CONSUMER_KEY = "59vthmq3f6i15v6jmcjskfkmh";
    private static final String CONSUMER_SECRET = "ef8tl4gihlpfd7r8jpc1t1nda33q5kcnn32cj375lq6mg2nv7rb";
    private static final String API_KEY = "oVN89kXyTx1cKT3ZohH7h6foEmQmjqQm3OK2U8Ue";
    private static final String CALLBACK_URL = "https://0b11de7c-a436-4932-a947-aec37cb63408.mock.pstmn.io/mobilemoneymock/cb/transaction/type/merchantpay";

    @Test
    @DisplayName("Create Merchant Transaction Test Success")
    void createMerchantTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Create Merchant Transaction Test Failure")
    void createMerchantTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction());
    }

    @Test
    @DisplayName("Obtain an Authorisation Code Success Test")
    void createAuthorisationCodeTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("USD");

        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest)
                .addCallBack(CALLBACK_URL)
                .createAuthorisationCode(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Payment with authorisation code success")
    void createMerchantTransactionWithAuthorisationCodeSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("USD");

        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createAuthorisationCode(new Identifiers(identifierList));
        
        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCode authorisationCodeResponse = mmClient.addRequest(merchantPaymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        Transaction transaction = getTransactionObject();
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Transaction transaction = mmClient.addRequest(merchantPaymentRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Transaction Refund")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(CALLBACK_URL).createRefundTransaction();
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Transaction Reversal")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        merchantPaymentRequest.setReversal(reversal);
        sdkResponse =  mmClient.addRequest(merchantPaymentRequest).addCallBack(CALLBACK_URL).createReversal(txnRef);
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<Transaction> transactions = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new MerchantPaymentRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        String clientCorrelationId = merchantPaymentRequest.getClientCorrelationId();
        Transaction transaction = mmClient.addRequest(merchantPaymentRequest).viewResponse(clientCorrelationId, Transaction.class);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

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
