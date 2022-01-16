package p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class ViewRequestState extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();

			System.out.println("Please wait...");

			String serverCorrelationId = "efa39ae1-04d8-4511-b350-2ead2c8f6cd0";
			AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).viewRequestState(serverCorrelationId);

			System.out.println(String.format("Request State Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
