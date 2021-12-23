# Create A Link

`Here, createAccountLink(Identifiers identifiers) creates a POST request to /accounts/{identifierType}/{identifier}/links`

> `Provided with a valid object representation, this endpoint allows a new link to be created for a specific account.`

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

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));
```

### Response Example

```java
{
  "serverCorrelationId": "03a059de-3867-47a6-8481-fa11effee7a4",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "425",
  "pollLimit": 100
}
```