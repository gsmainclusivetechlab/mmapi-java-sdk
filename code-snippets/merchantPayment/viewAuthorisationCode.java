MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
AuthorisationCode authorisationCode = new AuthorisationCode();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

authorisationCode.setCodeLifetime("<code expiry time in seconds>");
authorisationCode.setAmount("<amount>");
authorisationCode.setCurrency("<currency>");

merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));

/** Obtain generated pre-authorized code to perform merchant payment **/

AuthorisationCode authorisationCodeResponse = mmClient.addRequest(merchantPaymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());
