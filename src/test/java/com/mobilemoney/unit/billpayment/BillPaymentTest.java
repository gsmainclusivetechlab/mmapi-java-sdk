package com.mobilemoney.unit.billpayment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.billpayment.model.Bill;
import com.mobilemoney.billpayment.model.BillPay;
import com.mobilemoney.billpayment.model.BillPayments;
import com.mobilemoney.billpayment.model.Bills;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.common.model.Transaction;

public class BillPaymentTest {

    private static final String SERVER_CORRELATION_ID = UUID.randomUUID().toString();

    @Test
    @DisplayName("Create Bill Transaction Payload Test Success")
    void createBillTransactionPayloadTestSuccess() {
        Transaction transaction = getTransactionObject();
        String jsonString = transaction.toJSON();
        Transaction transaction1 = JSONFormatter.fromJSON(jsonString, Transaction.class);

        assertNotNull(jsonString);
        assertNotNull(transaction1);
        assertEquals(transaction.getAmount(), transaction1.getAmount());
    }

    @Test
    @DisplayName("Create Bill Payment Payload Test Success")
    void createBillPaymentPayloadTestSuccess() {
        BillPay billPayment = getBillPayment();
        String jsonString = billPayment.toJSON();
        BillPay billPayment1 = JSONFormatter.fromJSON(jsonString, BillPay.class);

        assertNotNull(jsonString);
        assertNotNull(billPayment);
        assertEquals(billPayment.getAmountPaid(), billPayment1.getAmountPaid());
    }

    @Test
    @DisplayName("Json String To Bill Payment Object Test Success")
    void jsonToBillPayObjectTestSuccess() {
        String billPayObjectString = "{\"amountPaid\": \"16.00\",\"currency\": \"USD\"}";
        BillPay billPay = JSONFormatter.fromJSON(billPayObjectString, BillPay.class);

        assertNotNull(billPay);
        assertEquals(billPay.getAmountPaid(), "16.00");
    }

    @Test
    @DisplayName("Json String To Bill Payment Object Test Failure")
    void jsonToBillPayObjectTestFailure() {
        String billPayObjectString = "{\"amountPaid\": \"16.00\",\"currency\": \"USD\"}";
        BillPay billPay = JSONFormatter.fromJSON(billPayObjectString, BillPay.class);

        assertNotNull(billPay);
        assertNotEquals(billPay.getAmountPaid(), "18.00");
    }

