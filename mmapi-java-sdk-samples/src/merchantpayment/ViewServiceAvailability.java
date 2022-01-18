package merchantpayment;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.ServiceAvailability;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

import base.SDKClient;

public class ViewServiceAvailability extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			
			System.out.println("Please wait...");
			ServiceAvailability serviceAvailability = mmClient.addRequest(new MerchantPaymentRequest()).viewServiceAvailability();
			
			System.out.println(String.format("Service Availability Status: %s", serviceAvailability.getServiceStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
