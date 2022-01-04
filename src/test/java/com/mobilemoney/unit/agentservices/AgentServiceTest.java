package com.mobilemoney.unit.agentservices;

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

import com.mobilemoney.agentservices.model.Account;
import com.mobilemoney.agentservices.model.Identity;
import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Fees;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;

public class AgentServiceTest {
	private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();
	
	@Test
	@DisplayName("View Transaction Test Success")
	void viewTransactionTestSuccess() throws MobileMoneyException {
		Transaction expectedTransaction = getTransactionObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedTransaction).when(agentServiceRequestSpy).viewTransaction("REF-XYZ");
		
		Transaction actualTransaction = agentServiceRequestSpy.viewTransaction("REF-XYZ");
		assertNotNull(expectedTransaction);
		assertNotNull(actualTransaction);
		assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
	}
	
	@Test
    @DisplayName("Create Authorisation Code Test Success")
    void createAuthorisationCodeTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).createAuthorisationCode(identifiers);
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.createAuthorisationCode(identifiers);
		
		assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("View Request State Test Success")
	void viewRequestStateTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).viewRequestState(SERVER_CORRELATION_ID);
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.viewRequestState(SERVER_CORRELATION_ID);
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("View Authorisation Code Test Success")
	void viewAuthorisationCodeTestSuccess() throws MobileMoneyException {
		AuthorisationCode expectedAuthorisationCode = getAuthorisationCodeObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
		Mockito.doReturn(expectedAuthorisationCode).when(agentServiceRequestSpy).viewAuthorisationCode(identifiers, SERVER_CORRELATION_ID);
		
		AuthorisationCode actualAuthorisationCode = agentServiceRequestSpy.viewAuthorisationCode(identifiers, SERVER_CORRELATION_ID);
		
		assertNotNull(expectedAuthorisationCode);
		assertNotNull(actualAuthorisationCode);
		assertEquals(expectedAuthorisationCode.getAuthorisationCode(), actualAuthorisationCode.getAuthorisationCode());
		assertEquals(expectedAuthorisationCode.getCodeState(), actualAuthorisationCode.getCodeState());
	}
	
	@Test
	@DisplayName("View account name success")
	void viewAccountNameTestSuccess() throws MobileMoneyException {
		AccountHolderName expectedAccount = getAccountHolderNameObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
        Mockito.doReturn(expectedAccount).when(agentServiceRequestSpy).viewAccountName(identifiers);
        
        AccountHolderName actualAccount = agentServiceRequestSpy.viewAccountName(identifiers);
        
        assertNotNull(expectedAccount);
		assertNotNull(actualAccount);
		assertEquals(expectedAccount.getLei(), actualAccount.getLei());
	}
	
	@Test
	@DisplayName("Agent Service Reversal Success")
	void agentServiceTransferReversal() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		
		Reversal reversal = new Reversal();
        reversal.setType("reversal");
        agentServiceRequest.setReversal(reversal);
        
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).createReversal("transactionReference");
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.createReversal("transactionReference");
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
    @DisplayName("Obtain an Agent Balance")
    void obtainAgentBalanceTestSuccess() throws MobileMoneyException {
		Balance expectedBalance = getBalanceObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
		Mockito.doReturn(expectedBalance).when(agentServiceRequestSpy).viewAccountBalance(identifiers);
		
		Balance actualBalance = agentServiceRequestSpy.viewAccountBalance(identifiers);
		
		assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
	}

	@Test
    @DisplayName("Check for Service Availability")
    void checkForServiceAvailabilityTestSuccess() throws MobileMoneyException {
		ServiceAvailability expectedResponse = getServiceAvailabilityObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedResponse).when(agentServiceRequestSpy).viewServiceAvailability();
		
		ServiceAvailability actualResponse = agentServiceRequestSpy.viewServiceAvailability();
		
		assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
	}
	
	@Test
	@DisplayName("View AccountTransactions")
	void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
		List<Transaction> expectedList = getTransactionList();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
        TransactionFilter filter = new TransactionFilter();
        filter.setLimit(20);
        filter.setOffset(0);
        
		Mockito.doReturn(expectedList).when(agentServiceRequestSpy).viewAccountTransactions(identifiers, filter);
		
		List<Transaction> actualList = agentServiceRequestSpy.viewAccountTransactions(identifiers, filter);
		
		assertNotNull(expectedList);
		assertNotNull(actualList);
		assertEquals(expectedList.size(), actualList.size());
		assertTrue(expectedList.size() == 2);
		assertTrue(actualList.size() == 2);
		assertEquals(expectedList.get(0).getAmount(), actualList.get(0).getAmount());
	}
	
	@Test
	@DisplayName("View Response Test Success")
	void viewResponseTestSuccess() throws MobileMoneyException {
		Transaction expectedTransaction = getTransactionObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedTransaction).when(agentServiceRequestSpy).viewResponse("clientCorrelationId", Transaction.class);
		
		Transaction actualTransaction = agentServiceRequestSpy.viewResponse("clientCorrelationId", Transaction.class);
		
		assertNotNull(expectedTransaction);
		assertNotNull(actualTransaction);
		assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
	}
	
	@Test
	@DisplayName("Create Withdrawal Transaction Test Success")
	void createWithdrawalTransactionTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).createWithdrawalTransaction();
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.createWithdrawalTransaction();
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("Create Withdrawal Transaction Test Failure")
	void createWithdrawalTestFailure() {
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

		assertThrows(MobileMoneyException.class, () -> agentServiceRequest.createWithdrawalTransaction());
	}
	
	@Test
	@DisplayName("Create Deposit Transaction Test Success")
	void createDepositTransactionTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).createDepositTransaction();
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.createDepositTransaction();
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("Create Deposit Transaction Test Failure")
	void createDepositTestFailure() {
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

		assertThrows(MobileMoneyException.class, () -> agentServiceRequest.createDepositTransaction());
	}
	
	@Test
	@DisplayName("Create Account Test Success")
	void createAccountTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).createAccount();
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.createAccount();
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("Create Account Test Failure")
	void createAccountTestFailure() {
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

		assertThrows(MobileMoneyException.class, () -> agentServiceRequest.createAccount());
	}
	
	@Test
	@DisplayName("View Account Test Success")
	void viewAccountTestSuccess() throws MobileMoneyException {
		Account expecteResponse = getAccountObject();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
		Mockito.doReturn(expecteResponse).when(agentServiceRequestSpy).viewAccount(identifiers);
		
		Account actualResponse = agentServiceRequestSpy.viewAccount(identifiers);
		
		assertNotNull(expecteResponse);
		assertNotNull(actualResponse);
		assertEquals(expecteResponse.getAccountIdentifiers(), actualResponse.getAccountIdentifiers());
		assertEquals(expecteResponse.getIdentity(), actualResponse.getIdentity());
		assertEquals(expecteResponse.getAccountStatus(), actualResponse.getAccountStatus());
	}
	
	@Test
	@DisplayName("View Account Test Failure")
	void viewAccountTestFailure() {
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

		assertThrows(MobileMoneyException.class, () -> agentServiceRequest.viewAccount(null));
	}
	
	@Test
	@DisplayName("Update Account Identity Test Success")
	void updateAccountIdentityTestSuccess() throws MobileMoneyException {
		AsyncResponse expectedSdkResponse = getAsyncResponse();
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
		AgentServiceRequest agentServiceRequestSpy = Mockito.spy(agentServiceRequest);
		
		List<AccountIdentifier> identifierList = new ArrayList<>();
		identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);
        
        String identityId = "1";
        
		Mockito.doReturn(expectedSdkResponse).when(agentServiceRequestSpy).updateAccountIdentity(identifiers, identityId);
		
		AsyncResponse actualSdkResponse = agentServiceRequestSpy.updateAccountIdentity(identifiers, identityId);
		
		assertNotNull(expectedSdkResponse);
		assertNotNull(actualSdkResponse);
		assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
		assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
	}
	
	@Test
	@DisplayName("Update Account Identity Test Failure")
	void updateAccountIdentityTestFailure() {
		AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

		assertThrows(MobileMoneyException.class, () -> agentServiceRequest.updateAccountIdentity(null, null));
	}
	
	/**
     * *
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
	private AuthorisationCode getAuthorisationCodeObject() {
		AuthorisationCode authorisationCode = new AuthorisationCode();
		authorisationCode.setAuthorisationCode("AUTH-CODE");
		authorisationCode.setCodeState("active");

		return authorisationCode;
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
	
	/**
     * *
     *
     * @return
     */
    private Balance getBalanceObject() {
        Balance balance = new Balance();
        balance.setAccountStatus("available");
        balance.setAvailableBalance("100.00");
        return balance;
    }
	
	/**
     * *
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
    
    /***
	 * 
	 * @return
	 */
	private Account getAccountObject() {
		Account account = new Account();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("msisdn", "+44" + getPhoneNumber()));//mandatory

        List<Identity> identityList = new ArrayList<>();
        Identity identity = new Identity();

        List<CustomData> customDataList = new ArrayList<>();
        customDataList.add(new CustomData("test", "custom"));

