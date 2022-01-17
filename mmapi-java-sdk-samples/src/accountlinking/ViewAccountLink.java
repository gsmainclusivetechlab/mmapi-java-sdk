package accountlinking;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;

import base.SDKClient;

public class ViewAccountLink extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));

			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("accountid", "15523"));

			System.out.println("Please wait...");
			Link linkResponse = mmClient.addRequest(new AccountLinkingRequest())
					.viewAccountLink(new Identifiers(identifierList), "REF-1642397088123");

			System.out.println(String.format("Account Link View Status: %s", linkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
