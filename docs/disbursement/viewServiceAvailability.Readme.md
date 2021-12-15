# Check API availability

`Here, viewServiceAvailability() creates a GET request to /heartbeat`

> `This endpoint returns the current status of the API.`

### Usage/Examples

```java
ServiceStatusResponse serviceStatusResponse = mmClient.addRequest(new DisbursementRequest()).viewServiceAvailability();
``` 

### Response Example

```java
{
  "serviceStatus": "available"
}
```