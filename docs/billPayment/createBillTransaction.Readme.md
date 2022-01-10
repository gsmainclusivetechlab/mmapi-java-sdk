# Create a Bill Transaction

`Here, createBillTransaction() creates a POST request to /transactions/type/billpay`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<Amount>");
transaction.setCurrency("<Currency>");

billPaymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).createBillTransaction();
``` 

Additionally, if you want to use transaction details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";

billPaymentRequest.setTransaction(transactionObjectString);

AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).createBillTransaction();
``` 

### Callback Response Example

```java
{ 
  "serverCorrelationId":"06f8ae9f-e6bc-4f54-99a3-4d7df22a6494",
  "status":"pending",
  "notificationMethod":"callback",
  "objectReference":"19066",
  "pollLimit":100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "18c55e1a-9619-46c2-a442-d40344832d03",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "19820",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.