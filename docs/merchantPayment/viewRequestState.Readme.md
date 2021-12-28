# View A Request State

`Here, viewRequestState(String serverCorrelationId) creates a GET request to /requeststates/{serverCorrelationId}`

> `This endpoint returns a specific request state.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
merchantPaymentRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();

sdkResponse = mmClient.addRequest(merchantPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
```

### Response Example

```java
{
  "serverCorrelationId": "83075964-97ec-4bb4-aa51-58f7fbf4ed68",
  "status": "completed",
  "notificationMethod": "polling",
  "objectReference": "REF-1639128370835",
  "pollLimit": 100
}
```