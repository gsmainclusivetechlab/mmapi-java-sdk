# GSMA Mobile Money Payments Java SDK
Use this SDK to integrate GSMA Mobile Money payments into your app.

## Prerequisites
- Java JDK-1.8 or higher
- Apache Maven 3 or higher

## To build this application
In order to build the SDK from the source code you need to use Apache Maven and Java 1.8+
- Run 'mvn clean package' to build jar file

## Include GSMA Java SDK in your Java application
1. Build the jar file using  'mvn clean package' command
2. Import 'mmapi-java-sdk' jar file to your project's classpath


# Configure the SDK
To write an app using the mobile money SDK:

- Register for sandbox account and get your consumer key, consumer secret and API key
- You always need to create MMClient instance before making any API calls

Create an instance of the MMClient with consumer key, consumer secret and API key.
```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
```
By default, this would be in **SANDBOX** mode with **SecurityLevel.DEVELOPMENT**

You can configure MMClient with either **Environment.SANDBOX** mode or **Environment.PRODUCTION** mode.

Available security levels are:

- **NONE**
- **DEVELOPMENT**
- **STANDARD**
- **ENHANCED**

You can override the default configuration by using either of the following ways:

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>", "<mode>");
```
Or
```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>", "<mode>", "<security level>");
```
For example:
```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>", Environment.SANDBOX, SecurityLevel.DEVELOPMENT);
```

# Merchant Payment
The Merchant Payment Mobile Money SDKs allow merchants to accept payments from mobile money customers.

## Payee-initiated merchant payment.
The merchant initiates the request and will be credited when the payer approves the request.

A transaction object is to be created before calling the merchant payment. The example for transaction object as follows,

```java
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
```
Initiate the merchant pay request using the following code

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>").addCallBackUrl("<Place your callback URL>");
PaymentRequest paymentRequest = new PaymentRequest();

paymentRequest.setTransaction(transactionObject);
AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();
```

## Payee-Initiated Merchant Payment using the Polling Method

An asynchronous payment flow is used with the polling method. The client polls against the request state object to determine the outcome of the payment request. Steps to create merchant payment using polling method:

1. **Payee Initiated Merchant Payment**
```java
PaymentRequest paymentRequest = new PaymentRequest();
paymentRequest.setTransaction(transactionObject);

AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();
```
2. **Poll to Determine the Request State**
```java
String serverCorrelationId = sdkResponse.getServerCorrelationId();
AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).viewRequestState(serverCorrelationId);
```
3. **Retrieve a Transaction**
```java
String transactionReference = sdkResponse.getObjectReference();
TransactionResponse transactionResponse = mmClient.addRequest(paymentRequest).viewTransaction(transactionReference);
```

## Payer Initiated Merchant Payment

The merchant initiates the request and will be credited when the payer approves the request.

A transaction object is to be created before calling the payee-initiated merchant payment, the example for transaction object as follows,

```java
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
```
Initiate the merchant pay request using the following code

```java
PaymentRequest paymentRequest = new PaymentRequest();
paymentRequest.setTransaction(paymentRequestObject);

AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();
```

## Payee-Initiated Merchant Payment using a Pre-authorised Payment Code

A payer submit a request to generate a payment code which when presented to the payee, can be redeemed for an amount associated with the code. 

The following code illustrates how a customer can generate an authorization code which can in turn be used at a merchant to authorize a payment.

```java
PaymentRequest paymentRequest = new PaymentRequest();
AuthorisationCodeRequest authorisationCodeRequest = new AuthorisationCodeRequest();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

authorisationCodeRequest.setCodeLifetime("<code expiry time in seconds>");
authorisationCodeRequest.setAmount("<amount>");
authorisationCodeRequest.setCurrency("<currency>");

paymentRequest.setAuthorisationCodeRequest(authorisationCodeRequest);

AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));
```

Obtain generated pre-authorized code to perform merchant payment

```java
AuthorisationCodeResponse authorisationCodeResponse = mmClient.addRequest(paymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());
```

