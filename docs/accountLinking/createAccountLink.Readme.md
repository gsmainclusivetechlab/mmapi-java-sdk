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

Link link = new Link();
link.setSourceAccountIdentifiers(sourceAccountIdentifiers);
link.setMode("active");
link.setStatus("both");
link.setRequestingOrganisation(requestingOrganisation);
link.setRequestDate("2018-07-03T11:43:27.405Z");
link.setCustomData(customDataList);

AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

accountLinkingRequest.setLink(link);

List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createAccountLink(new Identifiers(identifierList));
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