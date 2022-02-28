MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
BillPay billPay = new BillPay();

billPay.setCurrency("<Currency>");
billPay.setAmountPaid("<Amount>");

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

billPaymentRequest.setBillPayment(billPay);
AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack("<Place your callback URL>").createBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());

String clientCorrelationId = billPaymentRequest.getClientCorrelationId();
BillPay billPay = mmClient.addRequest(billPaymentRequest).viewResponse(clientCorrelationId, BillPay.class);