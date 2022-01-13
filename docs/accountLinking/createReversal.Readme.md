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

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
accountLinkingRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createTransferTransaction();

sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

Reversal reversal = new Reversal();
reversal.setType("reversal");
accountLinkingRequest.setReversal(reversal);
sdkResponse =  mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createReversal(txnRef);
```

Additionally, if you want to use reversal details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

String reversalJsonString = "{\"type\": \"reversal\"}";

accountLinkingRequest.setReversal(reversalJsonString);

AsyncResponse sdkResponse =  mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
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

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.