package com.mobilemoney.integration.agentservices;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        Transaction transactionResponse = mmClient.addRequest(agentServiceRequest).viewTransaction(sdkResponse.getObjectReference());

    }

//    @Test
//    @DisplayName("Customer-initiated Cash-out")
//    void customerInitiatedCashOutTestSuccess() throws MobileMoneyException {
//        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
//        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
//        agentServiceRequest.setTransaction(getTransactionObject());
//
//        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();
//    }
//
//    @Test
//    @DisplayName("Customer-initiated Cash-out Failiure")
//    void customerInitiatedCashOutTestFailiure() throws MobileMoneyException {
//        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
//        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
//        agentServiceRequest.setTransaction(getTransactionObjectFailure());
//
//        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction());
//    }

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
        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        AuthorisationCode authorisationCodeResponse = mmClient.addRequest(agentServiceRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

        Transaction transaction = getTransactionObject();
        transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());

        agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(transaction);

        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createWithdrawalTransaction();

    }

    @Test
    @DisplayName("Agent-initiated Customer Cash-in")
    void agentInitiatedCustomerCashInTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        AccountHolderName accountHolderName = mmClient.addRequest(new AgentServiceRequest()).viewAccountName(new Identifiers(identifierList));

        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setTransaction(getTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createDepositTransaction();
        //PUT request needed?
    }

    @Test
    @DisplayName("Cash-out Reversal")
    void cashOutReversalTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        agentServiceRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).createWithdrawalTransaction();
        //createWithdrawalTransaction or createDepositTransaction

        sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        agentServiceRequest.setReversal(reversal);
        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(txnRef);

    }

    @Test
    @DisplayName("Register a Customer Mobile Money Account")
    void registerCustomerMobileMoneyAccountTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setAccount(getRequestAccountObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

    }

    @Test
    @DisplayName("Verify a Customer’s KYC")
    void cerifyCustomerKYCTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setAccount(getRequestAccountObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).createAccount();

        List<AccountIdentifier> identifierList = new ArrayList<>();
        identifierList.add(new AccountIdentifier("walletid", "1"));//identityId=1
//      identifierList.add(new AccountIdentifier("accountid", "2000"));//identityId=105

        Account account = mmClient.addRequest(agentServiceRequest).viewAccount(new Identifiers(identifierList));

        String identityId = "1";
        
        List<PatchData> patchDataList = new ArrayList<>();
        patchDataList.add(new PatchData(OP.REPLACE.getOP(), "/kycVerificationStatus", Value.VERIFIED.getValue()));
//    	agentServiceRequest = new AgentServiceRequest();
        agentServiceRequest.setPatchData(patchDataList);
        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(loader.get("CALLBACK_URL")).updateAccountIdentity(new Identifiers(identifierList), identityId);

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

        filter.setLimit(20);
        filter.setOffset(20);
        List<Transaction> transactions1 = mmClient.addRequest(new AgentServiceRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

    }

    @Test
    @DisplayName("Check for Service Availability")
    void checkForServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new AgentServiceRequest()).viewServiceAvailability();
    }

    @Test
    @DisplayName("Retrieve a Missing API Response")
    void retrieveMissingAPIResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY")).addCallBackUrl(loader.get("CALLBACK_URL"));
        AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

        agentServiceRequest.setTransaction(getTransactionObject());
        AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).createWithdrawalTransaction();

        String clientCorrelationId = agentServiceRequest.getClientCorrelationId();
        Transaction transaction = mmClient.addRequest(agentServiceRequest).viewResponse(clientCorrelationId, Transaction.class);

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
