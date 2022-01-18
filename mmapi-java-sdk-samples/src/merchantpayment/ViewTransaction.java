package merchantpayment;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

import base.SDKClient;

public class ViewTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
			
			System.out.println("Please wait...");
			
			String transactionReference = "REF-1642147237565";
			Transaction transaction = mmClient.addRequest(merchantPaymentRequest).viewTransaction(transactionReference);
			
			System.out.println(String.format("Transaction Amount: %s", transaction.getAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
		
	}
}
