# Create a Transaction

`Here, createRefundTransaction() creates a POST request to /transactions/type/adjustment`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

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

MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
merchantPaymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack("<Place your callback URL>").createRefundTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "63420476-fdce-4fb2-95e5-d1dfed69d92d",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "17823",
  "pollLimit": 100
}
```