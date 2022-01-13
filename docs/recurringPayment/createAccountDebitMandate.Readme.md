# Create A Debit Mandate

`Here, createAccountDebitMandate() creates a POST request to /accounts/{identifierType}/{identifier}/debitmandates`

> `Provided with a valid object representation, this endpoint allows for a new debit mandate to be created for a specific account.`

### Usage/Examples

A debit mandate object is to be created before calling the createAccountDebitMandate method. The example for debit mandate object as follows,

```java
DebitMandate debitMandate = new DebitMandate();
List<AccountIdentifier> payee = new ArrayList<>();
List<CustomData> customData = new ArrayList<>();

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
```
Initiate the create debit mandate request using the following code

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));
```

Additionally, if you want to use account debit mandate details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

String debitMandateJsonString = "{\"currency\": \"USD\",\"startDate\": \"2018-07-03T10:43:27.405Z\",\"endDate\": \"2028-07-03T10:43:27.405Z\",\"requestDate\": \"2018-07-03T10:43:27.405Z\",\"frequencyType\": \"sixmonths\",\"amountLimit\": \"1000.00\",\"numberOfPayments\": 2,\"payee\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";

recurringPaymentRequest.setDebitMandate(debitMandateJsonString);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));
```

### Callback Response Example

```java
{ 
  "serverCorrelationId":"06f8ae9f-e6bc-4f54-99a3-6d7df22a6496",
  "status":"pending",
  "notificationMethod":"callback",
  "objectReference":"17067",
  "pollLimit":100
}
```

### Polling Response Example

```java
{
  "serverCorrelationId": "18c55e1a-9619-46c2-a442-640344832d06",
  "status": "pending",
  "notificationMethod": "polling",
  "objectReference": "17827",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAccountDebitMandate.Readme.md">viewAccountDebitMandate()</a>
function to acquire the final representation of the debit mandate object.