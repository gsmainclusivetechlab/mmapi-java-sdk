MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
AuthorisationCode authorisationCode = new AuthorisationCode();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

authorisationCode.setCodeLifetime("<code expiry time in seconds>");
authorisationCode.setAmount("<amount>");
authorisationCode.setCurrency("<currency>");

agentServiceRequest.setAuthorisationCodeRequest(authorisationCode);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));