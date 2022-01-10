# View Account Bills

`Here, viewAccountBills(Identifiers identifiers) creates a GET request to /accounts/{identifierType}/{identifier}/bills`

> `This endpoint returns bills linked to an account.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));
```

### Response Example

```java
[
  {
    "billReference": "string",
    "billStatus": "unpaid",
    "amountDue": "15.00",
    "currency": "AED",
    "billdescription": "string",
    "dueDate": "2018-11-20",
    "minimumAmountDue": "15.00",
    "creationDate": "2021-12-28T08:25:21.385Z",
    "modificationDate": "2021-12-28T08:25:21.385Z",
    "customData": [
      {
        "key": "string",
        "value": "string"
      }
    ],
    "metadata": [
      {
        "key": "string",
        "value": "string"
      }
    ]
  }
]
```