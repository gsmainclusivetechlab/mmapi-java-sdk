# Create an Authorisation Code via an account identifier

`Here, createAuthorisationCode(Identifiers identifiers) creates a POST request to /accounts/{identifierType}/{identifier}/authorisationcodes`

> `This endpoint allows allows a mobile money payer or payee to generate a code which when presented to the other party, can be redeemed for an amount set by the payer or payee, depending upon the use case.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
AuthorisationCode authorisationCode = new AuthorisationCode();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

authorisationCode.setCodeLifetime("<code expiry time in seconds>");
authorisationCode.setAmount("<amount>");
authorisationCode.setCurrency("<currency>");

agentServiceRequest.setAuthorisationCodeRequest(authorisationCode);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));
```

### Response Example

```java
{
  "serverCorrelationId": "8c8d04ee-24b9-4eb9-8b7a-c6d0d94dc842",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "2514",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAuthorisationCode.Readme.md">viewAuthorisationCode()</a>
function to acquire the final representation of the Transaction object.