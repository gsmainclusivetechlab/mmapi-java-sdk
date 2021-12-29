# View Account Bills

`Here, viewBillPayment(Identifiers identifiers, String billReference) creates a GET request to /accounts/{identifierType}/{identifier}/bills/{billReference}/payments`

> `This endpoint allows for bill payments for a specific account to be returned`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

List<Bill> bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

BillPay billPayment = new BillPay();
billPayment.setCurrency("<Currency>");
billPayment.setAmountPaid("<Amount>");

billPaymentRequest.setBillPayment(billPayment);
AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).createBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());

sdkResponse = mmClient.addRequest(billPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

List<BillPay> billPayments = mmClient.addRequest(billPaymentRequest).viewBillPayment(new Identifiers(identifierList), bills.get(0).getBillReference());
```

### Response Example

```java
[
    {
	  "serviceProviderPaymentReference": "string",
	  "requestingOrganisationTransactionReference": "string",
	  "customerReference": "string",
	  "paymentType": "partialpayment",
	  "billPaymentStatus": "string",
	  "amountPaid": "15.00",
	  "currency": "AED",
	  "requestingOrganisation": "string",
	  "supplementaryBillReferenceDetails": [
		{
		  "paymentReferenceType": "string",
		  "paymentReferenceValue": "string"
		}
	  ],
	  "serviceProviderComment": "string",
	  "serviceProviderNotification": "string",
	  "requestDate": "2021-12-28T08:51:30.680Z",
	  "creationDate": "2021-12-28T08:51:30.680Z",
	  "modificationDate": "2021-12-28T08:51:30.680Z",
	  "customData": [
		{
		  "key": "string",
		  "value": "string"
		}
	  ],
	  "metadata": [
		{
		  "key": "string",
		  "value": "string"
		}
	  ],
	  "paidAmount": "15.21"
	}
]
```