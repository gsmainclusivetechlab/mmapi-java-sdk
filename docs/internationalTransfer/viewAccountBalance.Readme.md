# View Account Balance

`Here, viewAccountBalance(Identifiers identifiers) creates a GET request to /accounts/{identifierType}/{identifier}/balance`

> `This endpoint returns the balance of an account.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Balance balance = mmClient.addRequest(new InternationalTransferRequest()).viewAccountBalance(new Identifiers(identifierList));
``` 

### Response Example

```java
{
  "currentBalance": "1000000000.00",
  "availableBalance": "0.00",
  "reservedBalance": "0.00",
  "unclearedBalance": "0.00",
  "currency": "GBP",
  "accountStatus": "available"
}
```