# Create a Bill Transaction

`Here, createBillPayment(Identifiers identifiers, String billReferenc) creates a POST request to /accounts/{identifierType}/{identifier}/bills/{billReference}/payments`

> `Provided with a valid object representation, this endpoint allows for a new bill payment to be created for a specific account`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

List<Bill> bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

billPaymentRequest.setBillPayment(getBillPayment());
AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack("<Place your callback URL>").createBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());
``` 

### Callback Response Example

```java
{ 
  "serverCorrelationId":"06f8ae9f-e6bc-4f54-73a3-4d7df22a6434",
  "status":"pending",
  "notificationMethod":"callback",
  "objectReference":"19126",
  "pollLimit":100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "18c55e1a-9619-46c2-a442-240344832d22",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "19830",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewBillPayment.Readme.md">viewBillPayment()</a>
function to acquire the final representation of the bill pay object.
```