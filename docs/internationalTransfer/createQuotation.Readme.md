# Create A New Quotation

`Here, createQuotation() creates a POST request to /quotations`

> `Provided with a valid object representation, this endpoint allows for a new quotation to be created.`

### Usage/Examples

1. Initialize quotation object
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

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
customDataList.add(new CustomData("<data key>", "<data value>"));

Quotation quotation = new Quotation("<amount>", "<currency>", creditPartyList, debitPartyList);
quotation.setSubType("abc");
quotation.setChosenDeliveryMethod("agent");
quotation.setSendingServiceProviderCountry("AD");
quotation.setOriginCountry("AD");
quotation.setReceivingCountry("AD");
quotation.setRequestDate("2018-07-03T11:43:27.405Z");
quotation.setSenderKyc(senderKyc);
quotation.setCustomData(customDataList);
``` 

2. Request a quotation to perform international transfer.

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

internationalTransferRequest.setQuotation(quotation);

AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place your callback URL>").createQuotation();
```

Additionally, if you want to use quotation details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

String quotationJsonString = "{\"subType\": \"abc\",\"requestAmount\": \"75.30\",\"requestCurrency\": \"RWF\",\"chosenDeliveryMethod\": \"agent\",\"originCountry\": \"AD\",\"receivingCountry\": \"AD\",\"sendingServiceProviderCountry\": \"AD\",\"requestDate\": \"2018-07-03T11:43:27.405Z\",\"senderKyc\": {\"birthCountry\": \"GB\",\"contactPhone\": \"+447125588999\",\"dateOfBirth\": \"1970-07-03T11:43:27.405Z\",\"emailAddress\": \"luke.skywalkeraaabbb@gmail.com\",\"employerName\": \"MFX\",\"gender\": \"m\",\"nationality\": \"GB\",\"occupation\": \"Manager\",\"postalAddress\": {\"country\": \"GB\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"Luke\",\"middleName\": \"R\",\"lastName\": \"Skywalker\",\"fullName\": \"Luke R Skywalker\",\"nativeName\": \"ABC\"},\"idDocument\": [{\"idType\": \"nationalidcard\",\"idNumber\": \"1234567\",\"issueDate\": \"2018-07-03T11:43:27.405Z\",\"expiryDate\": \"2021-07-03T11:43:27.405Z\",\"issuer\": \"UKPA\",\"issuerPlace\": \"GB\",\"issuerCountry\": \"GB\",\"otherIddescription\": \"test\"}]},\"customData\": [{\"key\": \"keytest\",\"value\": \"keyvalue\"}],\"creditParty\": [{\"key\": \"accountid\",\"value\": \"2000\"}],\"debitParty\": [{\"key\": \"accountid\",\"value\": \"2999\"}]}";

internationalTransferRequest.setQuotation(quotationJsonString);

AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place your callback URL>").createQuotation();
```

### Response Example

```java
{
  "serverCorrelationId": "a9e3bbe1-e15b-49eb-a800-396618ea2659",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "1468",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewQuotation.Readme.md">viewQuotation()</a>
function to acquire the final representation of the Transaction object.