package disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.disbursement.model.BatchTransaction;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewBatchTransaction extends SDKClient {
	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			DisbursementRequest disbursementRequest = new DisbursementRequest();
			
			String transactionReference = "REF-1635751208477";
			
			System.out.println("Please wait...");
			BatchTransaction batchResponse = mmClient.addRequest(disbursementRequest).viewBatchTransaction(transactionReference);
		
			System.out.println(String.format("Batch Status: %s", batchResponse.getBatchStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
