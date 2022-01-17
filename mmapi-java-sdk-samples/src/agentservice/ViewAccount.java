package agentservice;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.agentservices.model.Account;
import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;

import base.SDKClient;

public class ViewAccount extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));

			List<AccountIdentifier> identifierList = new ArrayList<>();
			identifierList.add(new AccountIdentifier("walletid", "1"));

			System.out.println("Please wait...");

			Account accountViewed = mmClient.addRequest(new AgentServiceRequest())
					.viewAccount(new Identifiers(identifierList));

			System.out.println(String.format("Account Status: %s", accountViewed.getAccountStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
