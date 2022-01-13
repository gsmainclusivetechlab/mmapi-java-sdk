# Create a Transaction

`Here, createMerchantTransaction() creates a POST request to /transactions/type/merchantpay`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

A transaction object is to be created before calling the merchant transaction. The example for transaction object as follows,

```java
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
```
Initiate the merchant pay request using the following code

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

merchantPaymentRequest.setTransaction(transactionObject);
AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();
```

Additionally, if you want to use transaction details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";

merchantPaymentRequest.setTransaction(transactionObjectString );
AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();
```

### Callback Response Example

```java
{ 
  "serverCorrelationId":"06f8ae9f-e6bc-4f54-99a3-7d7df22a6490",
  "status":"pending",
  "notificationMethod":"callback",
  "objectReference":"16066",
  "pollLimit":100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "18c55e1a-9619-46c2-a442-b40344832d02",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "17820",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.