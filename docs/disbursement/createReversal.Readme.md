# Create A Reversal

`Here, createReversal(String transactionReference) creates a POST request to 
/transactions/{transactionReference}/reversals`

> `Provided with a valid object representation, this endpoint allows for a new reversal to be created.`

### Usage/Examples

```java
Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

DisbursementRequest disbursementRequest = new DisbursementRequest();
disbursementRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).createDisbursementTransaction();

sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

Reversal reversal = new Reversal();
reversal.setType("reversal");
disbursementRequest.setReversal(reversal);
sdkResponse =  mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createReversal(txnRef);
```

### Response Example

```java
{
  "serverCorrelationId": "0cef7251-bc7b-4437-8090-31c725484bbd",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "17825",
  "pollLimit": 100
}
```