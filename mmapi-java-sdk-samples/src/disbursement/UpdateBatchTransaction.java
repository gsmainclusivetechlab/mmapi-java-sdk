package disbursement;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class UpdateBatchTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			DisbursementRequest disbursementRequest = new DisbursementRequest();
			List<PatchData> patchDataList = new ArrayList<>();
			
			System.out.println("Please wait...");
			patchDataList.add(new PatchData("replace", "/batchStatus", "approved"));
			disbursementRequest.setPatchData(patchDataList);
			
			AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest).addCallBack(get("CALLBACK_URL")).updateBatchTransaction("REF-1635847150151");
			System.out.println(String.format("Batch Updated Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
