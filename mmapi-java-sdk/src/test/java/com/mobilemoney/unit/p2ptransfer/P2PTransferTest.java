package com.mobilemoney.unit.p2ptransfer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.Transactions;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

public class P2PTransferTest {
	private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

	@Test
	@DisplayName("P2P Transfer Quotation Payload Test Success")
	void quotationPayloadTestSuccess() {
		Quotation quotation = createQuotationObject();
		String jsonString = quotation.toJSON();
		Quotation quotation1 = JSONFormatter.fromJSON(jsonString, Quotation.class);

		assertNotNull(jsonString);
		assertNotNull(quotation1);
		assertEquals(quotation.getRequestAmount(), quotation1.getRequestAmount());
	}

	@Test
	@DisplayName("P2P Transaction Payload Success")
	void p2pTransactionPayLoadTestSuccess() {
		Transaction transaction = createP2PTransactionObject();
		String jsonString = transaction.toJSON();
		Transaction transaction1 = JSONFormatter.fromJSON(jsonString, Transaction.class);

		assertNotNull(jsonString);
		assertNotNull(transaction1);
		assertEquals(transaction.getAmount(), transaction1.getAmount());
	}

	@Test
	@DisplayName("P2P Transfer Quotation Without Payload Test Failure")
	void quotationWithoutPayloadTestFailure() {
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

		assertThrows(MobileMoneyException.class, () -> p2PTransferRequest.createQuotation());
	}

	@Test
	@DisplayName("P2P Transaction Without Payload Failure")
	void p2pTransactionWithoutPayLoadTestFailure() {
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

		assertThrows(MobileMoneyException.class, () -> p2PTransferRequest.createTransferTransaction());
	}

