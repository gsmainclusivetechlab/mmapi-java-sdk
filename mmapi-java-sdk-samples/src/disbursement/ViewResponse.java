package disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewResponse extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			DisbursementRequest disbursementRequest = new DisbursementRequest();
			
			String clientCorrelationId = "56647bb2-24e7-43d5-8aa6-b70f568d53c2";
			
			System.out.println("Please wait...");
			Transaction transaction = mmClient.addRequest(disbursementRequest).viewResponse(clientCorrelationId, Transaction.class);
		
			System.out.println(String.format("Transaction Amount: %s", transaction.getAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
