List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

List<Transaction> transactions = new ArrayList<>();
transactions.add(transaction);

/* Perform the bulk transaction using the following code */

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
DisbursementRequest disbursementRequest = new DisbursementRequest();
BatchTransaction batchTransaction = new BatchTransaction();

batchTransaction.setTransactions(transactions);
disbursementRequest.setBatchTransaction(batchTransaction);

AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createBatchTransaction();