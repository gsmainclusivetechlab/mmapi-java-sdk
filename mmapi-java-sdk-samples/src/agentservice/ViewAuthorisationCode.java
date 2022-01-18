package agentservice;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AuthorisationCode;
import com.mobilemoney.common.model.Identifiers;

import base.SDKClient;

public class ViewAuthorisationCode  extends SDKClient {
	
	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("walletid", "1"));
			
			System.out.println("Please wait...");
			
			AuthorisationCode authorisationCode = mmClient.addRequest(agentServiceRequest).viewAuthorisationCode(new Identifiers(identifierList), "61a0f8e6-e813-4412-957e-4c10f0d7a677");
			
			System.out.println(String.format("Authorisation Code: %s", authorisationCode.getAuthorisationCode()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
