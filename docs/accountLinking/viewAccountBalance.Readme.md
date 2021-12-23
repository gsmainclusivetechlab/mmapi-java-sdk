# View Account Balance

`Here, viewAccountBalance(Identifiers identifiers) creates a GET request to /accounts/{identifierType}/{identifier}/balance`

> `This endpoint returns the balance of an account.`

### Usage/Examples

```java
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier type value>"));

Balance balance = mmClient.addRequest(new AccountLinkRequest()).viewAccountBalance(new Identifiers(identifierList));
```

### Response Example

```java
{
  "currentBalance": "0.00",
  "availableBalance": "0.00",
  "reservedBalance": "0.00",
  "unclearedBalance": "0.00",
  "accountStatus": "available"
}
```