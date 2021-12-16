# Create a Transaction

`Here, createDisbursementTransaction() creates a POST request to /transactions/type/disbursement`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
Transaction transaction = new Transaction();
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

disbursementRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createDisbursementTransaction();
```

### Callback Response Example

```java
{
  "serverCorrelationId": "c672ab95-556e-47b4-a431-97de5b069d94",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "18097",
  "pollLimit": 100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "12aa4f5c-3978-4199-a7b9-269d796ea237",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "18098",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.