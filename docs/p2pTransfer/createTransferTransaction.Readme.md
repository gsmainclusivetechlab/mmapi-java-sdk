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

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));

transaction.setAmount("16.00");
transaction.setCurrency("USD");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);

p2PTransferRequest.setTransaction(transaction);

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