//        List<CustomData> customDataList1 = new ArrayList<>();
//        customDataList.add(new CustomData("test", "custom1"));

        KYCInformation senderKyc = new KYCInformation();
        List<IdDocument> identificationList = new ArrayList<>();

        IdDocument idDocument = new IdDocument("passport");
        idDocument.setIdType("passport");
        idDocument.setIdNumber("111111");
        idDocument.setIssuer("ABC");
        idDocument.setIssuerPlace("DEF");
        idDocument.setIssuerCountry("AD");
        idDocument.setIssueDate("2018-11-20");
        idDocument.setExpiryDate("2018-11-20");
        identificationList.add(idDocument);

        Address address = new Address("AD");
        address.setAddressLine1("37");
        address.setAddressLine2("ABC Drive");
        address.setAddressLine3("string");
        address.setCity("Berlin");
        address.setStateProvince("string");
        address.setPostalCode("AF1234");
        address.setCountry("AD");

        Name kycSubject = new Name();
        kycSubject.setTitle("Mr");
        kycSubject.setFirstName("H");
        kycSubject.setMiddleName("I");
        kycSubject.setLastName("J");
        kycSubject.setFullName("H I J");
        kycSubject.setNativeName("string");

        senderKyc.setNationality("AD");
        senderKyc.setBirthCountry("AD");
        senderKyc.setOccupation("Miner");
        senderKyc.setEmployerName("string");
        senderKyc.setContactPhone("+447777777777");
        senderKyc.setGender("m");
        senderKyc.setDateOfBirth("2000-11-20");
        senderKyc.setEmailAddress("xyz@xyz.com");
        senderKyc.setIdDocument(identificationList);
        senderKyc.setPostalAddress(address);
        senderKyc.setSubjectName(kycSubject);

        identity.setIdentityId("1");//mandatory
        identity.setIdentityType("individual");//mandatory
        identity.setIdentityStatus("identityStatus");
        identity.setIdentityKyc(senderKyc);//mandatory
        identity.setAccountRelationship("accountholder");//mandatory
        identity.setKycVerificationStatus("verified");
        identity.setKycVerificationEntity("ABC Agent");
        identity.setKycLevel(1);
        identity.setCustomData(customDataList);
        identityList.add(identity);

//        List<Fees> feesList = new ArrayList<>();
//        Fees fees = new Fees();
//        fees.setFeeType("string");
//        fees.setFeeAmount("5.46");
//        fees.setFeeCurrency("AED");
//        feesList.add(fees);

        account.setAccountIdentifiers(identifierList);//mandatory
        account.setIdentity(identityList);//mandatory accountStatus
        account.setAccountStatus("available");//mandatory
//        account.setAccountType("string");
//        account.setCustomData(customDataList1);
//        account.setFees(feesList);
//        account.setRegisteringEntity("ABC Agent");
//        account.setRequestDate("2021-02-17T15:41:45.194Z");

        return account;
	}
	
    /**
     * *
     *
     * @return
     */
    private String getPhoneNumber() {
        String currentTimeMillis = Long.toString(System.currentTimeMillis());
        return currentTimeMillis.substring(currentTimeMillis.length() - 10);
    }
}
