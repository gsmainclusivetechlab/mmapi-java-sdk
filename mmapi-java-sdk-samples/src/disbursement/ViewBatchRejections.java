package disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.disbursement.model.BatchRejections;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewBatchRejections extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			
			System.out.println("Please wait...");
			BatchRejections rejectedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchRejections("REF-1635751208477");
			System.out.println(String.format("Batch Rejected Transactions: %d", rejectedTransactions.getBatchRejections().size()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
