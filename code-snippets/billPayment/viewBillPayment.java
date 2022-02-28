MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

BillPay billPayment = new BillPay();
billPayment.setCurrency("<Currency>");
billPayment.setAmountPaid("<Amount>");

billPaymentRequest.setBillPayment(billPayment);
AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).createBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());

sdkResponse = mmClient.addRequest(billPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

BillPayments billPayments = mmClient.addRequest(billPaymentRequest).viewBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());