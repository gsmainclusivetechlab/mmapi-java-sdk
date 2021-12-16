# View Account Name

`Here, viewAccountName(Identifiers identifiers) creates a GET request to /accounts/{identifierType}/{identifier}/accountname`

> `This endpoint returns the name of an account holder.`

### Usage/Examples

```java
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier type value>"));
AccountHolderName accountHolderName = mmClient.addRequest(new P2PTransferRequest()).viewAccountName(new Identifiers(identifierList));
``` 

### Response Example

```java
{
  "name": {
    "title": "Mr",
    "firstName": "Jeff",
    "middleName": "James",
    "lastName": "Jimmer",
    "fullName": "Jeff Jimmer"
  },
  "lei": "AAAA0012345678901299"
}
```