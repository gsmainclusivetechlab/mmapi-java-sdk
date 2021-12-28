# Perform an International Transfer

`Here, createInternationalTransaction() creates a POST request to /transactions/type/inttransfer`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a inttransfer transaction type passed via the URL.`

### Usage/Examples

To perform international transaction request, you have to create transaction object first.

```java
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
``` 

Perform international transfer request using transaction request.

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

internationalTransferRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place you callback URL>").createInternationalTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "73b0967d-2ac2-4c6d-920b-0a28c52b6244",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "18114",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.