	@Test
	@DisplayName("Request a P2P Transfer Quotation Success")
	void p2pTransferQuotationRequestTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		p2PTransferRequest.setQuotation(createQuotationObject());

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedSdkResponse).when(p2PTransferRequestSpy).createQuotation();

		AsyncResponse actualSdkResponse = p2PTransferRequestSpy.createQuotation();

		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
	@DisplayName("Initiate P2P Transfer Success")
	void initiateP2PTransactionTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		p2PTransferRequest.setTransaction(createP2PTransactionObject());

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedSdkResponse).when(p2PTransferRequestSpy).createTransferTransaction();

		AsyncResponse actualSdkResponse = p2PTransferRequestSpy.createTransferTransaction();

		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
	@DisplayName("Retrieve Missing API Response for P2P Transaction")
	void viewResponseTestSuccess() throws MobileMoneyException {
		Transaction expectedTransaction = createP2PTransactionObject();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedTransaction).when(p2PTransferRequestSpy).viewResponse("clientCorrelationId",
				Transaction.class);

		Transaction actualTransaction = p2PTransferRequestSpy.viewResponse("clientCorrelationId", Transaction.class);

		assertNotNull(expectedTransaction);
		assertNotNull(actualTransaction);
		assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
	}

	@Test
	@DisplayName("P2P Transfer Reversal Success")
	void p2pTransferReversal() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		Reversal reversal = new Reversal();

		reversal.setType("reversal");
		p2PTransferRequest.setReversal(reversal);

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedSdkResponse).when(p2PTransferRequestSpy).createReversal("transactionReference");

		AsyncResponse actualSdkResponse = p2PTransferRequestSpy.createReversal("transactionReference");

		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
	@DisplayName("Retrieve Merchant Payments")
	void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
		Transactions expectedList = getTransactionList();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("accountid", "15523"));
		Identifiers identifiers = new Identifiers(identifierList);

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedList).when(p2PTransferRequestSpy).viewAccountTransactions(identifiers);

		Transactions actualList = p2PTransferRequestSpy.viewAccountTransactions(identifiers);

		assertNotNull(expectedList);
		assertNotNull(actualList);
		assertNotNull(expectedList.getTransactions());
		assertNotNull(actualList.getTransactions());
		assertEquals(expectedList.getTransactions().size(), actualList.getTransactions().size());
		assertTrue(expectedList.getTransactions().size() == 2);
		assertTrue(actualList.getTransactions().size() == 2);
		assertEquals(expectedList.getTransactions().get(0).getAmount(),
		actualList.getTransactions().get(0).getAmount());
	}

	@Test
	@DisplayName("View account name success")
	void viewAccountNameTestSuccess() throws MobileMoneyException {
		AccountHolderName expectedAccount = getAccountHolderNameObject();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("accountid", "15523"));
		Identifiers identifiers = new Identifiers(identifierList);

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedAccount).when(p2PTransferRequestSpy).viewAccountName(identifiers);

		AccountHolderName actualAccount = p2PTransferRequestSpy.viewAccountName(identifiers);

		assertNotNull(expectedAccount);
		assertNotNull(actualAccount);
		assertEquals(expectedAccount.getLei(), actualAccount.getLei());
	}

	@Test
	@DisplayName("Check Service Availability")
	void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
		ServiceAvailability expectedResponse = getServiceAvailabilityObject();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedResponse).when(p2PTransferRequestSpy).viewServiceAvailability();

		ServiceAvailability actualResponse = p2PTransferRequestSpy.viewServiceAvailability();

		assertNotNull(expectedResponse);
		assertNotNull(actualResponse);
		assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
	}

	@Test
	@DisplayName("Get Merchant Balance")
	void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
		Balance expectedBalance = getBalanceObject();
		P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

		identifierList.add(new AccountIdentifier("accountid", "15523"));
		Identifiers identifiers = new Identifiers(identifierList);

		P2PTransferRequest p2PTransferRequestSpy = Mockito.spy(p2PTransferRequest);

		Mockito.doReturn(expectedBalance).when(p2PTransferRequestSpy).viewAccountBalance(identifiers);

		Balance actualBalance = p2PTransferRequestSpy.viewAccountBalance(identifiers);

		assertNotNull(expectedBalance);
		assertNotNull(actualBalance);
		assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
		assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
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

	/***
	 * 
	 * @return
	 */
	private AsyncResponse getAsyncResponse() {
		AsyncResponse asyncResponse = new AsyncResponse();

		asyncResponse.setServerCorrelationId(SERVER_CORRELATION_ID);
		asyncResponse.setStatus("pending");

		return asyncResponse;
	}

	/***
	 * 
	 * @return
	 */
	private Balance getBalanceObject() {
		Balance balance = new Balance();
		balance.setAccountStatus("available");
		balance.setAvailableBalance("100.00");
		return balance;
	}

	/***
	 * 
	 * @return
	 */
	private ServiceAvailability getServiceAvailabilityObject() {
		ServiceAvailability serviceAvailability = new ServiceAvailability();
		serviceAvailability.setServiceStatus("available");
		return serviceAvailability;
	}

	/***
	 * 
	 * @return
	 */
	private AccountHolderName getAccountHolderNameObject() {
		AccountHolderName accountHolderName = new AccountHolderName();
		accountHolderName.setLei("lei");
		return accountHolderName;
	}

	/***
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

	/***
	 *
	 * @return
	 */
	private Transaction createP2PTransactionObject() {
		Transaction transaction = new Transaction();
		KYCInformation senderKyc = new KYCInformation();
		Name kycSubject = new Name();
		RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
		Address address = new Address("GB");
		IdDocument idDocument = new IdDocument("nationalidcard");
		InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

		List<AccountIdentifier> debitPartyList = new ArrayList<>();
		List<AccountIdentifier> creditPartyList = new ArrayList<>();
		List<IdDocument> identificationList = new ArrayList<>();

		idDocument.setIdNumber("1234567");
		idDocument.setIssuer("UKPA");
		idDocument.setIssuerPlace("GB");
		idDocument.setIssuerCountry("GB");
		idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
		idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
		idDocument.setOtherIddescription("test");
		identificationList.add(idDocument);

		kycSubject.setTitle("Mr");
		kycSubject.setFirstName("Luke");
		kycSubject.setMiddleName("R");
		kycSubject.setLastName("Skywalker");
		kycSubject.setFullName("Luke R Skywalker");
		kycSubject.setNativeName("ABC");

		senderKyc.setNationality("GB");
		senderKyc.setBirthCountry("GB");
		senderKyc.setOccupation("Manager");
		senderKyc.setEmployerName("MFX");
		senderKyc.setContactPhone("+447125588999");
		senderKyc.setGender("m");
		senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
		senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
		senderKyc.setIdDocument(identificationList);
		senderKyc.setPostalAddress(address);
		senderKyc.setSubjectName(kycSubject);

		requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
		requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

		debitPartyList.add(new AccountIdentifier("walletid", "1"));
		creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));

		transaction.setAmount("16.00");
		transaction.setCurrency("USD");
		transaction.setInternationalTransferInformation(transferInformation);
		transaction.setSenderKyc(senderKyc);
		transaction.setRequestingOrganisation(requestingOrganisation);
		transaction.setCreditParty(creditPartyList);
		transaction.setDebitParty(debitPartyList);

		return transaction;
	}

	/***
	 *
	 * @return
	 */
	private Quotation createQuotationObject() {
		IdDocument idDocument = new IdDocument("nationalidcard");
		Address address = new Address("GB");
		Name kycSubject = new Name();
		KYCInformation senderKyc = new KYCInformation();

		List<AccountIdentifier> debitPartyList = new ArrayList<>();
		List<AccountIdentifier> creditPartyList = new ArrayList<>();
		List<CustomData> customDataList = new ArrayList<>();
		List<IdDocument> identificationList = new ArrayList<>();

		idDocument.setIdNumber("1234567");
		idDocument.setIssuer("UKPA");
		idDocument.setIssuerPlace("GB");
		idDocument.setIssuerCountry("GB");
		idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
		idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
		idDocument.setOtherIddescription("test");
		identificationList.add(idDocument);

		kycSubject.setTitle("Mr");
		kycSubject.setFirstName("Luke");
		kycSubject.setMiddleName("R");
		kycSubject.setLastName("Skywalker");
		kycSubject.setFullName("Luke R Skywalker");
		kycSubject.setNativeName("ABC");

		senderKyc.setNationality("GB");
		senderKyc.setBirthCountry("GB");
		senderKyc.setOccupation("Manager");
		senderKyc.setEmployerName("MFX");
		senderKyc.setContactPhone("+447125588999");
		senderKyc.setGender("m");
		senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
		senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
		senderKyc.setIdDocument(identificationList);
		senderKyc.setPostalAddress(address);
		senderKyc.setSubjectName(kycSubject);

		debitPartyList.add(new AccountIdentifier("walletid", "1"));
		creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
		customDataList.add(new CustomData("keytest", "keyvalue"));

		Quotation quotation = new Quotation("75.30", "RWF", creditPartyList, debitPartyList);
		quotation.setSubType("abc");
		quotation.setChosenDeliveryMethod("agent");
		quotation.setSendingServiceProviderCountry("AD");
		quotation.setOriginCountry("AD");
		quotation.setReceivingCountry("AD");
		quotation.setRequestDate("2018-07-03T11:43:27.405Z");
		quotation.setSenderKyc(senderKyc);
		quotation.setCustomData(customDataList);

		return quotation;
	}
}