    @Test
    @DisplayName("Create Bill Transaction Without Payload Test Failure")
    void createBillTransactionWithOutPayloadTestFailure() {
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> billPaymentRequest.createBillTransaction());
    }

    @Test
    @DisplayName("Create Bill Payment Without Identifier Test Failure")
    void createBillPaymentWithOutIdentifierTestFailure() {
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> billPaymentRequest.createBillPayment(null, null));
    }

    @Test
    @DisplayName("View Account Bills Without Identifier Test Failure")
    void viewAccountBillsWithOutIdentifierTestFailure() {
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> billPaymentRequest.viewAccountBills(null));
    }

    @Test
    @DisplayName("View Bill Payments Without Identifier Test Failure")
    void viewBillPaymentWithOutIdentifierTestFailure() {
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

        assertThrows(MobileMoneyException.class, () -> billPaymentRequest.viewBillPayment(null, null));
    }

    @Test
    @DisplayName("Create Bill Payment Without Bill Reference Test Failure")
    void createBillPaymentWithOutBillReferenceTestFailure() {
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));

        assertThrows(MobileMoneyException.class,
                () -> billPaymentRequest.createBillPayment(new Identifiers(identifierList), null));
    }

    @Test
    @DisplayName("Create Bill Transaction Test Success")
    void createBillTransactionTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(billPaymentRequestSpy).createBillTransaction();

        AsyncResponse actualSdkResponse = billPaymentRequestSpy.createBillTransaction();

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("Create Bill Payment Test Success")
    void createBillPaymentTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        Mockito.doReturn(expectedSdkResponse).when(billPaymentRequestSpy).createBillPayment(identifiers, "Reference");

        AsyncResponse actualSdkResponse = billPaymentRequestSpy.createBillPayment(identifiers, "Reference");

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("View Account Bills Test Success")
    void viewAccountBillsTestSuccess() throws MobileMoneyException {
        Bills expectedBillList = getBillList();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        Mockito.doReturn(expectedBillList).when(billPaymentRequestSpy).viewAccountBills(identifiers);

        Bills actualBillList = billPaymentRequestSpy.viewAccountBills(identifiers);

        assertNotNull(expectedBillList);
        assertNotNull(actualBillList);
        assertNotNull(expectedBillList.getBills());
        assertNotNull(actualBillList.getBills());
        assertEquals(expectedBillList.getBills().size(), actualBillList.getBills().size());
        assertEquals(expectedBillList.getBills().get(0).getBillReference(), actualBillList.getBills().get(0).getBillReference());
    }

    @Test
    @DisplayName("View Bill Payment Test Success")
    void viewBillPaymentTestSuccess() throws MobileMoneyException {
    	BillPayments expectedBillPaymentList = getBillPaymentList();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);
        List<AccountIdentifier> identifierList = new ArrayList<>();

        identifierList.add(new AccountIdentifier("walletid", "1"));
        Identifiers identifiers = new Identifiers(identifierList);

        Mockito.doReturn(expectedBillPaymentList).when(billPaymentRequestSpy).viewBillPayment(identifiers, "REF-XYZ");

        BillPayments actualBillPaymentList = billPaymentRequestSpy.viewBillPayment(identifiers, "REF-XYZ");

        assertNotNull(expectedBillPaymentList);
        assertNotNull(actualBillPaymentList);
        assertNotNull(expectedBillPaymentList.getBillPayments());
        assertNotNull(actualBillPaymentList.getBillPayments());
        assertEquals(expectedBillPaymentList.getBillPayments().size(), actualBillPaymentList.getBillPayments().size());
        assertEquals(expectedBillPaymentList.getBillPayments().get(0).getAmountPaid(), actualBillPaymentList.getBillPayments().get(0).getAmountPaid());
    }

    @Test
    @DisplayName("View Transaction Test Success")
    void viewTransactionTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(billPaymentRequestSpy).viewTransaction("REF-XYZ");

        Transaction actualTransaction = billPaymentRequestSpy.viewTransaction("REF-XYZ");

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("View Request State Test Success")
    void viewRequestStateTestSuccess() throws MobileMoneyException {
        AsyncResponse expectedSdkResponse = getAsyncResponse();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);

        Mockito.doReturn(expectedSdkResponse).when(billPaymentRequestSpy).viewRequestState(SERVER_CORRELATION_ID);

        AsyncResponse actualSdkResponse = billPaymentRequestSpy.viewRequestState(SERVER_CORRELATION_ID);

        assertNotNull(expectedSdkResponse);
        assertNotNull(actualSdkResponse);
        assertEquals(expectedSdkResponse.getServerCorrelationId(), actualSdkResponse.getServerCorrelationId());
        assertEquals(expectedSdkResponse.getStatus(), actualSdkResponse.getStatus());
    }

    @Test
    @DisplayName("View Response Test Success")
    void viewResponseTestSuccess() throws MobileMoneyException {
        Transaction expectedTransaction = getTransactionObject();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);

        Mockito.doReturn(expectedTransaction).when(billPaymentRequestSpy).viewResponse("clientCorrelationId",
                Transaction.class);

        Transaction actualTransaction = billPaymentRequestSpy.viewResponse("clientCorrelationId", Transaction.class);

        assertNotNull(expectedTransaction);
        assertNotNull(actualTransaction);
        assertEquals(expectedTransaction.getAmount(), actualTransaction.getAmount());
    }

    @Test
    @DisplayName("Check Service Availability Test Success")
    void viewServiceAvailabilityTestSuccess() throws MobileMoneyException {
        ServiceAvailability expectedResponse = getServiceAvailabilityObject();
        BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

        BillPaymentRequest billPaymentRequestSpy = Mockito.spy(billPaymentRequest);

        Mockito.doReturn(expectedResponse).when(billPaymentRequestSpy).viewServiceAvailability();

        ServiceAvailability actualResponse = billPaymentRequestSpy.viewServiceAvailability();

        assertNotNull(expectedResponse);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getServiceStatus(), actualResponse.getServiceStatus());
    }

    /**
     * *
     *
     * @return
     */
    private BillPayments getBillPaymentList() {
        List<BillPay> billPaymentList = new ArrayList<BillPay>();
        BillPay bill = new BillPay();

        bill.setCurrency("USD");
        bill.setAmountPaid("16.00");

        billPaymentList.add(bill);

        BillPayments billPayments = new BillPayments();
        billPayments.setBillPayments(billPaymentList);
        
        return billPayments;
    }

    /**
     * *
     *
     * @return
     */
    private Bills getBillList() {
        List<Bill> billList = new ArrayList<Bill>();
        Bill bill = new Bill();

        bill.setCurrency("USD");
        bill.setBillReference("REF-XYZ");

        billList.add(bill);
        
        Bills bills = new Bills();
        bills.setBills(billList);
        return bills;
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
    private BillPay getBillPayment() {
        BillPay billPayment = new BillPay();

        billPayment.setCurrency("USD");
        billPayment.setAmountPaid("16.00");

        return billPayment;

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
}
