# Create a Transaction

`Here, createRefundTransaction() creates a POST request to /transactions/type/adjustment`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
merchantPaymentRequest.setTransaction(transaction);

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack("<Place your callback URL>").createRefundTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "63420476-fdce-4fb2-95e5-d1dfed69d92d",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "17823",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.