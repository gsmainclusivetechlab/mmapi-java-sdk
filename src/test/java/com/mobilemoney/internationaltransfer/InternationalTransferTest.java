package com.mobilemoney.internationaltransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.*;
import com.mobilemoney.internationaltransfer.model.*;
import com.mobilemoney.internationaltransfer.request.InternationalTransferRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternationalTransferTest {
    private static final String CONSUMER_KEY = "59vthmq3f6i15v6jmcjskfkmh";
    private static final String CONSUMER_SECRET = "ef8tl4gihlpfd7r8jpc1t1nda33q5kcnn32cj375lq6mg2nv7rb";
    private static final String API_KEY = "oVN89kXyTx1cKT3ZohH7h6foEmQmjqQm3OK2U8Ue";
    private static final String CALLBACK_URL = "https://0b11de7c-a436-4932-a947-aec37cb63408.mock.pstmn.io/mobilemoneymock/cb/transaction/type/merchantpay";

    @Test
    @DisplayName("Request a International Transfer Quotation Success")
    void internationalTransferQuotationRequestTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        internationalTransferRequest.setQuotation(createQuotationObject());

        AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createQuotation();
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Initiate International Transfer Success")
    void initiateInternationalTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        internationalTransferRequest.setTransaction(createInternationalTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createInternationalTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Initiate International Transfer Failure")
    void initiateInternationalTransactionTestFailure() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        Transaction internationalTransaction = createInternationalTransactionObject();
        internationalTransaction.setAmount("00.00");

        /*List<AccountIdentifier> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();

        debitPartyList.add(new AccountIdentifier("walletid", "1"));
        creditPartyList.add(new CreditParty("walletid", "1"));

        internationalTransaction.setCreditParty(creditPartyList);
        internationalTransaction.setDebitParty(debitPartyList);*/

        internationalTransferRequest.setTransaction(internationalTransaction);

        assertThrows(MobileMoneyException.class, () -> mmClient.addRequest(internationalTransferRequest).addCallBack(CALLBACK_URL).createInternationalTransaction());
    }

    @Test
    @DisplayName("International Transfer Reversal Success")
    void internationalTransferReversal() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        internationalTransferRequest.setTransaction(createInternationalTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createInternationalTransaction();

        sdkResponse = mmClient.addRequest(internationalTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        Reversal reversal = new Reversal();
        reversal.setType("reversal");
        internationalTransferRequest.setReversal(reversal);
        sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack(CALLBACK_URL).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Retrieve Missing API Response")
    void viewResponseTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        internationalTransferRequest.setTransaction(createInternationalTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).createInternationalTransaction();

        String clientCorrelationId = internationalTransferRequest.getClientCorrelationId();
        Transaction transaction = mmClient.addRequest(internationalTransferRequest).viewResponse(clientCorrelationId, Transaction.class);

        assertNotNull(transaction);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new InternationalTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        Balance balance = mmClient.addRequest(new InternationalTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(balance);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new InternationalTransferRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    /***
     *
     * @return
     */
    private Transaction createInternationalTransactionObject() {
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

        //debitPartyList.add(new AccountIdentifier("walletid", "1"));
        //creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
        debitPartyList.add(new AccountIdentifier("accountid", "2999"));
        creditPartyList.add(new AccountIdentifier("accountid", "2999"));

        transaction.setAmount("100.00");
        transaction.setCurrency("GBP");
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

        debitPartyList.add(new AccountIdentifier("accountid", "2999"));
        creditPartyList.add(new AccountIdentifier("accountid", "2000"));
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
