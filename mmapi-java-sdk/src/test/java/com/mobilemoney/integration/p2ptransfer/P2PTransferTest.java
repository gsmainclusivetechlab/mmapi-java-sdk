package com.mobilemoney.integration.p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.*;
import com.mobilemoney.config.PropertiesLoader;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class P2PTransferTest {
        private static PropertiesLoader loader;

    @BeforeAll
    public static void setUp(){
        loader = new PropertiesLoader();
    }

    @Test
    @DisplayName("Request a P2P Transfer Quotation Success")
    void p2pTransferQuotationRequestTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setQuotation(createQuotationObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createQuotation();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Request a P2P Transfer Quotation With Json Input Success")
    void p2pTransferQuotationRequestWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        String quotationJsonString = "{\"subType\": \"abc\",\"requestAmount\": \"75.30\",\"requestCurrency\": \"RWF\",\"chosenDeliveryMethod\": \"agent\",\"originCountry\": \"AD\",\"receivingCountry\": \"AD\",\"sendingServiceProviderCountry\": \"AD\",\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}],\"creditParty\": [{\"key\": \"accountid\",\"value\": \"2000\"}],\"debitParty\": [{\"key\": \"accountid\",\"value\": \"2999\"}]}";
        p2PTransferRequest.setQuotation(quotationJsonString);

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createQuotation();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Initiate P2P Transfer Success")
    void initiateP2PTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("Initiate P2P Transfer With Json Input Success")
    void initiateP2PTransactionWithJsonInputTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        String p2pTransactionJsonString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"internationalTransferInformation\": {\"originCountry\": \"GB\"},\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"debitParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"creditParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";
        p2PTransferRequest.setTransaction(p2pTransactionJsonString);

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("View Transaction Test Success")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        String txnRef = sdkResponse.getObjectReference();

        Transaction transaction = mmClient.addRequest(p2PTransferRequest).viewTransaction(txnRef);

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
    @DisplayName("View Quatation Test Success")
    void viewQuotationTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setQuotation(createQuotationObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createQuotation();

        sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        String qtnRef = sdkResponse.getObjectReference();
        if (qtnRef == null) qtnRef = "REF-1637057900018";

        Quotation quotation = mmClient.addRequest(p2PTransferRequest).viewQuotation(qtnRef);

        assertNotNull(quotation);
        assertNotNull(quotation.getQuotationReference());
        assertNotNull(quotation.getRequestAmount());
        assertNotNull(quotation.getRequestCurrency());
        assertNotNull(quotation.getCreditParty());
        assertNotNull(quotation.getDebitParty());
        assertTrue(quotation.getCreditParty().size() > 0);
        assertTrue(quotation.getDebitParty().size() > 0);
    }

    @Test
    @DisplayName("Retrieve Missing API Response for P2P Transaction")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        String clientCorrelationId = p2PTransferRequest.getClientCorrelationId();
        Transaction transaction = mmClient.addRequest(p2PTransferRequest).viewResponse(clientCorrelationId, Transaction.class);

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
    @DisplayName("P2P Transfer Reversal Success")
    void p2pTransferReversal() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        p2PTransferRequest.setReversal(reversal);
        sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
        assertNotNull(sdkResponse.getServerCorrelationId());
        assertEquals(sdkResponse.getNotificationMethod(), "callback");
        assertTrue(Arrays.asList("pending", "completed", "failed").contains(sdkResponse.getStatus()));
    }

    @Test
    @DisplayName("P2P Transfer Reversal With Json Input Success")
    void p2pTransferReversalWithJsonInput() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(loader.get("CALLBACK_URL"))
                .createTransferTransaction();

        sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());

        String reversalJsonString = "{\"type\": \"reversal\"}";
        p2PTransferRequest.setReversal(reversalJsonString);
        sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack(loader.get("CALLBACK_URL")).createReversal(sdkResponse.getObjectReference());

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

        identifierList.add(new AccountIdentifier("walletid", "1"));
        filter.setLimit(10);
        filter.setOffset(0);

        Transactions transactions = mmClient.addRequest(new P2PTransferRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

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
    @DisplayName("View account name success")
    void viewAccountNameTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        AccountHolderName accountHolderName = mmClient.addRequest(new P2PTransferRequest()).viewAccountName(new Identifiers(identifierList));
        
        assertNotNull(accountHolderName);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        ServiceAvailability serviceAvailability = mmClient.addRequest(new P2PTransferRequest()).viewServiceAvailability();

        assertNotNull(serviceAvailability);
        assertNotNull(serviceAvailability.getServiceStatus());
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new P2PTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(loader.get("CONSUMER_KEY"), loader.get("CONSUMER_SECRET"), loader.get("API_KEY"));
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("msisdn", "+44012345678"));
        //identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new P2PTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
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

        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        creditPartyList.add(new AccountIdentifier("walletid", "1"));
        customDataList.add(new CustomData("keytest", "keyvalue"));

        Quotation quotation = new Quotation("16.00", "USD", creditPartyList, debitPartyList);
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