Initiate the merchant pay request using the following code. A transaction object is to be created before calling the merchant payment. The example for transaction object as follows,

```java
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
```

Initiate the merchant pay with pre-authorised code

```java
PaymentRequest paymentRequest = new PaymentRequest();
AuthorisationCodeRequest authorisationCodeRequest = new AuthorisationCodeRequest();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

authorisationCodeRequest.setCodeLifetime("<code expiry time in seconds>");
authorisationCodeRequest.setAmount("<amount>");
authorisationCodeRequest.setCurrency("<currency>");

accountRequest.setAuthorisationCodeRequest(authorisationCodeRequest);
AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));

sdkResponse = mmClient.addRequest(paymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
AuthorisationCodeResponse authorisationCodeResponse = mmClient.addRequest(paymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

paymentRequest = new PaymentRequest();
transaction.setOneTimeCode(authorisationCodeResponse.getAuthorisationCode());
paymentRequest.setTransaction(transaction);

sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();
```

## Merchant Payment Refund

Merchants can issue a refund to payers.

```java
Transaction transaction = new Transaction();
PaymentRequest paymentRequest = new PaymentRequest();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

paymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createRefundTransaction();
```

## Payment Reversal

In some failure scenarios, merchant may need to reverse a transaction

```java
AsyncResponse sdkResponse =  mmClient.addRequest(new PaymentRequest()).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

## Balance

Obtain the balance of a requested account. 

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new PaymentRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Retrieve Payments

The following code illustrates the mechanism to retrieve all payments for a merchant

```java
TransactionFilter filter = new TransactionFilter();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
filter.setLimit(10);
filter.setOffset(0);

