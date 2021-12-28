# Check API availability

`Here, viewServiceAvailability() creates a GET request to /heartbeat`

> `This endpoint returns the current status of the API.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
ServiceAvailability serviceAvailability = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();
``` 

### Response Example

```java
{
  "serviceStatus": "available"
}
```