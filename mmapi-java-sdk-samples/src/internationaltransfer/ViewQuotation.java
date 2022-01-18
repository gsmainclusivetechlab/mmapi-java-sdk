package internationaltransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.internationaltransfer.request.InternationalTransferRequest;

import base.SDKClient;

public class ViewQuotation extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			
			System.out.println("Please wait...");
			Quotation quotation = mmClient.addRequest(new InternationalTransferRequest()).viewQuotation("REF-1637057900018");
			
			System.out.println(String.format("Quotation Requested Amount: %s", quotation.getRequestAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