List<TransactionResponse> transactions = mmClient.addRequest(new PaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
```

## Check for Service Availability

The application should perform service availability check before calling the payment scenarios

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new PaymentRequest()).viewServiceAvailability();
```

## Retrieve a Missing API Response

Merchant to retrieve a link to the final representation of the resource for which it attempted to create. Use this SDK when a callback is not received from the mobile money provider.

### 1. Missing Transaction Response

```java
PaymentRequest paymentRequest = new PaymentRequest();
Transaction transaction = new Transaction();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

paymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(paymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();

String clientCorrelationId = paymentRequest.getClientCorrelationId();
TransactionResponse transaction = mmClient.addRequest(paymentRequest).viewResponse(clientCorrelationId, TransactionResponse.class);
```

## Error Object
The mobile money SDK uses a common error object to provide error details. The following properties are included:

```java
{
  "errorCategory": "businessRule",
  "errorCode": "genericError",
  "errordescription": "string",
  "errorDateTime": "2021-12-01T10:26:02.015Z",
  "errorParameters": [
    {
      "key": "string",
      "value": "string"
    }
  ]
}
```

Example:

```java
{
  "errorCategory": "identification",
  "errorCode": "identifierError",
  "errorDescription": "Transaction does not exists",
  "errorDateTime": "2021-12-01T10:36:02.015Z",
  "errorParameters": [
    {
      "key": "providedValue",
      "value": "txnRef"
    }
  ]
}
```

# Disbursement

The Disbursement Mobile Money APIs allow organisations to disburse funds to mobile money recipients.

## Individual Disbursement

Following code illustrates an individual disbursement using an asynchronous flow with the notification provided via a callback.

```java
Transaction transaction = new Transaction();
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

disbursementRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createDisbursementTransaction();
```

## Individual Disbursement Using the Polling Method

The individual disbursement using polling method can be completed using the following function calls

```java
Transaction transaction = new Transaction();
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

disbursementRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).setNotificationType(NotificationType.POLLING).createDisbursementTransaction();

sdkResponse = mmClient.addRequest(disbursementRequest).viewRequestState(sdkResponse.getServerCorrelationId());
TransactionResponse transactionResponse = mmClient.addRequest(disbursementRequest).viewTransaction(sdkResponse.getObjectReference());
```


## Bulk Disbursement
  
The bulk disbursement use case consist of  following  request
      
1. Perform a Bulk Disbursement
2. Retrieve Batch Transactions that have Completed
3. Retrieve Batch Transactions that have been Rejected
  
  
### 1. Perform a  bulk Disbursement
  
Create a bulk Transaction Object before performing the bulk disbursement

```java
Transaction transaction = new Transaction();
List<Transaction> transactions = new ArrayList<>();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

transactions.add(transaction);
```

Perform the bulk transaction using the following code

```java
DisbursementRequest disbursementRequest = new DisbursementRequest();
DisbursementTransaction disbursementTransaction = new DisbursementTransaction();

disbursementTransaction.setTransactions(transactions);
disbursementRequest.setDisbursementTransaction(disbursementTransaction);

AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").createBatchTransaction();
``` 
 
### 2. Retrieve Batch Transactions that have Completed
This use case allows the disbursement organisation to retrieve all completed transactions for a given batch.
  
```java
List<DisbursementCompletedTransactionResponse> completedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchCompletions("<object reference>");
```
  
### 3. Retrieve Batch Transactions that have been Rejected

This use case allows the disbursement organisation to retrieve all rejected transactions for a given batch

```java
List<DisbursementRejectedTransactionResponse> rejectedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchRejections("<object reference>");
```

## Bulk Disbursement with maker/checker

The bulk disbursement with maker/checker use case consist of  following  request
      
1. Perform a Bulk Disbursement
2. Retrieve Batch Transactions that have Completed
3. Retrieve Batch Transactions that have been Rejected
4. Approve Batch request
5. Retrieve the batch request
      
Perform the step 1 to step 3 which is already mentioned in bulk disbursement use cases     
    
### 4. Approve the batch request

```java
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<PatchData> patchDataList = new ArrayList<>();

patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));

disbursementRequest.setPatchData(patchDataList);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").updateBatchTransaction("objectreference");
```

### 5. Retrieve the batch request

```java
DisbursementTransactionResponse batchResponse = mmClient.addRequest(disbursementRequest).viewBatchTransaction("<object reference>");
```

## Disbursement Reversal

In some failure scenarios, merchant may need to reverse a transaction

```java
AsyncResponse sdkResponse =  mmClient.addRequest(new DisbursementRequest()).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

## Disbursement Balance

Obtain the balance of a requested account. 

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new DisbursementRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Retrieve Disbursement Transaction

The following code illustrates the mechanism to retrieve all transactions for a disbursement organisation

```java
TransactionFilter filter = new TransactionFilter();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
filter.setLimit(10);
filter.setOffset(0);

List<TransactionResponse> transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
```

## Check for Service Availability

The application should perform service availability check before calling the transaction scenarios

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();
```

### 1. Missing Transaction Response

Use this SDK when a callback is not received from the mobile money provider.

```java
DisbursementTransactionResponse transaction = mmClient.addRequest(disbursementRequestObject).viewResponse("<client correlation id>", DisbursementTransactionResponse.class);
```

# International Transfers

The International Transfer Mobile Money SDK allow financial service providers to perform cross-border mobile money transfers, including remittances.

## International Transfer via Hub/Bilateral International Transfer

A hub is used by the sending FSP to obtain a quotation and perform the transfer with the receiving FSP. A callback is provided by the receiving FSP to return the quotation and the confirmation of the transfer.

### 1. Request a International Transfer Quotation

Initialize quotation object

```java
Identification identification = new Identification("nationalidcard");
PostalAddress postalAddress = new PostalAddress("GB");
KYCSubject kycSubject = new KYCSubject();
KYC senderKyc = new KYC();

List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();
List<CustomData> customDataList = new ArrayList<>();
List<Identification> identificationList = new ArrayList<>();

identification.setIdNumber("1234567");
identification.setIssuer("UKPA");
identification.setIssuerPlace("GB");
identification.setIssuerCountry("GB");
identification.setIssueDate("2018-07-03T11:43:27.405Z");
identification.setExpiryDate("2021-07-03T11:43:27.405Z");
identification.setOtherIddescription("test");
identificationList.add(identification);

kycSubject.setTitle("Mr");
kycSubject.setFirstName("Luke");
kycSubject.setMiddleName("R");
kycSubject.setLastName("Skywalker");
kycSubject.setFullName("Luke R Skywalker");
kycSubject.setNativeName("ABC");

senderKyc.setNationality("GB");
senderKyc.setBirthCountry("GB");
senderKyc.setOccupation("Manager");
senderKyc.setEmployerName("MFX");
senderKyc.setContactPhone("+447125588999");
senderKyc.setGender("m");
senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
senderKyc.setIdDocument(identificationList);
senderKyc.setPostalAddress(postalAddress);
senderKyc.setSubjectName(kycSubject);

debitPartyList.add(new DebitParty("accountid", "2999"));
creditPartyList.add(new CreditParty("accountid", "2999"));
customDataList.add(new CustomData("keytest", "keyvalue"));

Quotation quotation = new Quotation("75.30", "RWF", creditPartyList, debitPartyList);
quotation.setSubType("abc");
quotation.setChosenDeliveryMethod("agent");
quotation.setSendingServiceProviderCountry("AD");
quotation.setOriginCountry("AD");
quotation.setReceivingCountry("AD");
quotation.setRequestDate("2018-07-03T11:43:27.405Z");
quotation.setSenderKyc(senderKyc);
quotation.setCustomData(customDataList);
``` 

Request a quotation to perform international transfer.

```java
InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

internationalTransferRequest.setQuotation(quotation);

AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place your callback URL>").createQuotation();
```

### 2. Perform an International Transfer

To perform international transaction request, you have to create transaction object first.

```java
Transaction transaction = new Transaction();
KYC senderKyc = new KYC();
KYCSubject kycSubject = new KYCSubject();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
PostalAddress postalAddress = new PostalAddress("GB");
Identification identification = new Identification("nationalidcard");
InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();
List<Identification> identificationList = new ArrayList<>();

identification.setIdNumber("1234567");
identification.setIssuer("UKPA");
identification.setIssuerPlace("GB");
identification.setIssuerCountry("GB");
identification.setIssueDate("2018-07-03T11:43:27.405Z");
identification.setExpiryDate("2021-07-03T11:43:27.405Z");
identification.setOtherIddescription("test");
identificationList.add(identification);

kycSubject.setTitle("Mr");
kycSubject.setFirstName("Luke");
kycSubject.setMiddleName("R");
kycSubject.setLastName("Skywalker");
kycSubject.setFullName("Luke R Skywalker");
kycSubject.setNativeName("ABC");

senderKyc.setNationality("GB");
senderKyc.setBirthCountry("GB");
senderKyc.setOccupation("Manager");
senderKyc.setEmployerName("MFX");
senderKyc.setContactPhone("+447125588999");
senderKyc.setGender("m");
senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
senderKyc.setIdDocument(identificationList);
senderKyc.setPostalAddress(postalAddress);
senderKyc.setSubjectName(kycSubject);

requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

debitPartyList.add(new DebitParty("walletid", "1"));
creditPartyList.add(new CreditParty("msisdn", "+44012345678"));

transaction.setAmount("100.00");
transaction.setCurrency("GBP");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);
```
Perform international transfer request using transaction request

```java
InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

internationalTransferRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place you callback URL>").createInternationalTransaction();
```

## International Transfer Reversal

In some failure scenarios, merchant may need to reverse a transaction

```java
AsyncResponse sdkResponse =  mmClient.addRequest(new InternationalTransferRequest()).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

## Balance

Obtain an FSP Balance 

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new InternationalTransferRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Check for Service Availability

The application should perform service availability check before submit a request

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new InternationalTransferRequest()).viewServiceAvailability();
```

## Missing Transaction Response

Use this SDK when a callback is not received from the mobile money provider.

```java
TransactionResponse transaction = mmClient.addRequest(internationalTransferRequestObject).viewResponse("<client correlation id>", TransactionResponse.class);
```


# P2P Transfers

The P2P Transfer Mobile Money SDKs allow financial service providers (FSPs) to transfer funds from an account holding individual to another account holding individual or to a non-account holding individual.

## P2P Transfer via Switch

In P2P Transfer mechanism, a switch is used by the sending FSP to;
1. Confirm the recipient name
2. Request a quotation and,
3. Perform the transfer with the receiving FSP

### 1. Confirm the recipient name

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
AccountNameResponse accountNameResponse = mmClient.addRequest(new P2PTransferRequest()).viewAccountName(new Identifiers(identifierList));
```

### 2. Request a quotation
```java
P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

p2PTransferRequest.setQuotation("<quotation object>");

AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack("<Place your callback URL>").createQuotation();
```

### 3. Perform P2P transfer
```java
P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

p2PTransferRequest.setTransaction("<transaction object>");

AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack("<Place your callback URL>").createTransferTransaction();
```

## P2P Transfer Reversal

In some failure scenarios, merchant may need to reverse a transaction

```java
AsyncResponse sdkResponse =  mmClient.addRequest(new P2PTransferRequest()).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

## Balance

Obtain an FSP Balance 

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new P2PTransferRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Retrieve Transactions

The following code illustrates the mechanism to retrieve all transactions

```java
TransactionFilter filter = new TransactionFilter();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
filter.setLimit(10);
filter.setOffset(0);

List<TransactionResponse> transactions = mmClient.addRequest(new P2PTransferRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
```

## Check for Service Availability

The application should perform service availability check before submit a request

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new P2PTransferRequest()).viewServiceAvailability();
```

## Missing Transaction Response

Use this SDK when a callback is not received from the mobile money provider.

```java
TransactionResponse transaction = mmClient.addRequest(p2pTransferRequestObject).viewResponse("<client correlation id>", TransactionResponse.class);
```

# Recurring Payments

The Recurring Payments Mobile Money SDKs allow service providers to setup electronic payment mandates for mobile money customers and initiate payments against payment mandates.

## Setup a Recurring Payment
A debit mandate object is to be created before submitting the request. The service provider then initiates the request which is authorised by the account holding customer.

Creating debit mandate object:
```java
DebitMandate debitMandate = new DebitMandate();
List<Party> payee = new ArrayList<>();
List<CustomData> customData = new ArrayList<>();

