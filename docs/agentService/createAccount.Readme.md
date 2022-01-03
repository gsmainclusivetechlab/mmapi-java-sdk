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