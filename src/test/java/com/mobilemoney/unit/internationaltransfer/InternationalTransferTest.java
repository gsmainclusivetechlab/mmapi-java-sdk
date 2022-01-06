package com.mobilemoney.unit.internationaltransfer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.internationaltransfer.request.InternationalTransferRequest;

public class InternationalTransferTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("International Transfer Quotation Payload Test Success")
    void quotationPayloadTestSuccess() {
        Quotation quotation = createQuotationObject();
        String jsonString = quotation.toJSON();
        Quotation quotation1 = JSONFormatter.fromJSON(jsonString, Quotation.class);

        assertNotNull(jsonString);
        assertNotNull(quotation1);
        assertEquals(quotation.getRequestAmount(), quotation1.getRequestAmount());
    }

    @Test
    @DisplayName("Json String To Quotation Object Test Success")
    void jsonToQuotationObjectTestSuccess() {
        String quotationObjectString = "{\"subType\": \"abc\",\"requestAmount\": \"75.30\",\"requestCurrency\": \"RWF\",\"chosenDeliveryMethod\": \"agent\",\"originCountry\": \"AD\",\"receivingCountry\": \"AD\",\"sendingServiceProviderCountry\": \"AD\",\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}],\"creditParty\": [{\"key\": \"accountid\",\"value\": \"2000\"}],\"debitParty\": [{\"key\": \"accountid\",\"value\": \"2999\"}]}";
        Quotation quotation = JSONFormatter.fromJSON(quotationObjectString, Quotation.class);

        assertNotNull(quotation);
        assertEquals(quotation.getRequestAmount(), "75.30");
    }

    @Test
    @DisplayName("Json String To Quotation Object Test Failure")
    void jsonToQuotationObjectTestFailure() {
        String quotationObjectString = "{\"subType\": \"abc\",\"requestAmount\": \"75.30\",\"requestCurrency\": \"RWF\",\"chosenDeliveryMethod\": \"agent\",\"originCountry\": \"AD\",\"receivingCountry\": \"AD\",\"sendingServiceProviderCountry\": \"AD\",\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}],\"creditParty\": [{\"key\": \"accountid\",\"value\": \"2000\"}],\"debitParty\": [{\"key\": \"accountid\",\"value\": \"2999\"}]}";
        Quotation quotation = JSONFormatter.fromJSON(quotationObjectString, Quotation.class);

        assertNotNull(quotation);
        assertNotEquals(quotation.getRequestAmount(), "65.30");
    }

    @Test
    @DisplayName("International Transaction Payload Success")
    void internationalTransactionPayLoadTestSuccess() {
        Transaction transaction = createInternationalTransactionObject();
        String jsonString = transaction.toJSON();
        Transaction transaction1 = JSONFormatter.fromJSON(jsonString, Transaction.class);

        assertNotNull(jsonString);
        assertNotNull(transaction1);
        assertEquals(transaction.getAmount(), transaction1.getAmount());
    }

    @Test
    @DisplayName("International Transfer Quotation Without Payload Test Failure")
    void quotationWithoutPayloadTestFailure() {
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        assertThrows(MobileMoneyException.class, () -> internationalTransferRequest.createQuotation());
    }

    @Test
    @DisplayName("International Transaction Without Payload Failure")
    void internationalTransactionWithoutPayLoadTestFailure() {
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

        assertThrows(MobileMoneyException.class, () -> internationalTransferRequest.createInternationalTransaction());
    }

    @Test
    @DisplayName("Request a International Transfer Quotation Success")
    void internationalTransferQuotationRequestTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        internationalTransferRequest.setQuotation(createQuotationObject());

        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedSdkResponse).when(internationalTransferRequestSpy).createQuotation();

        AsyncResponse actualSdkResponse = internationalTransferRequestSpy.createQuotation();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("International Transfer Reversal Success")
    void internationalTransferReversal() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        Reversal reversal = new Reversal();

        reversal.setType("reversal");
        internationalTransferRequest.setReversal(reversal);

        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedSdkResponse).when(internationalTransferRequestSpy)
                .createReversal("transactionReference");

        AsyncResponse actualSdkResponse = internationalTransferRequestSpy.createReversal("transactionReference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Retrieve Missing API Response for International Transaction")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = createInternationalTransactionObject();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedTransaction).when(internationalTransferRequestSpy).viewResponse("clientCorrelationId",
                Transaction.class);

        Transaction actualTransaction = internationalTransferRequestSpy.viewResponse("clientCorrelationId",
                Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("View Quatation Test Success")
    void viewQuotationTestSuccess() throws MobileMoneyException {
        Quotation expectedQuotation = createQuotationObject();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedQuotation).when(internationalTransferRequestSpy).viewQuotation("quotationReference");

        Quotation actualQuotation = internationalTransferRequestSpy.viewQuotation("quotationReference");

        assertNotNull(expectedQuotation);
        assertNotNull(actualQuotation);
        assertEquals(expectedQuotation.getRequestAmount(), actualQuotation.getRequestAmount());
    }

    @Test
    @DisplayName("Retrieve Transactions Test Success")
    void viewAccountTransactionsTestSuccess() throws MobileMoneyException {
        List<Transaction> expectedList = getTransactionList();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedList).when(internationalTransferRequestSpy).viewAccountTransactions(identifiers);

        List<Transaction> actualList = internationalTransferRequestSpy.viewAccountTransactions(identifiers);

        assertNotNull(expectedList);
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());
        assertTrue(expectedList.size() == 2);
        assertTrue(actualList.size() == 2);
        assertEquals(expectedList.get(0).getAmount(), actualList.get(0).getAmount());
    }

    @Test
    @DisplayName("Get Merchant Balance")
    void viewAccountBalanceWithSingleIdentifierTestSuccess() throws MobileMoneyException {
        Balance expectedBalance = getBalanceObject();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("accountid", "15523"));
        Identifiers identifiers = new Identifiers(identifierList);

        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedBalance).when(internationalTransferRequestSpy).viewAccountBalance(identifiers);

        Balance actualBalance = internationalTransferRequestSpy.viewAccountBalance(identifiers);

        assertNotNull(expectedBalance);
        assertNotNull(actualBalance);
        assertEquals(expectedBalance.getAccountStatus(), actualBalance.getAccountStatus());
        assertEquals(expectedBalance.getAvailableBalance(), actualBalance.getAvailableBalance());
    }

    @Test
    @DisplayName("Check Service Availability")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
        InternationalTransferRequest internationalTransferRequestSpy = Mockito.spy(internationalTransferRequest);

        Mockito.doReturn(expectedResponse).when(internationalTransferRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = internationalTransferRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
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

    /**
     * *
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

        // debitPartyList.add(new AccountIdentifier("walletid", "1"));
        // creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
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

    /**
     * *
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
