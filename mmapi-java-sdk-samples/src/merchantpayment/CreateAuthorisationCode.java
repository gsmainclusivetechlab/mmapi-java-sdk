package merchantpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.merchantpayment.request.MerchantPaymentRequest;

import base.SDKClient;

public class CreateAuthorisationCode  extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
			AuthorisationCode authorisationCode = new AuthorisationCode();
			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("accountid", "2000"));

			authorisationCode.setCodeLifetime(5);
			authorisationCode.setAmount("17.00");
			authorisationCode.setCurrency("USD");

			merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

			System.out.println("Please wait...");
			
			AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack(get("CALLBACK_URL")).createAuthorisationCode(new Identifiers(identifierList));
		
			System.out.println(String.format("Create Authorisation Code Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
