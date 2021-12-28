# View A Request State

`Here, viewRequestState(String serverCorrelationId) creates a GET request to /requeststates/{serverCorrelationId}`

> `This endpoint returns a specific request state.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

DebitMandate debitMandate = new DebitMandate();
List<AccountIdentifier> payee = new ArrayList<>();
List<CustomData> customData = new ArrayList<>();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();
List<AccountIdentifier> identifierList = new ArrayList<>();

payee.add(new AccountIdentifier("<identifier type>", "<identifier>"));
customData.add(new CustomData("<data key>", "<data value>"));

debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
debitMandate.setCurrency("<currency>");
debitMandate.setAmountLimit("<amount>");
debitMandate.setNumberOfPayments("<payments count>");
debitMandate.setFrequencyType("<frequency>");
debitMandate.setPayee(payee);
debitMandate.setCustomData(customData);

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).setNotificationType(NotificationType.POLLING).createAccountDebitMandate(new Identifiers(identifierList));

sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
```

### Response Example

```java
{
  "serverCorrelationId": "83075964-97ec-4bb4-aa51-55f7fbf4ed86",
  "status": "completed",
  "notificationMethod": "polling",
  "objectReference": "REF-16396283708786",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAccountDebitMandate.Readme.md">viewAccountDebitMandate()</a>
function to acquire the final representation of the debit mandate object.