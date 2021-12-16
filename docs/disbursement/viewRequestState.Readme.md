# View A Request State

`Here, viewRequestState(String serverCorrelationId) creates a GET request to /requeststates/{serverCorrelationId}`

> `This endpoint returns a specific request state.`

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
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).setNotificationType(NotificationType.POLLING).createDisbursementTransaction();

sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
``` 

### Response Example

```java
{
  "serverCorrelationId": "74fda17c-d7b9-4d6e-908a-62eb8eeef7d9",
  "status": "completed",
  "notificationMethod": "polling",
  "objectReference": "REF-1639459534512",
  "pollLimit": 100
}
```