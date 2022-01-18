package p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class ViewResponse extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

			String clientCorrelationId = "56647bb2-24e7-43d5-8aa6-b70f568d53c2";

			System.out.println("Please wait...");
			Transaction transaction = mmClient.addRequest(p2PTransferRequest).viewResponse(clientCorrelationId,
					Transaction.class);

			System.out.println(String.format("Transaction Amount: %s", transaction.getAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
