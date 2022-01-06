package com.mobilemoney.integration.agentservices;

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

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.agentservices.model.Account;
import com.mobilemoney.agentservices.model.Identity;
import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.constants.OP;
import com.mobilemoney.base.constants.Value;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AccountHolderName;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Fees;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;

public class AgentServiceTest {

    static PropertiesLoader loader;

    @BeforeAll
    public static void init() {
        loader = new PropertiesLoader();
    }

    @Test
    @DisplayName("Agent-initiated Cash-out Success")
    void agentInitiatedCashOutTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Agent-initiated Cash-out With Json Input Success")
    void agentInitiatedCashOutWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
        agentServiceRequest.setTransaction(transactionObjectString);

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Agent-initiated Cash-out Failiure")
    void agentInitiatedCashOutTestFailiure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(getTransactionObjectFailure());

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction());
    }

    @Test
    @DisplayName("Agent-initiated Cash-out using the Polling Method")
    void agentInitiatedCashOutPollingTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).setNotificationType(NotificationType.POLLING).createWithdrawalTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "polling");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        Transaction transaction = mmClient.addRequest(agentServiceRequest).viewTransaction(sdkResponse.getObjectReference());

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
    @DisplayName("Customer Cash-out at an ATM using an Authorisation Code")
    void customerCashOutATMUsingAuthorisationTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        AuthorisationCode authorisationCode = new AuthorisationCode();
        authorisationCode.setCodeLifetime(1);
        authorisationCode.setAmount("1000.00");
        authorisationCode.setCurrency("USD");
        agentServiceRequest.setAuthorisationCodeRequest(authorisationCode);

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("walletid", "1"));

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAuthorisationCode(new Identifiers(identifierList));

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCode authorisationCodeResponse = mmClient.addRequest(agentServiceRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        assertNotNull(authorisationCodeResponse);
        assertNotNull(authorisationCodeResponse.getAuthorisationCode());
        assertNotNull(authorisationCodeResponse.getCodeState());
        assertTrue(Arrays.asList("active", "expired", "cancelled").contains(authorisationCodeResponse.getCodeState()));

        Transaction transaction = getTransactionObject();
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Customer Cash-out at an ATM using an Authorisation Code With Json Input Test Success")
    void customerCashOutATMUsingAuthorisationWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        String authorisationCodeJsonString = "{\"amount\": \"1000.00\",\"currency\": \"USD\",\"codeLifetime\": 1,\"holdFundsIndicator\": false}";
        agentServiceRequest.setAuthorisationCodeRequest(authorisationCodeJsonString);

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("walletid", "1"));

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAuthorisationCode(new Identifiers(identifierList));

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCode authorisationCodeResponse = mmClient.addRequest(agentServiceRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        assertNotNull(authorisationCodeResponse);
        assertNotNull(authorisationCodeResponse.getAuthorisationCode());
        assertNotNull(authorisationCodeResponse.getCodeState());
        assertTrue(Arrays.asList("active", "expired", "cancelled").contains(authorisationCodeResponse.getCodeState()));

        Transaction transaction = getTransactionObject();
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Agent-initiated Customer Cash-in")
    void agentInitiatedCustomerCashInTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        AccountHolderName accountHolderName = mmClient.addRequest(new AgentServiceRequest()).viewAccountName(new Identifiers(identifierList));

        assertNotNull(accountHolderName);

        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createDepositTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Cash-out Reversal")
    void cashOutReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        agentServiceRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).createWithdrawalTransaction();

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        agentServiceRequest.setReversal(reversal);
        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

    }

    @Test
    @DisplayName("Cash-out Reversal With Json Input Test Success")
    void cashOutReversalWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        agentServiceRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).createWithdrawalTransaction();

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        String reversalJsonString = "{\"type\": \"reversal\"}";
        agentServiceRequest.setReversal(reversalJsonString);
        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

    }

    @Test
    @DisplayName("Register a Customer Mobile Money Account")
    void registerCustomerMobileMoneyAccountTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setAccount(getRequestAccountObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Register a Customer Mobile Money Account With Json Input Test Success")
    void registerCustomerMobileMoneyAccountWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        String accountJsonString = "{\"accountIdentifiers\": [{\"key\": \"msisdn\",\"value\": \"+44" + getPhoneNumber() + "\"}],\"identity\": [{\"identityKyc\": {\"birthCountry\": \"AD\",\"contactPhone\": \"+447777777777\",\"dateOfBirth\": \"2000-11-20\",\"emailAddress\": \"xyz@xyz.com\",\"employerName\": \"string\",\"gender\": \"m\",\"nationality\": \"AD\",\"occupation\": \"Miner\",\"postalAddress\": {\"addressLine1\": \"37\",\"addressLine2\": \"ABC Drive\",\"addressLine3\": \"string\",\"city\": \"Berlin\",\"stateProvince\": \"string\",\"postalCode\": \"AF1234\",\"country\": \"AD\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"H\",\"middleName\": \"I\",\"lastName\": \"J\",\"fullName\": \"H I J\",\"nativeName\": \"string\"},\"idDocument\": [{\"idType\": \"passport\",\"idNumber\": \"111111\",\"issueDate\": \"2018-11-20\",\"expiryDate\": \"2018-11-20\",\"issuer\": \"ABC\",\"issuerPlace\": \"DEF\",\"issuerCountry\": \"AD\"}]},\"accountRelationship\": \"accountholder\",\"kycVerificationStatus\": \"verified\",\"kycVerificationEntity\": \"ABC Agent\",\"kycLevel\": 1,\"customData\": [{\"key\": \"test\",\"value\": \"custom\"},{\"key\": \"test\",\"value\": \"custom1\"}]}],\"accountType\": \"string\",\"customData\": [],\"fees\": [{\"feeType\": \"string\",\"feeAmount\": \"5.46\",\"feeCurrency\": \"AED\"}],\"registeringEntity\": \"ABC Agent\",\"requestDate\": \"2021-02-17T15:41:45.194Z\"}";
        agentServiceRequest.setAccount(accountJsonString);
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Verify a Customer’s KYC")
    void verifyCustomerKYCTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setAccount(getRequestAccountObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("walletid", "1"));//identityId=1
//        identifierList.add(new AccountIdentifier("accountid", "2000"));//identityId=105

        Account accountViewed = mmClient.addRequest(agentServiceRequest).viewAccount(new Identifiers(identifierList));

        assertNotNull(accountViewed);
        assertNotNull(accountViewed.getAccountIdentifiers());
        assertTrue(accountViewed.getAccountIdentifiers().size() > 0);
        if (accountViewed.getAccountIdentifiers().size() > 0) {
            assertNotNull(accountViewed.getAccountIdentifiers().get(0).getKey());
            assertNotNull(accountViewed.getAccountIdentifiers().get(0).getValue());
        }
        assertNotNull(accountViewed.getIdentity());

        String identityId = "0";

        if (accountViewed.getIdentity().size() > 0) {
            assertNotNull(accountViewed.getIdentity().get(0).getIdentityId());
            identityId = accountViewed.getIdentity().get(0).getIdentityId();
            assertNotNull(accountViewed.getIdentity().get(0).getIdentityType());
            assertNotNull(accountViewed.getIdentity().get(0).getIdentityKyc());
            assertNotNull(accountViewed.getIdentity().get(0).getAccountRelationship());
        }
        assertNotNull(accountViewed.getAccountStatus());

        List<PatchData> patchDataList = new ArrayList<>();
        patchDataList.add(new PatchData(OP.REPLACE.getOP(), "/kycVerificationStatus", Value.VERIFIED.getValue()));
//    	agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setPatchData(patchDataList);
        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).updateAccountIdentity(new Identifiers(identifierList), identityId);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Obtain an Agent Balance")
    void obtainAgentBalanceTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new AgentServiceRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Retrieve Transactions for an Agent")
    void retrieveTransactionsForAgentTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        TransactionFilter filter = new TransactionFilter();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        filter.setLimit(20);
        filter.setOffset(0);

        List<Transaction> transactions = mmClient.addRequest(new AgentServiceRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
        if (transactions.size() > 0) {
            assertNotNull(transactions.get(0).getTransactionReference());
            assertNotNull(transactions.get(0).getTransactionStatus());
            assertNotNull(transactions.get(0).getAmount());
            assertNotNull(transactions.get(0).getCurrency());
            assertNotNull(transactions.get(0).getCreditParty());
            assertNotNull(transactions.get(0).getDebitParty());
            assertTrue(transactions.get(0).getCreditParty().size() > 0);
            assertTrue(transactions.get(0).getDebitParty().size() > 0);
            assertTrue(Arrays.asList("billpay", "deposit", "disbursement", "transfer", "merchantpay", "inttransfer", "adjustment", "reversal", "withdrawal").contains(transactions.get(0).getType()));
        }

        filter.setLimit(20);
        filter.setOffset(20);
        List<Transaction> transactions1 = mmClient.addRequest(new AgentServiceRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

    }

    @Test
    @DisplayName("Check for Service Availability")
    void checkForServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new AgentServiceRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }

    @Test
    @DisplayName("Retrieve a Missing API Response")
    void retrieveMissingAPIResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));

        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        agentServiceRequest.setAccount(getRequestAccountObject());

        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

        String clientCorrelationId = agentServiceRequest.getClientCorrelationId();
        Account accountResponse = mmClient.addRequest(agentServiceRequest).viewResponse(clientCorrelationId, Account.class);

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertNotNull(accountResponse);
    }

    /**
     * *
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

    /**
     * *
     *
     * @return
     */
    private Transaction getTransactionObjectFailure() {
        List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<AccountIdentifier> creditPartyList = new ArrayList<>();

        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        creditPartyList.add(new AccountIdentifier("walletid", "1"));

        Transaction transaction = new Transaction();
        transaction.setDebitParty(debitPartyList);
        transaction.setCreditParty(creditPartyList);
//        transaction.setAmount("16.00");
        transaction.setCurrency("USD");

        return transaction;
    }

    /**
     * *
     *
     * @return
     */
    private Account getRequestAccountObject() {
        Account account = new Account();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("msisdn", "+44" + getPhoneNumber()));

        List<Identity> identityList = new ArrayList<>();
        Identity identity = new Identity();

        List<CustomData> customDataList = new ArrayList<>();
        customDataList.add(new CustomData("test", "custom"));

        List<CustomData> customDataList1 = new ArrayList<>();
        customDataList.add(new CustomData("test", "custom1"));

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
//        idDocument.setOtherIddescription("test");
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

        identity.setAccountRelationship("accountholder");
        identity.setCustomData(customDataList);
        identity.setIdentityKyc(senderKyc);
        identity.setKycLevel(1);
        identity.setKycVerificationEntity("ABC Agent");
        identity.setKycVerificationStatus("verified");
        identityList.add(identity);

        List<Fees> feesList = new ArrayList<>();
        Fees fees = new Fees();
        fees.setFeeType("string");
        fees.setFeeAmount("5.46");
        fees.setFeeCurrency("AED");
        feesList.add(fees);

        account.setAccountIdentifiers(identifierList);
        account.setIdentity(identityList);
        account.setAccountType("string");
        account.setCustomData(customDataList1);
        account.setFees(feesList);
        account.setRegisteringEntity("ABC Agent");
        account.setRequestDate("2021-02-17T15:41:45.194Z");

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
