# View A Request State

`Here, viewRequestState(String serverCorrelationId) creates a GET request to /requeststates/{serverCorrelationId}`

> `This endpoint returns a specific request state.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Transaction transaction = new Transaction();
transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<Amount>");
transaction.setCurrency("<Currency>");

agentServiceRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createWithdrawalTransaction();

sdkResponse = mmClient.addRequest(agentServiceRequest).viewRequestState(sdkResponse.getServerCorrelationId());
```

### Response Example

```java
{
  "serverCorrelationId": "c1ee4902-ca72-4f65-8ae6-f54538a31043",
  "status": "completed",
  "notificationMethod": "callback",
  "objectReference": "REF-1641164668812",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the transaction object.