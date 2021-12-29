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
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

recurringPaymentRequest.setTransaction(transactionObject);
AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createMerchantTransaction();
```

### Callback Response Example

```java
{ 
  "serverCorrelationId":"06f8ae9f-e6bc-4f54-99a3-7d7df22a6477",
  "status":"pending",
  "notificationMethod":"callback",
  "objectReference":"19081",
  "pollLimit":100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "18c55e1a-9619-46c2-a442-740344832d72",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "19105",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.