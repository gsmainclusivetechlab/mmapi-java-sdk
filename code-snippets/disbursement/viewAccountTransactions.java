MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
TransactionFilter filter = new TransactionFilter();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
filter.setLimit(10);
filter.setOffset(0);

Transactions transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);