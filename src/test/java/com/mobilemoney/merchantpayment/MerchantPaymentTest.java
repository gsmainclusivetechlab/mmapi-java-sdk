package com.mobilemoney.merchantpayment;

import static org.junit.jupiter.api.Assertions.*;

import com.mobilemoney.common.model.AccountBalance;
import com.mobilemoney.common.model.AuthorisationCodeRequest;
import com.mobilemoney.common.model.AuthorisationCodeResponse;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.*;
import com.mobilemoney.merchantpayment.model.TransactionResponse;
import com.mobilemoney.merchantpayment.request.PaymentRequest;
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
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Create Merchant Transaction Test Failure")
    void createMerchantTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionFailedObject("200.00", "RWF"));

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(merchantPayment).createMerchantTransaction());
    }

    @Test
    @DisplayName("Obtain an Authorisation Code Success Test")
    void createAuthorisationCodeTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();
        AuthorisationCodeRequest authorisationCode = new AuthorisationCodeRequest();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("GBP");

        merchantPayment.setAuthorisationCodeRequest(authorisationCode);

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment)
                .addCallBack(CALLBACK_URL)
                .createAuthorisationCode(new Identifiers(identifierList));

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Payment with authorisation code success")
    void createMerchantTransactionWithAuthorisationCodeSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        PaymentRequest merchantPayment = new PaymentRequest();
        AuthorisationCodeRequest authorisationCode = new AuthorisationCodeRequest();
        TransactionFilter filter = new TransactionFilter();List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("GBP");

        merchantPayment.setAuthorisationCodeRequest(authorisationCode);
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createAuthorisationCode(new Identifiers(identifierList));

        sdkResponse = mmClient.addRequest(merchantPayment).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCodeResponse authorisationCodeResponse = mmClient.addRequest(merchantPayment).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        Transaction transaction = getTransactionObject("200.00", "RWF");
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        merchantPayment = new PaymentRequest();
        merchantPayment.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPayment).viewRequestState(sdkResponse.getServerCorrelationId());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPayment).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        TransactionResponse transaction = mmClient.addRequest(merchantPayment).viewTransaction(txnRef);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Transaction Refund")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createRefundTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Transaction Reversal")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPayment).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        sdkResponse =  mmClient.addRequest(new PaymentRequest()).createReversal(txnRef);

        assertNotNull(sdkResponse);
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

        List<TransactionResponse> transactions = mmClient.addRequest(new PaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new PaymentRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        PaymentRequest merchantPayment = new PaymentRequest();
        String clientCorrelationId = merchantPayment.getClientCorrelationId();

        merchantPayment.setTransaction(getTransactionObject("200.00", "RWF"));
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPayment).createMerchantTransaction();

        TransactionResponse transaction = mmClient.addRequest(merchantPayment).viewResponse(clientCorrelationId, TransactionResponse.class);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AccountBalance accountBalance = mmClient.addRequest(new PaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        identifierList.add(new IdentifierData("msisdn", "+123456789102345"));

        AccountBalance accountBalance = mmClient.addRequest(new PaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

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
