List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

/* Initiate the merchant pay request using the following code */

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();

merchantPaymentRequest.setTransaction(transactionObject);
AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).createMerchantTransaction();