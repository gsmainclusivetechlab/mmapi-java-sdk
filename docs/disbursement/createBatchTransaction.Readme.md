# Create A Transaction Batch

`Here, createBatchTransaction() creates a POST request to /batchtransactions`

> `Provided with a valid object representation, this endpoint allows for a new transaction batch to be created.`

### Usage/Examples

Create a bulk Transaction Object before performing the bulk disbursement

```java
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

List<Transaction> transactions = new ArrayList<>();
transactions.add(transaction);
```

Perform the bulk transaction using the following code

```java
DisbursementRequest disbursementRequest = new DisbursementRequest();
BatchTransaction batchTransaction = new BatchTransaction();

batchTransaction.setTransactions(transactions);
disbursementRequest.setBatchTransaction(batchTransaction);

AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createBatchTransaction();
``` 

### Response Example

```java
{
  "serverCorrelationId": "75825e07-271e-46e5-8d6a-d6325914abe9",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "1619",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="docs/disbursement/viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="docs/disbursement/viewBatchTransaction.Readme.md">viewBatchTransaction()</a>
function to acquire the final representation of the Transaction object.