# View A Transaction

`Here, viewTransaction(String transactionReference) creates a GET request to /transactions/{transactionReference}`

> `This endpoint returns the details of a transaction.`

### Usage/Examples

```java
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

sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(sdkResponse.getServerCorrelationId());

String txnRef = sdkResponse.getObjectReference();
Transaction transactionResponse = mmClient.addRequest(accountLinkingRequest).viewTransaction(txnRef);
```

### Response Example

```java
{
  "transactionReference": "REF-1640158853973",
  "creditParty": [
    {
      "key": "msisdn",
      "value": "+44012345678"
    },
    {
      "key": "accountid",
      "value": "3"
    },
    {
      "key": "mandatereference",
      "value": "REF-1601985847787"
    },
    {
      "key": "mandatereference",
      "value": "REF-1601985859399"
    },
    {
      "key": "mandatereference",
      "value": "REF-1601986025735"
    },
    {
      "key": "linkref",
      "value": "REF-1639493640797"
    },
    {
      "key": "linkref",
      "value": "REF-1639493642261"
    },
    {
      "key": "linkref",
      "value": "REF-1639493662185"
    },
    {
      "key": "linkref",
      "value": "REF-1639493749407"
    },
    {
      "key": "linkref",
      "value": "REF-1639493750632"
    },
    {
      "key": "linkref",
      "value": "REF-1639493820584"
    },
    {
      "key": "linkref",
      "value": "REF-1639493821921"
    },
    {
      "key": "linkref",
      "value": "REF-1639493851726"
    },
    {
      "key": "linkref",
      "value": "REF-1639493852975"
    },
    {
      "key": "linkref",
      "value": "REF-1639493881467"
    }
  ],
  "debitParty": [
    {
      "key": "msisdn",
      "value": "+449999999"
    },
    {
      "key": "walletid",
      "value": "1"
    },
    {
      "key": "accountid",
      "value": "1"
    },
    {
      "key": "mandatereference",
      "value": "REF-1583141449478"
    },
    {
      "key": "linkref",
      "value": "REF-1473082363913"
    },
    {
      "key": "linkref",
      "value": "REF-1579002505974"
    },
    {
      "key": "mandatereference",
      "value": "REF-1599647601577"
    },
    {
      "key": "mandatereference",
      "value": "REF-1613740431016"
    },
    {
      "key": "linkref",
      "value": "REF-1613740470938"
    },
    {
      "key": "mandatereference",
      "value": "REF-1613740615603"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614024918957"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614025064838"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614025221748"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614097239538"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614163668072"
    },
    {
      "key": "linkref",
      "value": "REF-1614172481727"
    },
    {
      "key": "linkref",
      "value": "REF-1614172597261"
    },
    {
      "key": "linkref",
      "value": "REF-1614172656091"
    },
    {
      "key": "linkref",
      "value": "REF-1614173800129"
    },
    {
      "key": "linkref",
      "value": "REF-1614173872194"
    },
    {
      "key": "linkref",
      "value": "REF-1614174252665"
    },
    {
      "key": "linkref",
      "value": "REF-1614179612570"
    },
    {
      "key": "linkref",
      "value": "REF-1614255971622"
    },
    {
      "key": "linkref",
      "value": "REF-1614256172931"
    },
    {
      "key": "linkref",
      "value": "REF-1614256998924"
    },
    {
      "key": "linkref",
      "value": "REF-1614257709534"
    },
    {
      "key": "linkref",
      "value": "REF-1614258016942"
    },
    {
      "key": "linkref",
      "value": "REF-1614258036195"
    },
    {
      "key": "linkref",
      "value": "REF-1614258054851"
    },
    {
      "key": "linkref",
      "value": "REF-1614258135135"
    },
    {
      "key": "linkref",
      "value": "REF-1614258167890"
    },
    {
      "key": "linkref",
      "value": "REF-1614258209978"
    },
    {
      "key": "linkref",
      "value": "REF-1614258254108"
    },
    {
      "key": "linkref",
      "value": "REF-1614258289916"
    },
    {
      "key": "linkref",
      "value": "REF-1614258353721"
    },
    {
      "key": "linkref",
      "value": "REF-1614258806823"
    },
    {
      "key": "linkref",
      "value": "REF-1614259484113"
    },
    {
      "key": "linkref",
      "value": "REF-1614259518394"
    },
    {
      "key": "linkref",
      "value": "REF-1614259655836"
    },
    {
      "key": "linkref",
      "value": "REF-1614259656462"
    },
    {
      "key": "linkref",
      "value": "REF-1614336223173"
    },
    {
      "key": "linkref",
      "value": "REF-1614336223786"
    },
    {
      "key": "mandatereference",
      "value": "REF-1614598472228"
    },
    {
      "key": "linkref",
      "value": "REF-1614618683962"
    },
    {
      "key": "linkref",
      "value": "REF-1614618684550"
    },
    {
      "key": "linkref",
      "value": "REF-1614677481537"
    },
    {
      "key": "linkref",
      "value": "REF-1614677481929"
    },
    {
      "key": "linkref",
      "value": "REF-1614685684136"
    },
    {
      "key": "linkref",
      "value": "REF-1614685684490"
    },
    {
      "key": "linkref",
      "value": "REF-1614687536271"
    },
    {
      "key": "linkref",
      "value": "REF-1614687536627"
    },
    {
      "key": "linkref",
      "value": "REF-1614688493891"
    },
    {
      "key": "linkref",
      "value": "REF-1614688494227"
    },
    {
      "key": "linkref",
      "value": "REF-1614688615922"
    },
    {
      "key": "linkref",
      "value": "REF-1614688616259"
    },
    {
      "key": "linkref",
      "value": "REF-1614689669742"
    },
    {
      "key": "linkref",
      "value": "REF-1614689670151"
    },
    {
      "key": "linkref",
      "value": "REF-1614695087258"
    },
    {
      "key": "linkref",
      "value": "REF-1614695087636"
    },
    {
      "key": "linkref",
      "value": "REF-1614695232130"
    },
    {
      "key": "linkref",
      "value": "REF-1614695232466"
    },
    {
      "key": "linkref",
      "value": "REF-1614696138661"
    },
    {
      "key": "linkref",
      "value": "REF-1614696139245"
    },
    {
      "key": "linkref",
      "value": "REF-1614696547822"
    },
    {
      "key": "linkref",
      "value": "REF-1614696548450"
    },
    {
      "key": "linkref",
      "value": "REF-1614779009971"
    },
    {
      "key": "linkref",
      "value": "REF-1614779015041"
    },
    {
      "key": "linkref",
      "value": "REF-1614783611972"
    },
    {
      "key": "linkref",
      "value": "REF-1614783612576"
    },
    {
      "key": "linkref",
      "value": "REF-1614858826142"
    },
    {
      "key": "linkref",
      "value": "REF-1614858826535"
    },
    {
      "key": "linkref",
      "value": "REF-1614861892672"
    },
    {
      "key": "linkref",
      "value": "REF-1614861892995"
    },
    {
      "key": "linkref",
      "value": "REF-1614873771867"
    },
    {
      "key": "linkref",
      "value": "REF-1614873772193"
    },
    {
      "key": "linkref",
      "value": "REF-1614874240011"
    },
    {
      "key": "linkref",
      "value": "REF-1614874240605"
    },
    {
      "key": "linkref",
      "value": "REF-1614956212034"
    },
    {
      "key": "linkref",
      "value": "REF-1614956212433"
    },
    {
      "key": "linkref",
      "value": "REF-1614956761814"
    },
    {
      "key": "linkref",
      "value": "REF-1614956762388"
    },
    {
      "key": "mandatereference",
      "value": "REF-1627463546650"
    },
    {
      "key": "mandatereference",
      "value": "REF-1627463574933"
    },
    {
      "key": "mandatereference",
      "value": "REF-1627463646773"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637758230837"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637758246333"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637758246666"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637758263349"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637759433028"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637759481627"
    },
    {
      "key": "linkref",
      "value": "REF-1638168537556"
    },
    {
      "key": "linkref",
      "value": "REF-1638168537976"
    },
    {
      "key": "linkref",
      "value": "REF-1638168563421"
    },
    {
      "key": "linkref",
      "value": "REF-1638171072599"
    },
    {
      "key": "linkref",
      "value": "REF-1638171121877"
    },
    {
      "key": "linkref",
      "value": "REF-1638171141861"
    },
    {
      "key": "linkref",
      "value": "REF-1638171142118"
    },
    {
      "key": "linkref",
      "value": "REF-1638172486630"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638172505362"
    },
    {
      "key": "linkref",
      "value": "REF-1638172894160"
    },
    {
      "key": "linkref",
      "value": "REF-1638172904232"
    },
    {
      "key": "linkref",
      "value": "REF-1638172941080"
    },
    {
      "key": "linkref",
      "value": "REF-1638172948735"
    },
    {
      "key": "linkref",
      "value": "REF-1638173001975"
    },
    {
      "key": "linkref",
      "value": "REF-1638173009795"
    },
    {
      "key": "linkref",
      "value": "REF-1638173019679"
    },
    {
      "key": "linkref",
      "value": "REF-1638173025535"
    },
    {
      "key": "linkref",
      "value": "REF-1638180081592"
    },
    {
      "key": "linkref",
      "value": "REF-1638180143213"
    },
    {
      "key": "linkref",
      "value": "REF-1638335434089"
    },
    {
      "key": "linkref",
      "value": "REF-1638335440708"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638335490120"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639135100909"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639135104790"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639135110742"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639135130282"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639141045325"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639141049525"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639141054205"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639141069168"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387429783"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387435604"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387442336"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387459902"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387547107"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639387567972"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639393314559"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639393319493"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639393325626"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639393340218"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639495383663"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639495385477"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639495408479"
    },
    {
      "key": "linkref",
      "value": "REF-1639634343360"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639740338536"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639740340056"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639740367083"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744147029"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744148511"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744171944"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744195563"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744196908"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639744224451"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639986862852"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639986867808"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639986873564"
    }
  ],
  "type": "transfer",
  "transactionStatus": "completed",
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
  "creationDate": "2021-12-22T07:40:54",
  "modificationDate": "2021-12-22T07:40:54",
  "requestDate": "2021-12-22T07:40:54"
}
```