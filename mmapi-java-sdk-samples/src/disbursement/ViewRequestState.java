package disbursement;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewRequestState extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			DisbursementRequest disbursementRequest = new DisbursementRequest();

			System.out.println("Please wait...");

			String serverCorrelationId = "ea495e98-b5d2-4b03-ba43-4dfbce39cc60";
			AsyncResponse sdkResponse = mmClient.addRequest(disbursementRequest)
					.viewRequestState(serverCorrelationId);
			
			System.out.println(String.format("Request State Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
