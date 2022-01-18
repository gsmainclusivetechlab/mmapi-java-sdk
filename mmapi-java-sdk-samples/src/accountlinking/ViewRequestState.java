package accountlinking;

import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;

import base.SDKClient;

public class ViewRequestState extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

			System.out.println("Please wait...");

			String serverCorrelationId = "efa39ae1-04d8-4511-b350-2ead2c8f6cd0";
			AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest).viewRequestState(serverCorrelationId);

			System.out.println(String.format("Request State Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
