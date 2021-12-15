# Create A New Quotation

`Here, createQuotation() creates a POST request to /quotations`

> `Provided with a valid object representation, this endpoint allows for a new quotation to be created.`

### Usage/Examples


```java
IdDocument idDocument = new IdDocument("nationalidcard");
Address address = new Address("GB");
Name kycSubject = new Name();
KYCInformation senderKyc = new KYCInformation();

List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();
List<CustomData> customDataList = new ArrayList<>();
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

debitPartyList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
creditPartyList.add(new AccountIdentifier("accountid", "<Place your account id of credit party here>"));
customDataList.add(new CustomData("keytest", "keyvalue"));

Quotation quotation = new Quotation("16.00", "USD", creditPartyList, debitPartyList);
quotation.setSubType("abc");
quotation.setChosenDeliveryMethod("agent");
quotation.setSendingServiceProviderCountry("AD");
quotation.setOriginCountry("AD");
quotation.setReceivingCountry("AD");
quotation.setRequestDate("2018-07-03T11:43:27.405Z");
quotation.setSenderKyc(senderKyc);
quotation.setCustomData(customDataList);

P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

p2PTransferRequest.setQuotation(quotation);

AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack("<Place your callback URL>").createQuotation();
```

### Response Example

```java
{
  "serverCorrelationId": "6ba7cd5b-cb56-4b22-a682-0ccd09182d1c",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "1469",
  "pollLimit": 100
}
```