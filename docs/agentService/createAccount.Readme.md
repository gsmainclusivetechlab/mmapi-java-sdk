# Create an Account

`Here, createAccount() creates a POST request to /accounts/{identityType}`

> `Provided with a valid object representation, this endpoint allows for a new account to be created.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
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