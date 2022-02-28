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