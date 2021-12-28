# View A Response

`Here, viewResponse(String clientCorrelationId, Class<T> objectReference) creates a GET request to /responses/{clientCorrelationId}`

> `This endpoint returns a specific response.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

DebitMandate debitMandate = new DebitMandate();
List<AccountIdentifier> payee = new ArrayList<>();
List<CustomData> customData = new ArrayList<>();
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

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createAccountDebitMandate(new Identifiers(identifierList));

String clientCorrelationId = recurringPaymentRequest.getClientCorrelationId();
DebitMandate debitMandateResponse = mmClient.addRequest(recurringPaymentRequest).viewResponse(clientCorrelationId, DebitMandate.class);
```

### Response Example

```java
{
  "mandateReference": "string",
  "payee": [
    {
      "key": "msisdn",
      "value": "+33555123456"
    }
  ],
  "mandateStatus": "active",
  "startDate": "2018-11-20",
  "amountLimit": "15.00",
  "currency": "AED",
  "endDate": "2018-11-20",
  "frequencyType": "weekly",
  "numberOfPayments": 0,
  "requestingOrganisation": {
    "requestingOrganisationIdentifierType": "lei",
    "requestingOrganisationIdentifier": "string"
  },
  "creationDate": "2021-12-28T05:49:51.277Z",
  "modificationDate": "2021-12-28T05:49:51.277Z",
  "requestDate": "2021-12-28T05:49:51.277Z",
  "customData": [
    {
      "key": "string",
      "value": "string"
    }
  ],
  "dateCreated": "2021-12-28T05:49:51.277Z",
  "dateModified": "2021-12-28T05:49:51.277Z"
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAccountDebitMandate.Readme.md">viewAccountDebitMandate()</a>
function to acquire the final representation of the debit mandate object.