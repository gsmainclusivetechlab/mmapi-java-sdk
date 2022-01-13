# Create a Transaction

`Here, createTransferTransaction() creates a POST request to /transactions/type/transfer`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

Transaction transaction = new Transaction();
KYCInformation senderKyc = new KYCInformation();
Name kycSubject = new Name();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
Address address = new Address("GB");
IdDocument idDocument = new IdDocument("nationalidcard");
InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();
List<IdDocument> identificationList = new ArrayList<>();

idDocument.setIdNumber("1234567");
idDocument.setIssuer("UKPA");
idDocument.setIssuerPlace("GB");
idDocument.setIssuerCountry("GB");
idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
idDocument.setOtherIddescription("test");
identificationList.add(idDocument);

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
senderKyc.setPostalAddress(address);
senderKyc.setSubjectName(kycSubject);

requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setAmount("16.00");
transaction.setCurrency("USD");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);

accountLinkingRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createTransferTransaction();
```

Additionally, if you want to use transaction details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

String accountLinkTransactionJsonString = "{\"amount\": \"100.00\",\"currency\": \"GBP\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"internationalTransferInformation\": {\"originCountry\": \"GB\"},\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"debitParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"creditParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";

accountLinkingRequest.setTransaction(accountLinkTransactionJsonString);

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).addCallBack("<Place your callback URL>").createTransferTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "1d477bf9-63d6-4f24-82fd-184689a4cb05",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "19035",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.