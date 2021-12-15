# Check API availability

`Here, viewServiceAvailability() creates a GET request to /heartbeat`

> `This endpoint returns the current status of the API.`

### Usage/Examples

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new InternationalTransferRequest()).viewServiceAvailability();
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