package com.mobilemoney.unit.disbursement;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.disbursement.model.BatchCompletion;
import com.mobilemoney.disbursement.model.BatchRejection;
import com.mobilemoney.disbursement.model.BatchTransaction;
import com.mobilemoney.disbursement.request.DisbursementRequest;

public class DisbursementTest {
	private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

	@Test
	@DisplayName("Individual Disbursement Payload Test Success")
	void individualDisbursementPayloadTestSuccess() {
		Transaction transaction = getTransactionObject();
		String jsonString = transaction.toJSON();
		Transaction transaction1 = JSONFormatter.fromJSON(jsonString, Transaction.class);

		assertNotNull(jsonString);
		assertNotNull(transaction1);
		assertEquals(transaction.getAmount(), transaction1.getAmount());
	}

	@Test
	@DisplayName("Batch Transaction Payload Test Success")
	void batchTransactionPayloadTestSuccess() {
		BatchTransaction batchTransaction = new BatchTransaction();
		batchTransaction.setTransactions(getTransactionList());
		String jsonString = batchTransaction.toJSON();
		BatchTransaction batchTransaction1 = JSONFormatter.fromJSON(jsonString, BatchTransaction.class);

		assertNotNull(jsonString);
		assertNotNull(batchTransaction1);
		assertEquals(batchTransaction.getTransactions().size(), batchTransaction1.getTransactions().size());
		assertTrue(batchTransaction.getTransactions().size() == 2);
		assertTrue(batchTransaction1.getTransactions().size() == 2);
		assertEquals(batchTransaction.getTransactions().get(0).getAmount(),
				batchTransaction1.getTransactions().get(0).getAmount());
	}

	@Test
	@DisplayName("Individual Disbursement Without Payload Test Failure")
	void individualDisbursementWithoutPayloadTestFailure() throws MobileMoneyException {
		DisbursementRequest disbursementRequest = new DisbursementRequest();

		assertThrows(MobileMoneyException.class, () -> disbursementRequest.createDisbursementTransaction());
	}

	@Test
	@DisplayName("Batch Transaction Without Payload Test Failure")
	void batchTransactionWithoutPayloadTestFailure() throws MobileMoneyException {
		DisbursementRequest disbursementRequest = new DisbursementRequest();

		assertThrows(MobileMoneyException.class, () -> disbursementRequest.createBatchTransaction());
	}

	@Test
	@DisplayName("Individual Disbursement Test Success")
	void individualDisbursementTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		DisbursementRequest disbursementRequest = new DisbursementRequest();

