package p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Reversal;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class CreateReversal extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

			Reversal reversal = new Reversal();
			reversal.setType("reversal");
			p2PTransferRequest.setReversal(reversal);

			String transactionReference = "REF-1635251574104";

			System.out.println("Please wait...");
			AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack(get("CALLBACK_URL"))
					.createReversal(transactionReference);

			System.out.println(String.format("Transaction Reversal Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
