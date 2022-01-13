# Create A Link

`Here, createAccountLink(Identifiers identifiers) creates a POST request to /accounts/{identifierType}/{identifier}/links`

> `Provided with a valid object representation, this endpoint allows a new link to be created for a specific account.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

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

Additionally, if you want to use account link details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

String linkJsonString = "{\"sourceAccountIdentifiers\": [{\"key\": \"accountid\",\"value\": \"2999\"}],\"mode\": \"both\",\"status\": \"active\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}]}";

accountLinkingRequest.setLink(linkJsonString);

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

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAccountLink.Readme.md">viewAccountLink()</a> 
function to acquire the final representation of the AccountLink object.