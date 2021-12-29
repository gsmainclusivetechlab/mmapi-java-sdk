# Update A Transaction Batch

`Here, updateBatchTransaction(String batchId) creates a PATCH request to /batchtransactions/{batchId}`

> `This endpoint updates a specific transaction batch. Only the batchStatus field can be modified.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<PatchData> patchDataList = new ArrayList<>();

patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));

disbursementRequest.setPatchData(patchDataList);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").updateBatchTransaction("batch reference");
``` 

### Response Example

```java
{
  "serverCorrelationId": "e19cc34d-290b-4b8c-9f33-c9ef9fb350dd",
  "status": "pending",
  "notificationMethod": "callback",
  "objectReference": "1624",
  "pollLimit": 100
}
```

### NOTE

In asynchronous flows, a callback mechanism or polling mechanism is utilised to allow the client to determine the request's final state.
Use the <a href="viewRequestState.Readme.md">viewRequestState()</a> function for the polling mechanism to receive the status of a request, and the <a href="viewBatchTransaction.Readme.md">viewBatchTransaction()</a>
function to acquire the final representation of the Transaction object.