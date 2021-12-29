# View A Debit Mandate

`Here, viewAccountDebitMandate(Identifiers identifiers, String debitMandateReference) creates a GET request to /accounts/{identifierType}/{identifier}/debitmandates/{debitMandateReference}`

> `This endpoint returns a specific debit mandate linked to an account.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

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

List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createAccountDebitMandate(new Identifiers(identifierList));
        
sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
DebitMandate debitMandateResponse = mmClient.addRequest(recurringPaymentRequest).viewAccountDebitMandate(new Identifiers(identifierList), sdkResponse.getObjectReference());
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
  "creationDate": "2021-12-28T06:13:08.495Z",
  "modificationDate": "2021-12-28T06:13:08.495Z",
  "requestDate": "2021-12-28T06:13:08.495Z",
  "customData": [
    {
      "key": "string",
      "value": "string"
    }
  ],
  "dateCreated": "2021-12-28T06:13:08.495Z",
  "dateModified": "2021-12-28T06:13:08.495Z"
}
```