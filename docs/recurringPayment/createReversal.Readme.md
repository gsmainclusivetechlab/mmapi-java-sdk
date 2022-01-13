# Create A Reversal

`Here, createReversal(String transactionReference) creates a POST request to 
/transactions/{transactionReference}/reversals`

> `Provided with a valid object representation, this endpoint allows for a new reversal to be created.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
recurringPaymentRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createMerchantTransaction();

sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

Reversal reversal = new Reversal();
reversal.setType("reversal");
recurringPaymentRequest.setReversal(reversal);
sdkResponse =  mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createReversal(txnRef);
```

Additionally, if you want to use reversal details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

String reversalJsonString = "{\"type\": \"reversal\"}";

recurringPaymentRequest.setReversal(reversalJsonString);
AsyncResponse sdkResponse =  mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

### Response Example

```java
{
  "serverCorrelationId": "1cef7250-bc7b-4437-8090-61c725484bcc",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "19855",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.