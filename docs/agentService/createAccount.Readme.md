# Create an Account

`Here, createAccount() creates a POST request to /accounts/{identityType}`

> `Provided with a valid object representation, this endpoint allows for a new account to be created.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

Account account = new Account();

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("msisdn", "+440123456789"));

List<Identity> identityList = new ArrayList<>();
Identity identity = new Identity();

List<CustomData> customDataList = new ArrayList<>();
customDataList.add(new CustomData("test", "custom"));

List<CustomData> customDataList1 = new ArrayList<>();
customDataList.add(new CustomData("test", "custom1"));

KYCInformation senderKyc = new KYCInformation();
List<IdDocument> identificationList = new ArrayList<>();

IdDocument idDocument = new IdDocument("passport");
idDocument.setIdType("passport");
idDocument.setIdNumber("111111");
idDocument.setIssuer("ABC");
idDocument.setIssuerPlace("DEF");
idDocument.setIssuerCountry("AD");
idDocument.setIssueDate("2018-11-20");
idDocument.setExpiryDate("2018-11-20");
identificationList.add(idDocument);

Address address = new Address("AD");
address.setAddressLine1("37");
address.setAddressLine2("ABC Drive");
address.setAddressLine3("string");
address.setCity("Berlin");
address.setStateProvince("string");
address.setPostalCode("AF1234");
address.setCountry("AD");

Name kycSubject = new Name();
kycSubject.setTitle("Mr");
kycSubject.setFirstName("H");
kycSubject.setMiddleName("I");
kycSubject.setLastName("J");
kycSubject.setFullName("H I J");
kycSubject.setNativeName("string");

senderKyc.setNationality("AD");
senderKyc.setBirthCountry("AD");
senderKyc.setOccupation("Miner");
senderKyc.setEmployerName("string");
senderKyc.setContactPhone("+447777777777");
senderKyc.setGender("m");
senderKyc.setDateOfBirth("2000-11-20");
senderKyc.setEmailAddress("xyz@xyz.com");
senderKyc.setIdDocument(identificationList);
senderKyc.setPostalAddress(address);
senderKyc.setSubjectName(kycSubject);

identity.setAccountRelationship("accountholder");
identity.setCustomData(customDataList);
identity.setIdentityKyc(senderKyc);
identity.setKycLevel(1);
identity.setKycVerificationEntity("ABC Agent");
identity.setKycVerificationStatus("verified");
identityList.add(identity);

List<Fees> feesList = new ArrayList<>();
Fees fees = new Fees();
fees.setFeeType("string");
fees.setFeeAmount("5.46");
fees.setFeeCurrency("AED");
feesList.add(fees);

account.setAccountIdentifiers(identifierList);
account.setIdentity(identityList);
account.setAccountType("string");
account.setCustomData(customDataList1);
account.setFees(feesList);
account.setRegisteringEntity("ABC Agent");
account.setRequestDate("2021-02-17T15:41:45.194Z");

agentServiceRequest.setAccount(getRequestAccountObject());
AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createAccount();
```

Additionally, if you want to use account details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

String accountJsonString = "{\"accountIdentifiers\": [{\"key\": \"msisdn\",\"value\": \"+44" + getPhoneNumber() + "\"}],\"identity\": [{\"identityKyc\": {\"birthCountry\": \"AD\",\"contactPhone\": \"+447777777777\",\"dateOfBirth\": \"2000-11-20\",\"emailAddress\": \"xyz@xyz.com\",\"employerName\": \"string\",\"gender\": \"m\",\"nationality\": \"AD\",\"occupation\": \"Miner\",\"postalAddress\": {\"addressLine1\": \"37\",\"addressLine2\": \"ABC Drive\",\"addressLine3\": \"string\",\"city\": \"Berlin\",\"stateProvince\": \"string\",\"postalCode\": \"AF1234\",\"country\": \"AD\"},\"subjectName\": {\"title\": \"Mr\",\"firstName\": \"H\",\"middleName\": \"I\",\"lastName\": \"J\",\"fullName\": \"H I J\",\"nativeName\": \"string\"},\"idDocument\": [{\"idType\": \"passport\",\"idNumber\": \"111111\",\"issueDate\": \"2018-11-20\",\"expiryDate\": \"2018-11-20\",\"issuer\": \"ABC\",\"issuerPlace\": \"DEF\",\"issuerCountry\": \"AD\"}]},\"accountRelationship\": \"accountholder\",\"kycVerificationStatus\": \"verified\",\"kycVerificationEntity\": \"ABC Agent\",\"kycLevel\": 1,\"customData\": [{\"key\": \"test\",\"value\": \"custom\"},{\"key\": \"test\",\"value\": \"custom1\"}]}],\"accountType\": \"string\",\"customData\": [],\"fees\": [{\"feeType\": \"string\",\"feeAmount\": \"5.46\",\"feeCurrency\": \"AED\"}],\"registeringEntity\": \"ABC Agent\",\"requestDate\": \"2021-02-17T15:41:45.194Z\"}";

agentServiceRequest.setAccount(accountJsonString);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createAccount();
```

### Response Example

```java
{
  "serverCorrelationId": "ac6d6e38-7322-43cf-a4bb-57305a5bfc6e",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "268",
  "pollLimit": 100
}
```