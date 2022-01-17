package recurringpayment;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

import base.SDKClient;

public class ViewRequestState extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

			System.out.println("Please wait...");

			String serverCorrelationId = "4752de2d-1617-448f-8bbe-6ed758ee1e3b";
			AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
					.viewRequestState(serverCorrelationId);
			
			System.out.println(String.format("Request State Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
