package com.mobilemoney.recurringpayment;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.merchantpayment.model.TransactionResponse;
import com.mobilemoney.recurringpayment.model.DebitMandate;
import com.mobilemoney.recurringpayment.model.DebitMandateResponse;
import com.mobilemoney.recurringpayment.model.Party;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecurringPaymentTest {
    private static final String CONSUMER_KEY = "59vthmq3f6i15v6jmcjskfkmh";
    private static final String CONSUMER_SECRET = "ef8tl4gihlpfd7r8jpc1t1nda33q5kcnn32cj375lq6mg2nv7rb";
    private static final String API_KEY = "oVN89kXyTx1cKT3ZohH7h6foEmQmjqQm3OK2U8Ue";
    private static final String CALLBACK_URL = "https://0b11de7c-a436-4932-a947-aec37cb63408.mock.pstmn.io/mobilemoneymock/cb/transaction/type/merchantpay";

    @Test
    @DisplayName("Setup Recurring Payment Test Success")
    void createAccountDebitMandateTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Setup Recurring Payment Test Failure")
    void createAccountDebitMandateTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList)));
    }

    @Test
    @DisplayName("Setup Recurring Payment Using Polling Test Success")
    void createAccountDebitMandateUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createAccountDebitMandate(new Identifiers(identifierList));

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        DebitMandateResponse debitMandateResponse = mmClient.addRequest(recurringPaymentRequest)
                .viewAccountDebitMandate(new Identifiers(identifierList), sdkResponse.getObjectReference());

        assertNotNull(debitMandateResponse);
    }

    @Test
    @DisplayName("Take Recurring Payment Test Success")
    void createMerchantTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject("200.00", "RWF"));

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(CALLBACK_URL)
                .createMerchantTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Take Recurring Payment Test Failure")
    void createMerchantTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject("00.00", "RWF"));

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(recurringPaymentRequest).addCallBack(CALLBACK_URL).createMerchantTransaction());
    }

    @Test
    @DisplayName("Take Recurring Payment Using Polling Test Success")
    void createMerchantTransactionUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject("200.00", "RWF"));

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createMerchantTransaction();

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        TransactionResponse transactionResponse = mmClient.addRequest(recurringPaymentRequest).viewTransaction(sdkResponse.getObjectReference());

        assertNotNull(transactionResponse);
    }

    @Test
    @DisplayName("Recurring Payment Refund Test Success")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "2999"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createAccountDebitMandate(new Identifiers(identifierList));

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        debitPartyList.add(new DebitParty("accountid", "2999"));
        creditPartyList.add(new CreditParty("mandateReference", sdkResponse.getObjectReference()));

        Transaction transaction = new Transaction();
        transaction.setDebitParty(debitPartyList);
        transaction.setCreditParty(creditPartyList);
        transaction.setAmount("200.00");
        transaction.setCurrency("RWF");

        recurringPaymentRequest = new RecurringPaymentRequest();
        recurringPaymentRequest.setTransaction(transaction);
        sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(CALLBACK_URL)
                .createRefundTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Recurring Payment Reversal Test Success")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject("200.00", "RWF"));

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(CALLBACK_URL)
                .createMerchantTransaction();

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        sdkResponse =  mmClient.addRequest(new RecurringPaymentRequest())
                .addCallBack(CALLBACK_URL)
                .createReversal(txnRef);

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Get Service Provider Balance Test Success")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AccountBalance accountBalance = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }

    @Test
    @DisplayName("Retrieve Payments Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        TransactionFilter filter = new TransactionFilter();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "2000"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<TransactionResponse> transactions = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("Check Service Availability Test Success")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new RecurringPaymentRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY).addCallBackUrl(CALLBACK_URL);
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));
        String clientCorrelationId = recurringPaymentRequest.getClientCorrelationId();
        DebitMandateResponse debitMandateResponse = mmClient.addRequest(recurringPaymentRequest)
                .viewResponse(clientCorrelationId, DebitMandateResponse.class);

        assertNotNull(debitMandateResponse);
    }

    /***
     *
     * @return
     */
    private DebitMandate getAccountDebitMandateObject() {
        DebitMandate debitMandate = new DebitMandate();
        List<Party> payee = new ArrayList<>();
        List<CustomData> customData = new ArrayList<>();

        payee.add(new Party("accountid", "2999"));
        customData.add(new CustomData("keytest", "keyvalue"));

        debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
        debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
        debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
        debitMandate.setCurrency("GBP");
        debitMandate.setAmountLimit("1000.00");
        debitMandate.setNumberOfPayments(2);
        debitMandate.setFrequencyType("sixmonths");
        debitMandate.setPayee(payee);
        debitMandate.setCustomData(customData);

        return debitMandate;
    }

    /***
     *
     * @return
     */
    private DebitMandate getAccountDebitMandateFailedObject() {
        DebitMandate debitMandate = new DebitMandate();
        List<Party> payee = new ArrayList<>();
        List<CustomData> customData = new ArrayList<>();

        payee.add(new Party("accountid", "2999"));
        customData.add(new CustomData("keytest", "keyvalue"));

        debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
        debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
        debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
        debitMandate.setCurrency("GBP");
        debitMandate.setAmountLimit("000.00");
        debitMandate.setNumberOfPayments(2);
        debitMandate.setFrequencyType("sixmonths");
        debitMandate.setPayee(payee);
        debitMandate.setCustomData(customData);

        return debitMandate;
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
}
