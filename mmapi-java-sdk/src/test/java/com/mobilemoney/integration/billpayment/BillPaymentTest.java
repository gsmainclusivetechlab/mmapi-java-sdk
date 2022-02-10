package com.mobilemoney.integration.billpayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.billpayment.model.BillPay;
import com.mobilemoney.billpayment.model.BillPayments;
import com.mobilemoney.billpayment.model.Bills;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.config.PropertiesLoader;

public class BillPaymentTest {
	private static PropertiesLoader loader;

	@BeforeAll
	public static void setUp() {
		loader = new PropertiesLoader();
	}

	@Test
	@DisplayName("Create Bill Transaction Test Success")
	void createBillTransactionTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

		billPaymentRequest.setTransaction(getTransactionObject());

		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillTransaction();

		assertNotNull(sdkResponse);
		assertNotNull(sdkResponse.getServerCorrelationId());
		assertEquals(sdkResponse.getNotificationMethod(), "callback");
		assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
	}
	
	@Test
	@DisplayName("Create Bill Transaction With Json Input Test Success")
	void createBillTransactionWithJsonInputTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

		String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
		billPaymentRequest.setTransaction(transactionObjectString);

		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillTransaction();

		assertNotNull(sdkResponse);
		assertNotNull(sdkResponse.getServerCorrelationId());
		assertEquals(sdkResponse.getNotificationMethod(), "callback");
		assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
	}

	@Test
	@DisplayName("Create Bill Payment with Callback Test Success")
	void billPaymentWithCallBackTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		billPaymentRequest.setBillPayment(getBillPayment());
		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		assertNotNull(sdkResponse);
		assertNotNull(sdkResponse.getServerCorrelationId());
		assertEquals(sdkResponse.getNotificationMethod(), "callback");
		assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
	}
	
	@Test
	@DisplayName("Create Bill Payment with Callback With Json Input Test Success")
	void billPaymentWithCallBackWithJsonInputTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		String billPayJsonString = "{\"amountPaid\": \"16.00\",\"currency\": \"USD\"}";
		billPaymentRequest.setBillPayment(billPayJsonString);
		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		assertNotNull(sdkResponse);
		assertNotNull(sdkResponse.getServerCorrelationId());
		assertEquals(sdkResponse.getNotificationMethod(), "callback");
		assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
	}

	@Test
	@DisplayName("Create Bill Payment with Polling Test Success")
	void billPaymentWithPollingTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		billPaymentRequest.setBillPayment(getBillPayment());
		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).setNotificationType(NotificationType.POLLING)
				.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		assertNotNull(sdkResponse);
		assertNotNull(sdkResponse.getServerCorrelationId());
		assertEquals(sdkResponse.getNotificationMethod(), "polling");
		assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
	}

	@Test
	@DisplayName("View Bill Payment Test Success")
	void viewBillPaymentTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		billPaymentRequest.setBillPayment(getBillPayment());
		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest)
				.setNotificationType(NotificationType.POLLING)
				.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		sdkResponse = mmClient.addRequest(billPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

		BillPayments billPayments = mmClient.addRequest(billPaymentRequest)
				.viewBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		assertNotNull(billPayments);
		assertNotNull(billPayments.getBillPayments());
		if (billPayments.getBillPayments().size() > 0) {
			assertNotNull(billPayments.getBillPayments().get(0).getBillPaymentStatus());
			assertNotNull(billPayments.getBillPayments().get(0).getAmountPaid());
			assertNotNull(billPayments.getBillPayments().get(0).getCurrency());
		}
	}

	@Test
	@DisplayName("Create Bill Payment Test Failure")
	void createBillPaymentTestFailure() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		billPaymentRequest.setBillPayment(getBillPayment());

		assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(billPaymentRequest)
				.addCallBack(loader.get("CALLBACK_URL")).createBillPayment(new Identifiers(identifierList), "NULL"));
	}

	@Test
	@DisplayName("View Account Bills Test Success")
	void viewAccountBillsTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		assertNotNull(bills);
		assertNotNull(bills.getBills());
	}

	@Test
	@DisplayName("Retrieve Missing Bill Payment")
	void viewResponseTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("walletid", "1"));

		Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

		billPaymentRequest.setBillPayment(getBillPayment());
		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

		String clientCorrelationId = billPaymentRequest.getClientCorrelationId();

		BillPay billPay = mmClient.addRequest(billPaymentRequest).viewResponse(clientCorrelationId, BillPay.class);

		assertNotNull(billPay);
	}

	@Test
	@DisplayName("View Transaction Test Success")
	void viewTransactionTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

		billPaymentRequest.setTransaction(getTransactionObject());

		AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(loader.get("CALLBACK_URL"))
				.createBillTransaction();

		sdkResponse = mmClient.addRequest(billPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
		Transaction transactionResponse = mmClient.addRequest(billPaymentRequest)
				.viewTransaction(sdkResponse.getObjectReference());

		assertNotNull(transactionResponse);
		assertNotNull(transactionResponse.getTransactionReference());
		assertNotNull(transactionResponse.getTransactionStatus());
		assertNotNull(transactionResponse.getAmount());
		assertNotNull(transactionResponse.getCurrency());
		assertNotNull(transactionResponse.getCreditParty());
		assertNotNull(transactionResponse.getDebitParty());
		assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer",
				"adjustment", "reversal", "withdrawal").contains(transactionResponse.getType()));
		assertTrue(transactionResponse.getCreditParty().size() > 0);
		assertTrue(transactionResponse.getDebitParty().size() > 0);
	}

	@Test
	@DisplayName("Check Service Availability Test Success")
	void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		ServiceAvailability serviceAvailability = mmClient.addRequest(new BillPaymentRequest())
				.viewServiceAvailability();

		assertNotNull(serviceAvailability);
		assertNotNull(serviceAvailability.getServiceStatus());
	}

	@Test
	@DisplayName("Check if Callback URL is valid Test Fail")
	void validateCallbackURLTestFail() throws MobileMoneyException {
		MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"),
				loader.get("API_KEY"));
		BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

		billPaymentRequest.setTransaction(getTransactionObject());

		assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(billPaymentRequest)
				.addCallBack("https:sample.com").createBillTransaction());
	}

	/***
	 *
	 * @return
	 */
	private BillPay getBillPayment() {
		BillPay billPayment = new BillPay();

		billPayment.setCurrency("USD");
		billPayment.setAmountPaid("16.00");

		return billPayment;

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
}
