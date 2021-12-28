# View Batch Completions

`Here, viewBatchCompletions(String batchId) creates a GET request to /batchtransactions/{batchId}/completions`

> `This endpoint returns completed transactions for a specific batch.`

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

List<BatchCompletion> completedTransactions = mmClient.addRequest(disbursementRequest).viewBatchCompletions(sdkResponse.getObjectReference());
``` 

### Response Example

```java
[
  {
    "transactionReference": "string",
    "requestingOrganisationTransactionReference": "string",
    "creditParty": [
      {
        "key": "msisdn",
        "value": "+33555123456"
      }
    ],
    "debitParty": [
      {
        "key": "msisdn",
        "value": "+33555123456"
      }
    ],
    "completionDate": "2021-12-16T06:28:41.741Z",
    "link": "string",
    "customData": [
      {
        "key": "string",
        "value": "string"
      }
    ]
  }
]
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.