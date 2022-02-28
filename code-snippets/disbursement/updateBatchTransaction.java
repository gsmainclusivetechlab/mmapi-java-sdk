MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
DisbursementRequest disbursementRequest = new DisbursementRequest();
List<PatchData> patchDataList = new ArrayList<>();

patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));

disbursementRequest.setPatchData(patchDataList);
AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack("<Place your callback URL>").updateBatchTransaction("batch reference");