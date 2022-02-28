InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

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

internationalTransferRequest.setQuotation(quotation);

MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest).addCallBack("<Place your callback URL>").createQuotation();

sdkResponse = mmClient.addRequest(internationalTransferRequest).viewRequestState(sdkResponse.getServerCorrelationId());

String qtnRef = sdkResponse.getObjectReference();
if (qtnRef == null) qtnRef = "REF-1637057900018";
 
Quotation quotation = mmClient.addRequest(internationalTransferRequest).viewQuotation(qtnRef);