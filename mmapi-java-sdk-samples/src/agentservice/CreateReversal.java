package agentservice;

import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Reversal;

import base.SDKClient;

public class CreateReversal extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

			Reversal reversal = new Reversal();
			reversal.setType("reversal");
			agentServiceRequest.setReversal(reversal);

			String transactionReference = "REF-1635251574104";

			System.out.println("Please wait...");
			AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(get("CALLBACK_URL"))
					.createReversal(transactionReference);

			System.out.println(String.format("Transaction Reversal Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
