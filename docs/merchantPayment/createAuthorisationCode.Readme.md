# Create an Authorisation Code via an account identifier

`Here, createAuthorisationCode(Identifiers identifiers) creates a POST request to /accounts/{identifierType}/{identifier}/authorisationcodes`

> `This endpoint allows allows a mobile money payer or payee to generate a code which when presented to the other party, can be redeemed for an amount set by the payer or payee, depending upon the use case.`

### Usage/Examples

```java
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
AuthorisationCode authorisationCode = new AuthorisationCode();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier type value>"));

authorisationCode.setCodeLifetime("<code expiry time in seconds>");
authorisationCode.setAmount("<amount>");
authorisationCode.setCurrency("<currency>");

merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));
```

### Response Example

```java
{
  "serverCorrelationId": "c1e712f2-f03d-4fd8-b319-e742608cd0df",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "2280",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewAuthorisationCode.Readme.md">viewAuthorisationCode()</a>
function to acquire the final representation of the Transaction object.