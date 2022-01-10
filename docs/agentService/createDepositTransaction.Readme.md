# Create a Deposit Transaction

`Here, createDepositTransaction() creates a POST request to /transactions/type/deposit`

> `Provided with a valid object representation, this endpoint allows for a new transaction to be created for a given transaction type passed via the URL.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");

List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));
AccountHolderName accountHolderName = mmClient.addRequest(new AgentServiceRequest()).viewAccountName(new Identifiers(identifierList));

AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
Transaction transaction = new Transaction();
List<AccountIdentifier> debitPartyList = new ArrayList<>();
List<AccountIdentifier> creditPartyList = new ArrayList<>();

debitPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));
creditPartyList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

transaction.setDebitParty(debitPartyList);
transaction.setCreditParty(creditPartyList);
transaction.setAmount("<amount>");
transaction.setCurrency("<currency>");
agentServiceRequest.setTransaction(transaction);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createDepositTransaction();
```

Additionally, if you want to use transaction details as JSON string, you can use the following code;

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

String transactionObjectString = "{\"amount\": \"16.00\",\"currency\": \"USD\",\"debitParty\": [{\"key\": \"msisdn\",\"value\": \"+44012345678\"}],\"creditParty\": [{\"key\": \"walletid\",\"value\": \"1\"}],\"fees\": [],\"customData\": [],\"metadata\": []}";

agentServiceRequest.setTransaction(transactionObjectString);

AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack("<Place your callback URL>").createDepositTransaction();
```

### Response Example

```java
{
  "serverCorrelationId": "279601fb-c44e-4718-a7be-1f39c894fa1c",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "20537",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewTransaction.Readme.md">viewTransaction()</a>
function to acquire the final representation of the Transaction object.