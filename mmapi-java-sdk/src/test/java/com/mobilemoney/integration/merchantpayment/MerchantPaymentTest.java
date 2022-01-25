package com.mobilemoney.integration.merchantpayment;

import static org.junit.jupiter.api.Assertions.*;

import com.mobilemoney.common.model.Balance;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.*;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MerchantPaymentTest {
	private static PropertiesLoader loader;

    @BeforeAll
    public static void setUp(){
        loader = new PropertiesLoader();
    }
    
    @Test
    @DisplayName("Create Merchant Transaction Test Success")
    void createMerchantTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createMerchantTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Create Merchant Transaction With Transaction String Test Success")
    void createMerchantTransactionWithTransactionStringTestSuccess() throws MobileMoneyException {
    	String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
    	
    	MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(transactionObjectString);

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createMerchantTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Create Merchant Transaction Test Failure")
    void createMerchantTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionFailedObject());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createMerchantTransaction());
    }

    @Test
    @DisplayName("Obtain an Authorisation Code Success Test")
    void createAuthorisationCodeTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("USD");

        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createAuthorisationCode(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Obtain an Authorisation Code With Json Input Test Success")
    void createAuthorisationCodeWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        String authorisationCodeJsonString = "{\"amount\": \"1000.00\",\"currency\": \"USD\",\"codeLifetime\": 1,\"holdFundsIndicator\": false}";
        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCodeJsonString);

        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createAuthorisationCode(new Identifiers(identifierList));
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Payment with authorisation code success")
    void createMerchantTransactionWithAuthorisationCodeSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
        AuthorisationCode authorisationCode = new AuthorisationCode();
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("USD");

        merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createAuthorisationCode(new Identifiers(identifierList));
        
        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCode authorisationCodeResponse = mmClient.addRequest(merchantPaymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        Transaction transaction = getTransactionObject();
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        merchantPaymentRequest = new MerchantPaymentRequest();
        merchantPaymentRequest.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createMerchantTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Get Status of a Specific Transaction")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).setNotificationType(NotificationType.POLLING).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Transaction transaction = mmClient.addRequest(merchantPaymentRequest).viewTransaction(txnRef);

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionReference());
        assertNotNull(transaction.getTransactionStatus());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCurrency());
        assertNotNull(transaction.getCreditParty());
        assertNotNull(transaction.getDebitParty());
        assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transaction.getType()));
        assertTrue(transaction.getCreditParty().size() > 0);
        assertTrue(transaction.getDebitParty().size() > 0);
    }

    @Test
    @DisplayName("Transaction Refund")
    void createRefundTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createRefundTransaction();
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Transaction Reversal Test Success")
    void createReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        merchantPaymentRequest.setReversal(reversal);
        sdkResponse =  mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Transaction Reversal With Json Input Test Success")
    void createReversalWithJsonInputTestSuccess() throws MobileMoneyException {
    	MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();
        String reversalJsonString = "{\"type\": \"reversal\"}";
        
        merchantPaymentRequest.setReversal(reversalJsonString);
        sdkResponse =  mmClient.addRequest(merchantPaymentRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);
        
        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }
    
    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        //identifierList.add(new AccountIdentifier("msisdn", "+44012345678"));
        identifierList.add(new AccountIdentifier("accountid", "2999"));
        filter.setLimit(10);
        filter.setOffset(0);

        Transactions transactions = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

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
        ServiceAvailability serviceAvailability = mmClient.addRequest(new MerchantPaymentRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

        merchantPaymentRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

        String clientCorrelationId = merchantPaymentRequest.getClientCorrelationId();
        Transaction transaction = mmClient.addRequest(merchantPaymentRequest).viewResponse(clientCorrelationId, Transaction.class);

        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionReference());
        assertNotNull(transaction.getTransactionStatus());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCurrency());
        assertNotNull(transaction.getCreditParty());
        assertNotNull(transaction.getDebitParty());
        assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transaction.getType()));
        assertTrue(transaction.getCreditParty().size() > 0);
        assertTrue(transaction.getDebitParty().size() > 0);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new MerchantPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

	@Test
	@DisplayName("Check if Callback URL is valid Test Fail")
	void validateCallbackURLTestFail() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
		MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
		
		merchantPaymentRequest.setTransaction(getTransactionObject());
		
		assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(merchantPaymentRequest).addCallBack("https:sample.com").createMerchantTransaction());
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
