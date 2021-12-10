package com.mobilemoney.p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.*;
import com.mobilemoney.internationaltransfer.model.Identification;
import com.mobilemoney.internationaltransfer.model.KYC;
import com.mobilemoney.internationaltransfer.model.PostalAddress;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.merchantpayment.model.TransactionResponse;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class P2PTransferTest {
    private static final String CONSUMER_KEY = "59vthmq3f6i15v6jmcjskfkmh";
    private static final String CONSUMER_SECRET = "ef8tl4gihlpfd7r8jpc1t1nda33q5kcnn32cj375lq6mg2nv7rb";
    private static final String API_KEY = "oVN89kXyTx1cKT3ZohH7h6foEmQmjqQm3OK2U8Ue";
    private static final String CALLBACK_URL = "https://0b11de7c-a436-4932-a947-aec37cb63408.mock.pstmn.io/mobilemoneymock/cb/transaction/type/merchantpay";

    @Test
    @DisplayName("Request a P2P Transfer Quotation Success")
    void p2pTransferQuotationRequestTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setQuotation(createQuotationObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createQuotation();
        
        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Initiate P2P Transfer Success")
    void initiateP2PTransactionTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createTransferTransaction();

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("P2P Transfer Reversal Success")
    void p2pTransferReversal() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

        p2PTransferRequest.setTransaction(createP2PTransactionObject());

        AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest)
                .addCallBack(CALLBACK_URL)
                .createTransferTransaction();

        sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());
        
        Transaction transaction = new Transaction();
        transaction.setType("reversal");
        p2PTransferRequest.setTransaction(transaction);
        sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack(CALLBACK_URL).createReversal(sdkResponse.getObjectReference());

        assertNotNull(sdkResponse);
    }

    @Test
    @DisplayName("Retrieve Merchant Payments")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        TransactionFilter filter = new TransactionFilter();
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "2000"));
        filter.setLimit(10);
        filter.setOffset(0);

        List<TransactionResponse> transactions = mmClient.addRequest(new P2PTransferRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);

        assertNotNull(transactions);
    }

    @Test
    @DisplayName("View account name success")
    void viewAccountNameTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("walletid", "1"));
        AccountNameResponse accountNameResponse = mmClient.addRequest(new P2PTransferRequest()).viewAccountName(new Identifiers(identifierList));
        assertNotNull(accountNameResponse);
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new P2PTransferRequest()).viewServiceAvailability();

        assertNotNull(serviceStatusResponse);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));

        AccountBalance accountBalance = mmClient.addRequest(new P2PTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }

    @Test
    @DisplayName("Get Account Balance")
    void viewAccountBalanceWithMultipleIdentifiersTestSuccess() throws MobileMoneyException {
        MMClient mmClient = new MMClient(CONSUMER_KEY, CONSUMER_SECRET, API_KEY);
        List<IdentifierData> identifierList = new ArrayList<>();

        identifierList.add(new IdentifierData("accountid", "15523"));
        identifierList.add(new IdentifierData("msisdn", "+123456789102345"));

        AccountBalance accountBalance = mmClient.addRequest(new P2PTransferRequest()).viewAccountBalance(new Identifiers(identifierList));

        assertNotNull(accountBalance);
    }

    /***
     *
     * @return
     */
    private Transaction createP2PTransactionObject() {
        Transaction transaction = new Transaction();
        KYC senderKyc = new KYC();
        KYCSubject kycSubject = new KYCSubject();
        RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
        PostalAddress postalAddress = new PostalAddress("GB");
        Identification identification = new Identification("nationalidcard");
        InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();
        List<Identification> identificationList = new ArrayList<>();

        identification.setIdNumber("1234567");
        identification.setIssuer("UKPA");
        identification.setIssuerPlace("GB");
        identification.setIssuerCountry("GB");
        identification.setIssueDate("2018-07-03T11:43:27.405Z");
        identification.setExpiryDate("2021-07-03T11:43:27.405Z");
        identification.setOtherIddescription("test");
        identificationList.add(identification);

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
        senderKyc.setPostalAddress(postalAddress);
        senderKyc.setSubjectName(kycSubject);

        requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

        debitPartyList.add(new DebitParty("walletid", "1"));
        creditPartyList.add(new CreditParty("msisdn", "+44012345678"));

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
        Identification identification = new Identification("nationalidcard");
        PostalAddress postalAddress = new PostalAddress("GB");
        KYCSubject kycSubject = new KYCSubject();
        KYC senderKyc = new KYC();

        List<DebitParty> debitPartyList = new ArrayList<>();
        List<CreditParty> creditPartyList = new ArrayList<>();
        List<CustomData> customDataList = new ArrayList<>();
        List<Identification> identificationList = new ArrayList<>();

        identification.setIdNumber("1234567");
        identification.setIssuer("UKPA");
        identification.setIssuerPlace("GB");
        identification.setIssuerCountry("GB");
        identification.setIssueDate("2018-07-03T11:43:27.405Z");
        identification.setExpiryDate("2021-07-03T11:43:27.405Z");
        identification.setOtherIddescription("test");
        identificationList.add(identification);

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
        senderKyc.setPostalAddress(postalAddress);
        senderKyc.setSubjectName(kycSubject);

        debitPartyList.add(new DebitParty("accountid", "2999"));
        creditPartyList.add(new CreditParty("accountid", "2999"));
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
