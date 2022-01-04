# View A Response

`Here, viewResponse(String clientCorrelationId, Class<T> objectReference) creates a GET request to /responses/{clientCorrelationId}`

> `This endpoint returns a specific response.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

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

AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
agentServiceRequest.setAccount(account);
AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createAccount();

String clientCorrelationId = agentServiceRequest.getClientCorrelationId();
Account accountResponse = mmClient.addRequest(agentServiceRequest).viewResponse(clientCorrelationId, Account.class);
```

### Response Example

```java
{
  "accountIdentifiers": [
    {
      "key": "msisdn",
      "value": "+441190827677"
    }
  ],
  "identity": [
    {
      "identityId": "314",
      "identityType": "individual",
      "identityStatus": "active",
      "identityKyc": {
        "nationality": "AD",
        "dateOfBirth": "2000-11-20",
        "occupation": "Miner",
        "employerName": "string",
        "contactPhone": "+447777777777",
        "gender": "m",
        "idDocument": [
          {
            "idType": "passport",
            "idNumber": "111111",
            "issueDate": "2018-11-20",
            "expiryDate": "2018-11-20",
            "issuer": "ABC",
            "issuerPlace": "DEF",
            "issuerCountry": "AD"
          }
        ],
        "postalAddress": {
          "addressLine1": "37",
          "addressLine2": "ABC Drive",
          "addressLine3": "string",
          "city": "Berlin",
          "stateProvince": "string",
          "postalCode": "AF1234",
          "country": "AD"
        },
        "subjectName": {
          "title": "Mr",
          "firstName": "H",
          "middleName": "I",
          "lastName": "J",
          "fullName": "H I J",
          "nativeName": "string"
        },
        "emailAddress": "xyz@xyz.com",
        "birthCountry": "AD"
      },
      "accountRelationship": "accountholder",
      "kycVerificationStatus": "verified",
      "kycVerificationEntity": "ABC Agent",
      "kycLevel": 1,
      "customData": [
        {
          "key": "test",
          "value": "custom"
        },
        {
          "key": "test",
          "value": "custom1"
        }
      ]
    }
  ],
  "accountType": "string",
  "accountStatus": "available",
  "accountSubStatus": "subStatus",
  "fees": [
    {
      "feeType": "string",
      "feeAmount": "5.46",
      "feeCurrency": "AED"
    }
  ],
  "registeringEntity": "ABC Agent",
  "creationDate": "2022-01-03T06:20:19",
  "requestDate": "2021-02-17T15:41:45"
}
```