# View A Response

`Here, viewResponse(String clientCorrelationId, Class objectReference) creates a GET request to /responses/{clientCorrelationId}`

> `This endpoint returns a specific response.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
BillPay billPay = new BillPay();

billPay.setCurrency("<Currency>");
billPay.setAmountPaid("<Amount>");

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

List<Bill> bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

billPaymentRequest.setBillPayment(billPay);
AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack("<Place your callback URL>").createBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());

String clientCorrelationId = billPaymentRequest.getClientCorrelationId();
BillPay billPay = mmClient.addRequest(billPaymentRequest).viewResponse(clientCorrelationId, BillPay.class);
```

### Response Example

```java
{
  "serviceProviderPaymentReference": "string",
  "requestingOrganisationTransactionReference": "string",
  "customerReference": "string",
  "paymentType": "partialpayment",
  "billPaymentStatus": "string",
  "amountPaid": "15.00",
  "currency": "AED",
  "requestingOrganisation": "string",
  "supplementaryBillReferenceDetails": [
    {
      "paymentReferenceType": "string",
      "paymentReferenceValue": "string"
    }
  ],
  "serviceProviderComment": "string",
  "serviceProviderNotification": "string",
  "requestDate": "2021-12-28T07:22:36.324Z",
  "creationDate": "2021-12-28T07:22:36.324Z",
  "modificationDate": "2021-12-28T07:22:36.324Z",
  "customData": [
    {
      "key": "string",
      "value": "string"
    }
  ],
  "metadata": [
    {
      "key": "string",
      "value": "string"
    }
  ],
  "paidAmount": "15.21"
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewBillPayment.Readme.md">viewBillPayment()</a>
function to acquire the final representation of the bill pay object.