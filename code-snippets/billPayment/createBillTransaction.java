MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<Amount>");
transaction.setCurrency("<Currency>");

billPaymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).createBillTransaction();