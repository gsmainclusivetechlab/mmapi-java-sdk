MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();
accountLinkingRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).createTransferTransaction();

sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

Reversal reversal = new Reversal();
reversal.setType("reversal");
accountLinkingRequest.setReversal(reversal);
sdkResponse =  mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createReversal(txnRef);