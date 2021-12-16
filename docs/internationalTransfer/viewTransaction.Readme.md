# View A Transaction

`Here, viewTransaction(String transactionReference) creates a GET request to /transactions/{transactionReference}`

> `This endpoint returns the details of a transaction.`

### Usage/Examples

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

debitPartyList.add(new AccountIdentifier("walletid", "1"));
creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));

transaction.setAmount("100.00");
transaction.setCurrency("GBP");
transaction.setInternationalTransferInformation(transferInformation);
transaction.setSenderKyc(senderKyc);
transaction.setRequestingOrganisation(requestingOrganisation);
transaction.setCreditParty(creditPartyList);
transaction.setDebitParty(debitPartyList);

InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();
internationalTransferRequest.setTransaction(transaction);
AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place your callback URL>").createInternationalTransaction();

sdkResponse = mmClient.addRequest(internationalTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());
String txnRef = sdkResponse.getObjectReference();

transaction = mmClient.addRequest(internationalTransferRequest).viewTransaction(txnRef);
```

### Response Example

```java
{
  "transactionReference": "REF-1639640309474",
  "creditParty": [
    {
      "key": "accountid",
      "value": "2999"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907197912"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907232832"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907265888"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907412029"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907483978"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909732171"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330257762"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360515423"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444910612"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023787539"
    }
  ],
  "debitParty": [
    {
      "key": "accountid",
      "value": "2999"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907197912"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907232832"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907265888"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907412029"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637907483978"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909732171"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330257762"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360515423"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444910612"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023787539"
    }
  ],
  "type": "inttransfer",
  "transactionStatus": "pending",
  "amount": "100.00",
  "currency": "GBP",
  "internationalTransferInformation": {
    "originCountry": "GB"
  },
  "senderKyc": {
    "nationality": "GB",
    "dateOfBirth": "1970-07-03",
    "occupation": "Manager",
    "employerName": "MFX",
    "contactPhone": "+447125588999",
    "gender": "m",
    "idDocument": [
      {
        "idType": "nationalidcard",
        "idNumber": "1234567",
        "issueDate": "2018-07-03",
        "expiryDate": "2021-07-03",
        "issuer": "UKPA",
        "issuerPlace": "GB",
        "issuerCountry": "GB"
      }
    ],
    "postalAddress": {
      "country": "GB"
    },
    "subjectName": {
      "title": "Mr",
      "firstName": "Luke",
      "middleName": "R",
      "lastName": "Skywalker",
      "fullName": "Luke R Skywalker",
      "nativeName": "ABC"
    },
    "emailAddress": "luke.skywalkeraaabbb@gmail.com",
    "birthCountry": "GB"
  },
  "requestingOrganisation": {
    "requestingOrganisationIdentifierType": "organisationid",
    "requestingOrganisationIdentifier": "testorganisation"
  },
  "creationDate": "2021-12-16T07:38:29",
  "modificationDate": "2021-12-16T07:38:29",
  "requestDate": "2021-12-16T07:38:29"
}
```