		disbursementRequest.setTransaction(getTransactionObject());

		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedSdkResponse).when(disbursementRequestSpy).createDisbursementTransaction();

		AsyncResponse actualSdkResponse = disbursementRequestSpy.createDisbursementTransaction();

		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
	@DisplayName("Batch Transaction Test Success")
	void batchTransactionTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		BatchTransaction batchTransaction = new BatchTransaction();

		batchTransaction.setTransactions(getTransactionList());
		disbursementRequest.setBatchTransaction(batchTransaction);

		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedSdkResponse).when(disbursementRequestSpy).createBatchTransaction();

		AsyncResponse actualSdkResponse = disbursementRequestSpy.createBatchTransaction();

		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
	@DisplayName("View Current Status of a Batch Transaction")
	void viewBatchTransactionTestSuccess() throws MobileMoneyException {
		BatchTransaction expectedBatchTransaction = getBatchTransactionObject();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedBatchTransaction).when(disbursementRequestSpy).viewBatchTransaction("batchReference");

		BatchTransaction actualBatchTransaction = disbursementRequestSpy.viewBatchTransaction("batchReference");

		assertNotNull(expectedBatchTransaction);
		assertNotNull(actualBatchTransaction);
		assertEquals(expectedBatchTransaction.getBatchId(), actualBatchTransaction.getBatchId());
	}

	@Test
	@DisplayName("Update Batch Transaction Status")
	void updateBatchTransactionTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		List<PatchData> patchDataList = new ArrayList<>();

		patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));
		disbursementRequest.setPatchData(patchDataList);

		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedSdkResponse).when(disbursementRequestSpy).updateBatchTransaction("batchReference");

		AsyncResponse actualSdkResponse = disbursementRequestSpy.updateBatchTransaction("batchReference");
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}

	@Test
    @DisplayName("Get Completed Transactions of a Batch")
    void viewBatchCompletionsTestSuccess() throws MobileMoneyException {
		List<BatchCompletion> expectedCompletionList = getBatchCompletionList();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedCompletionList).when(disbursementRequestSpy).viewBatchCompletions("batchReference");
		
		List<BatchCompletion> actualCompletionList = disbursementRequestSpy.viewBatchCompletions("batchReference");
		
		assertNotNull(expectedCompletionList);
		assertNotNull(actualCompletionList);
		assertEquals(expectedCompletionList.size(), actualCompletionList.size());
    	assertTrue(expectedCompletionList.size() == 1);
    	assertTrue(actualCompletionList.size() == 1);
    	assertEquals(expectedCompletionList.get(0).getTransactionReference(), actualCompletionList.get(0).getTransactionReference());
	}
	
	@Test
    @DisplayName("Get Rejected Transactions of a Batch")
    void viewBatchRejectionsTestSuccess() throws MobileMoneyException {
		List<BatchRejection> expectedRejectionList = getBatchRejectionList();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);

		Mockito.doReturn(expectedRejectionList).when(disbursementRequestSpy).viewBatchRejections("batchReference");
		
		List<BatchRejection> actualRejectionList = disbursementRequestSpy.viewBatchRejections("batchReference");
		
		assertNotNull(expectedRejectionList);
		assertNotNull(actualRejectionList);
		assertEquals(expectedRejectionList.size(), actualRejectionList.size());
    	assertTrue(expectedRejectionList.size() == 1);
    	assertTrue(actualRejectionList.size() == 1);
    	assertEquals(expectedRejectionList.get(0).getTransactionReference(), actualRejectionList.get(0).getTransactionReference());
	}
	
	@Test
    @DisplayName("Get Details of a Specific Transaction")
    void viewTransactionTestSuccess() throws MobileMoneyException {
		Transaction expectedTransaction = getTransactionObject();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
		
		Mockito.doReturn(expectedTransaction).when(disbursementRequestSpy).viewTransaction("reference");
        
        Transaction actualTransaction = disbursementRequestSpy.viewTransaction("reference");
        
        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
        assertEquals(expectedTransaction.getCurrency(), actualTransaction.getCurrency());
	}
	
	@Test
    @DisplayName("Disbursement Reversal Test Success")
    void reversalTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		Reversal reversal = new Reversal();
		
        reversal.setType("reversal");
        disbursementRequest.setReversal(reversal);
        
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(disbursementRequestSpy).createReversal("transactionReference");
        
        AsyncResponse actualSdkResponse = disbursementRequestSpy.createReversal("transactionReference");
        
        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
    @DisplayName("Retrieve Missing API Response")
    void retrieveMissingResponse() throws MobileMoneyException {
		BatchTransaction expectedBatchTransaction = getBatchTransactionObject();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
		
		Mockito.doReturn(expectedBatchTransaction).when(disbursementRequestSpy).viewResponse("clientCorrelationId", BatchTransaction.class);
    	
		BatchTransaction actualBatchTransaction = disbursementRequestSpy.viewResponse("clientCorrelationId", BatchTransaction.class);
    	
		assertNotNull(expectedBatchTransaction);
		assertNotNull(actualBatchTransaction);
		assertEquals(expectedBatchTransaction.getBatchId(), actualBatchTransaction.getBatchId());
	}
	
	@Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
		List<Transaction> expectedList = getTransactionList();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
        DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
        
        Mockito.doReturn(expectedList).when(disbursementRequestSpy).viewAccountTransactions(identifiers);
    	
    	List<Transaction> actualList = disbursementRequestSpy.viewAccountTransactions(identifiers);
    	
    	assertNotNull(expectedList);
    	assertNotNull(actualList);
    	assertEquals(expectedList.size(), actualList.size());
    	assertTrue(expectedList.size() == 2);
    	assertTrue(actualList.size() == 2);
    	assertEquals(expectedList.get(0).getAmount(), actualList.get(0).getAmount());
	}
	
	@Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
		ServiceAvailability expectedResponse = getServiceAvailabilityObject();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
		
		Mockito.doReturn(expectedResponse).when(disbursementRequestSpy).viewServiceAvailability();
    	
    	ServiceAvailability actualResponse = disbursementRequestSpy.viewServiceAvailability();
    	
    	assertNotNull(expectedResponse);
    	assertNotNull(actualResponse);
    	assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
	}
	
	@Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
		Balance expectedBalance = getBalanceObject();
		DisbursementRequest disbursementRequest = new DisbursementRequest();
		List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
		DisbursementRequest disbursementRequestSpy = Mockito.spy(disbursementRequest);
		
		Mockito.doReturn(expectedBalance).when(disbursementRequestSpy).viewAccountBalance(identifiers);
    	
    	Balance actualBalance = disbursementRequestSpy.viewAccountBalance(identifiers);
    	
    	assertNotNull(expectedBalance);
    	assertNotNull(actualBalance);
    	assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
    	assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
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
    private ServiceAvailability getServiceAvailabilityObject() {
    	ServiceAvailability serviceAvailability = new ServiceAvailability();
    	serviceAvailability.setServiceStatus("available");
    	return serviceAvailability;
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
	private BatchTransaction getBatchTransactionObject() {
		BatchTransaction batchTransaction = new BatchTransaction();
		batchTransaction.setBatchId("batchId");
		return batchTransaction;
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
	private List<BatchCompletion> getBatchCompletionList() {
		List<BatchCompletion> batchCompletions = new ArrayList<BatchCompletion>();
		BatchCompletion batchCompletion = new BatchCompletion();
		batchCompletion.setTransactionReference("reference");
		batchCompletions.add(batchCompletion);
		return batchCompletions;
	}
	
	/***
	 * 
	 * @return
	 */
	private List<BatchRejection> getBatchRejectionList() {
		List<BatchRejection> batchRejections = new ArrayList<BatchRejection>();
		BatchRejection batchRejection = new BatchRejection();
		batchRejection.setTransactionReference("reference");
		batchRejections.add(batchRejection);
		return batchRejections;
	}
	
	/***
	 * 
	 * @return
	 */
	private List<Transaction> getTransactionList() {
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

		return transactions;
	}
}
