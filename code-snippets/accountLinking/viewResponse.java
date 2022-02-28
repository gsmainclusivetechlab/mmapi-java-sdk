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

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createAccountLink(new Identifiers(identifierList));

String clientCorrelationId = accountLinkingRequest .getClientCorrelationId();
Link linkResponse = mmClient.addRequest(accountLinkingRequest).viewResponse(clientCorrelationId, Link.class);