package p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class ViewTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

			System.out.println("Please wait...");

			String transactionReference = "REF-1636106992007";
			Transaction transaction = mmClient.addRequest(p2PTransferRequest).viewTransaction(transactionReference);

			System.out.println(String.format("Transaction Amount: %s", transaction.getAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}

	}
}
