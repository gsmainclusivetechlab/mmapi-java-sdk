# Create a Transaction

`Here, createTransferTransaction() creates a POST request to /transactions/type/transfer`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

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

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);

p2PTransferRequest.setTransaction(transaction);

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack("<Place your callback URL>").createTransferTransaction();
```

Additionally, if you want to use transaction details as JSON string, you can use the following code;

```java
P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

String p2pTransactionJsonString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"requestingOrganisation\": {\"requestingOrganisationIdentifierType\": \"organisationid\",\"requestingOrganisationIdentifier\": \"testorganisation\"},\"internationalTransferInformation\": {\"originCountry\": \"GB\"},\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"debitParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"creditParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";

p2PTransferRequest.setTransaction(p2pTransactionJsonString);

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack("<Place your callback URL>").createTransferTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "4426e859-ff83-4eae-b8c6-ac7402b97535",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "18121",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.