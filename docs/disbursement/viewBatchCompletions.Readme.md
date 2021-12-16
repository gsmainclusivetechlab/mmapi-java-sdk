# View Batch Completions

`Here, viewBatchCompletions(String batchId) creates a GET request to /batchtransactions/{batchId}/completions`

> `This endpoint returns completed transactions for a specific batch.`

### Usage/Examples

```java
DisbursementRequest disbursementRequest = new DisbursementRequest();
BatchTransaction batchTransaction = new BatchTransaction();
List<Transaction> transactions = new ArrayList<>();

List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

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