payee.add(new Party("accountid", "2999"));
customData.add(new CustomData("keytest", "keyvalue"));

debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
debitMandate.setCurrency("GBP");
debitMandate.setAmountLimit("1000.00");
debitMandate.setNumberOfPayments(2);
debitMandate.setFrequencyType("sixmonths");
debitMandate.setPayee(payee);
debitMandate.setCustomData(customData);
```

Setting-up of a recurring payment via a debit mandate;
```java
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createAccountDebitMandate(new Identifiers(identifierList));
```

## Take a Recurring Payment

Initiates a payment request to the FSP to debit the account-holders account as per the debit mandate.
```java
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("<identifier type>", "<identifier type value>"));
creditPartyList.add(new CreditParty("<identifier type>", "<identifier type value>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount(amount);
transaction.setCurrency(currency);

RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
recurringPaymentRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createMerchantTransaction();
```

## Take a Recurring Payment using the Polling Method

The client polls against the request state object to determine the outcome of the payment request.
```java
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).setNotificationType(NotificationType.POLLING).createAccountDebitMandate(new Identifiers(identifierList));

sdkResponse = mmClient.addRequest(recurringPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());
DebitMandateResponse debitMandateResponse = mmClient.addRequest(recurringPaymentRequest).viewAccountDebitMandate(new Identifiers(identifierList), sdkResponse.getObjectReference());
```

## Recurring Payment Refund

We can use the following code to perform a refund for a recurring payment using the Mobile Money SDK.
```java
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();

debitPartyList.add(new DebitParty("<identifier type>", "<identifier type value>"));
creditPartyList.add(new CreditParty("mandateReference", "<mandate reference>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

recurringPaymentRequest = new RecurringPaymentRequest();
recurringPaymentRequest.setTransaction(transaction);
sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createRefundTransaction();
```

## Recurring Payment Reversal

We can use the following code to submits the reversal request - need to pass the reference of the transaction that is to be reversed.
```java
AsyncResponse sdkResponse =  mmClient.addRequest(new RecurringPaymentRequest()).addCallBack("<Place your callback URL>").createReversal("<transaction reference>");
```

## Payer sets up a Recurring Payment using MMP Channel

The process is same as like a normal recurring payment, submits the debit mandate request for processing the recurring payment. 
```java
RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
recurringPaymentRequest.setDebitMandate(debitMandate);

AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack("<Place your callback URL>").createAccountDebitMandate(new Identifiers(identifierList));
```

## Obtain a Service Provider Balance

Use the following code to obtain the balance of the requested account.
```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Retrieve Payments for a Service Provider

The following code returns the first 10 transactions of the given account. 
```java
TransactionFilter filter = new TransactionFilter();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
filter.setLimit(10);
filter.setOffset(0);

List<TransactionResponse> transactions = mmClient.addRequest(new RecurringPaymentRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
```

## Check for Service Availability

The application should perform service availability check before submit a request
```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new RecurringPaymentRequest()).viewServiceAvailability();
```

## Retrieve a Missing API Response

Use this SDK when a callback is not received - using the service provider's clientCorrelationId.
```java
DebitMandateResponse debitMandateResponse = mmClient.addRequest(recurringPaymentRequestObject).viewResponse("<client correlation id>", DebitMandateResponse.class);
```

# Account Linking

The Account Linking Mobile Money APIs allow financial service providers to link customer accounts to mobile money accounts, thus allowing their customers to push funds to and pull funds from mobile money. Conversely, mobile money providers can use the APIs to link their customers mobile money accounts to financial service providers.

## Setup an Account Link

Following code illustrates how to create an Account Link using an asynchronous flow with the notification provided via a callback.

```java
List<DebitParty> sourceAccountIdentifiers = new ArrayList<>();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
List<CustomData> customDataList = new ArrayList<>();
        
sourceAccountIdentifiers.add(new DebitParty("accountid", "<Place your account id of debit party here>"));

customDataList.add(new CustomData("keytest", "keyvalue"));
        
requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        
AccountLink accountLink = new AccountLink();
accountLink.setSourceAccountIdentifiers(sourceAccountIdentifiers);
accountLink.setMode("active");
accountLink.setStatus("both");
accountLink.setRequestingOrganisation(requestingOrganisation);
accountLink.setRequestDate("2018-07-03T11:43:27.405Z");
accountLink.setCustomData(customDataList);

accountLinkRequest.setAccountLink(accountLink);
        
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));
```

## Perform a Transfer for a Linked Account

To perform transfer for a Linked Account, you have to create transaction object first.

```java
Transaction transaction = new Transaction();
KYC senderKyc = new KYC();
KYCSubject kycSubject = new KYCSubject();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
PostalAddress postalAddress = new PostalAddress("GB");
Identification identification = new Identification("nationalidcard");
InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();
List<Identification> identificationList = new ArrayList<>();

identification.setIdNumber("1234567");
identification.setIssuer("UKPA");
identification.setIssuerPlace("GB");
identification.setIssuerCountry("GB");
identification.setIssueDate("2018-07-03T11:43:27.405Z");
identification.setExpiryDate("2021-07-03T11:43:27.405Z");
identification.setOtherIddescription("test");
identificationList.add(identification);

kycSubject.setTitle("Mr");
kycSubject.setFirstName("Luke");
kycSubject.setMiddleName("R");
kycSubject.setLastName("Skywalker");
kycSubject.setFullName("Luke R Skywalker");
kycSubject.setNativeName("ABC");

senderKyc.setNationality("GB");
senderKyc.setBirthCountry("GB");
senderKyc.setOccupation("Manager");
senderKyc.setEmployerName("MFX");
senderKyc.setContactPhone("+447125588999");
senderKyc.setGender("m");
senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
senderKyc.setIdDocument(identificationList);
senderKyc.setPostalAddress(postalAddress);
senderKyc.setSubjectName(kycSubject);

requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

debitPartyList.add(new DebitParty("walletid", "1"));
creditPartyList.add(new CreditParty("msisdn", "+44012345678"));

transaction.setAmount("100.00");
transaction.setCurrency("GBP");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);
```

Perform transfer for a Linked Account using transaction request

```java
AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

accountLinkRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).addCallBack(CALLBACK_URL).createTransferTransaction();

sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

TransactionResponse transaction = mmClient.addRequest(accountLinkRequest).viewTransaction(txnRef);
```

## Perform a Transfer using an Account Link via the Polling Method

To perform a transfer using an Account Link via the Polling Method, follow the given code

```java
AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();
debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));
Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

accountLinkRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).setNotificationType(NotificationType.POLLING).createTransferTransaction();

sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
TransactionResponse transactionResponse = mmClient.addRequest(accountLinkRequest).viewTransaction(sdkResponse.getObjectReference());
```

## Perform a Transfer Reversal

In some failure scenarios, merchant may need to reverse a transaction

```java
AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

List<DebitParty> debitPartyList = new ArrayList<>();
List<CreditParty> creditPartyList = new ArrayList<>();
debitPartyList.add(new DebitParty("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new CreditParty("accountid", "<Place your account id of credit party here>"));
Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");

accountLinkRequest.setTransaction(transaction);
        
AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createTransferTransaction();

sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

sdkResponse =  mmClient.addRequest(new PaymentRequest()).createReversal(txnRef);
```

## Obtain a Financial Service Provider Balance

Obtain the balance of a requested account.

```java
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AccountBalance accountBalance = mmClient.addRequest(new AccountLinkRequest()).viewAccountBalance(new Identifiers(identifierList));
```

## Retrieve Transfers for a Financial Service Provider

The following code illustrates the mechanism to retrieve transfers

```java
TransactionFilter filter = new TransactionFilter();
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));
filter.setLimit(10);
filter.setOffset(0);

List<TransactionResponse> transactions = mmClient.addRequest(new AccountLinkRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
```

## Check for Service Availability

The application should perform service availability check before before submit a request

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new AccountLinkRequest()).viewServiceAvailability();
```

## Retrieve a Missing API Response

Merchant to retrieve a link to the final representation of the resource for which it attempted to create. Use this SDK when a callback is not received from the mobile money provider.

```java
AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

List<DebitParty> sourceAccountIdentifiers = new ArrayList<>();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
List<CustomData> customDataList = new ArrayList<>();
        
sourceAccountIdentifiers.add(new DebitParty("accountid", "2999"));

customDataList.add(new CustomData("keytest", "keyvalue"));
        
requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
        
AccountLink accountLink = new AccountLink();
accountLink.setSourceAccountIdentifiers(sourceAccountIdentifiers);
accountLink.setMode("active");
accountLink.setStatus("both");
accountLink.setRequestingOrganisation(requestingOrganisation);
accountLink.setRequestDate("2018-07-03T11:43:27.405Z");

accountLink.setCustomData(customDataList);

accountLinkRequest.setAccountLink(accountLink);
        
List<IdentifierData> identifierList = new ArrayList<>();

identifierList.add(new IdentifierData("<identifier type>", "<identifier type value>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));

String clientCorrelationId = accountLinkRequest.getClientCorrelationId();
AccountLinkResponse accountLinkResponse = mmClient.addRequest(accountLinkRequest).viewResponse(clientCorrelationId, AccountLinkResponse.class);
```