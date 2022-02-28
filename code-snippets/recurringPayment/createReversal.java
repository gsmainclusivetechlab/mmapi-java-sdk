MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
recurringPaymentRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).createMerchantTransaction();

sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

Reversal reversal = new Reversal();
reversal.setType("reversal");
recurringPaymentRequest.setReversal(reversal);
sdkResponse =  mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createReversal(txnRef);