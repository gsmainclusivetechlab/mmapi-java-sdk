package disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.disbursement.model.BatchCompletions;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewBatchCompletions extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			
			System.out.println("Please wait...");
			BatchCompletions completedTransactions = mmClient.addRequest(new DisbursementRequest()).viewBatchCompletions("REF-1635751208477");
			
			System.out.println(String.format("Batch Completed Transactions: %d", completedTransactions.getBatchCompletions().size()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
