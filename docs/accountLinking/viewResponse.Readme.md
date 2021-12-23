# View A Response

`Here, viewResponse(String clientCorrelationId, Class<T> objectReference) creates a GET request to /responses/{clientCorrelationId}`

> `This endpoint returns a specific response.`

### Usage/Examples

```java
List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
List<CustomData> customDataList = new ArrayList<>();

sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

customDataList.add(new CustomData("keytest", "keyvalue"));

requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");

AccountLink accountLink = new AccountLink();
accountLink.setSourceAccountIdentifiers(sourceAccountIdentifiers);
accountLink.setMode("active");
accountLink.setStatus("both");
accountLink.setRequestingOrganisation(requestingOrganisation);
accountLink.setRequestDate("2018-07-03T11:43:27.405Z");
accountLink.setCustomData(customDataList);

AccountLinkRequest accountLinkRequest = new AccountLinkRequest();
accountLinkRequest.setAccountLink(accountLink);

List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).addCallBack("<Place your callback URL>").createAccountLink(new Identifiers(identifierList));

String clientCorrelationId = accountLinkRequest .getClientCorrelationId();
AccountLink accountLinkResponse = mmClient.addRequest(accountLinkRequest).viewResponse(clientCorrelationId, AccountLink.class);
```

### Response Example

```java
{
  "linkReference": "REF-1640157062350",
  "sourceAccountIdentifiers": [
    {
      "key": "accountid",
      "value": "2999"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907197912"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907232832"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907265888"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907412029"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907483978"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909732171"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330257762"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360515423"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444910612"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023787539"
    }
  ],
  "mode": "active",
  "status": "active",
  "requestingOrganisation": {
    "requestingOrganisationIdentifierType": "organisationid",
    "requestingOrganisationIdentifier": "testorganisation"
  },
  "creationDate": "2021-12-22T07:11:02",
  "modificationDate": "2021-12-22T07:11:02",
  "requestDate": "2018-07-03T11:43:27",
  "customData": [
    {
      "key": "keytest",
      "value": "keyvalue"
    }
  ]
}
```