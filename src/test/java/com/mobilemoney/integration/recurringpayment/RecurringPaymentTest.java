package com.mobilemoney.integration.recurringpayment;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.recurringpayment.model.DebitMandate;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

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

public class RecurringPaymentTest {
	private static PropertiesLoader loader;

    @BeforeAll
    public static void init(){
        loader = new PropertiesLoader();
    }
    
    @Test
    @DisplayName("Setup Recurring Payment Test Success")
    void createAccountDebitMandateTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createAccountDebitMandate(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Setup Recurring Payment Test Failure")
    void createAccountDebitMandateTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList)));
    }

    @Test
    @DisplayName("Setup Recurring Payment Using Polling Test Success")
    void createAccountDebitMandateUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createAccountDebitMandate(new Identifiers(identifierList));
        
        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        DebitMandate debitMandateResponse = mmClient.addRequest(recurringPaymentRequest)
                .viewAccountDebitMandate(new Identifiers(identifierList), sdkResponse.getObjectReference());

        assertNotNull(debitMandateResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Take Recurring Payment Test Success")
    void createMerchantTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createMerchantTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Take Recurring Payment Test Failure")
    void createMerchantTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(recurringPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createMerchantTransaction());
    }

    @Test
    @DisplayName("Take Recurring Payment Using Polling Test Success")
    void createMerchantTransactionUsingPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createMerchantTransaction();

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        Transaction transactionResponse = mmClient.addRequest(recurringPaymentRequest).viewTransaction(sdkResponse.getObjectReference());

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
    @DisplayName("Recurring Payment Refund Test Success")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .setNotificationType(NotificationType.POLLING)
                .createAccountDebitMandate(new Identifiers(identifierList));

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        creditPartyList.add(new AccountIdentifier("mandateReference", sdkResponse.getObjectReference()));

        Transaction transaction = new Transaction();
        transaction.setDebitParty(debitPartyList);
        transaction.setCreditParty(creditPartyList);
        transaction.setAmount("16.00");
        transaction.setCurrency("USD");

        recurringPaymentRequest = new RecurringPaymentRequest();
        recurringPaymentRequest.setTransaction(transaction);
        sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createRefundTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Recurring Payment Reversal Test Success")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

        recurringPaymentRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createMerchantTransaction();

        sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        recurringPaymentRequest.setReversal(reversal);
        sdkResponse =  mmClient.addRequest(recurringPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createReversal(txnRef);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Get Service Provider Balance Test Success")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Retrieve Payments Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<Transaction> transactions = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
        if (transactions.size() > 0) {
        	assertNotNull(transactions.get(0).getTransactionReference());
            assertNotNull(transactions.get(0).getTransactionStatus());
            assertNotNull(transactions.get(0).getAmount());
            assertNotNull(transactions.get(0).getCurrency());
            assertNotNull(transactions.get(0).getCreditParty());
            assertNotNull(transactions.get(0).getDebitParty());
            assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transactions.get(0).getType()));
            assertTrue(transactions.get(0).getCreditParty().size() > 0);
            assertTrue(transactions.get(0).getDebitParty().size() > 0);
        }
    }

    @Test
    @DisplayName("Check Service Availability Test Success")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new RecurringPaymentRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        recurringPaymentRequest.setDebitMandate(getAccountDebitMandateObject());

        AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));
        String clientCorrelationId = recurringPaymentRequest.getClientCorrelationId();
        DebitMandate debitMandateResponse = mmClient.addRequest(recurringPaymentRequest)
                .viewResponse(clientCorrelationId, DebitMandate.class);

        assertNotNull(debitMandateResponse);
        assertNotNull(debitMandateResponse.getMandateReference());
    }

    /***
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

    /***
     *
     * @return
     */
    private DebitMandate getAccountDebitMandateFailedObject() {
        DebitMandate debitMandate = new DebitMandate();
        List<AccountIdentifier> payee = new ArrayList<>();
        List<CustomData> customData = new ArrayList<>();

        payee.add(new AccountIdentifier("walletid", "1"));
        customData.add(new CustomData("keytest", "keyvalue"));

        debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
        debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
        debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
        debitMandate.setCurrency("USD");
        debitMandate.setAmountLimit("000.00");
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
