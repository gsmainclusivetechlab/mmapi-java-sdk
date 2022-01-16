package p2ptransfer;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

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
			Quotation quotation = mmClient.addRequest(new P2PTransferRequest()).viewQuotation("REF-1637057900018");

			System.out.println(String.format("Quotation Requested Amount: %s", quotation.getRequestAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
