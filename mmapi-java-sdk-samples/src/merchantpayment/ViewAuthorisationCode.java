package merchantpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

import base.SDKClient;

public class ViewAuthorisationCode  extends SDKClient {
	
	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("accountid", "2999"));
			
			System.out.println("Please wait...");
			
			AuthorisationCode authorisationCode = mmClient.addRequest(merchantPaymentRequest).viewAuthorisationCode(new Identifiers(identifierList), "2b68c2a7-e0ef-4fa8-b180-ec092993016c");
			
			System.out.println(String.format("Authorisation Code: %s", authorisationCode.getAuthorisationCode()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
