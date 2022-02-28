MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
List<CustomData> customDataList = new ArrayList<>();

sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

customDataList.add(new CustomData("keytest", "keyvalue"));

requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");

Link link = new Link();
link.setSourceAccountIdentifiers(sourceAccountIdentifiers);
link.setMode(Mode.BOTH.getMode());
link.setStatus(Status.ACTIVE.getStatus());
link.setRequestingOrganisation(requestingOrganisation);
link.setRequestDate("2018-07-03T11:43:27.405Z");
link.setCustomData(customDataList);

accountLinkingRequest.setLink(link);

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createAccountLink(new Identifiers(identifierList));

sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());

String linkRef = sdkResponse.getObjectReference();
Link linkResponse = mmClient.addRequest(accountLinkingRequest).viewAccountLink(new Identifiers(identifierList), linkRef);