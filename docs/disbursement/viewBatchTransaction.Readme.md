# View A Transaction Batch

`Here, viewBatchTransaction(String batchId) creates a GET request to /batchtransactions/{batchId}`

> `This endpoint returns the current status of a batch transaction.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
DisbursementRequest disbursementRequest = new DisbursementRequest();
BatchTransaction batchTransaction = new BatchTransaction();
List<Transaction> transactions = new ArrayList<>();

List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

transactions.add(transaction);

batchTransaction.setTransactions(transactions);
disbursementRequest.setBatchTransaction(batchTransaction);

AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createBatchTransaction();

sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());

BatchTransaction batchResponse = mmClient.addRequest(disbursementRequest).viewBatchTransaction(sdkResponse.getObjectReference());
``` 

### Response Example

```java
{
  "batchId": "REF-1639627145381",
  "batchStatus": "created",
  "processingFlag": false,
  "completedCount": 0,
  "rejectionCount": 0,
  "parsingSuccessCount": 0,
  "creationDate": "2021-12-16T03:59:05",
  "modificationDate": "2021-12-16T03:59:05",
  "requestDate": "2021-12-16T03:59:05"
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.