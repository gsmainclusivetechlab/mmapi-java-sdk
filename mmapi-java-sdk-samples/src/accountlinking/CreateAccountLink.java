package accountlinking;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.accountlinking.model.Link;
import com.mobilemoney.accountlinking.request.AccountLinkingRequest;
import com.mobilemoney.base.constants.Mode;
import com.mobilemoney.base.constants.Status;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.RequestingOrganisation;

import base.SDKClient;

public class CreateAccountLink extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AccountLinkingRequest accountLinkingRequest = new AccountLinkingRequest();

			List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
			RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
			List<CustomData> customDataList = new ArrayList<>();

			sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "2999"));

			customDataList.add(new CustomData("keytest", "keyvalue"));

			requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
			requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");

			Link link = new Link();
			link.setSourceAccountIdentifiers(sourceAccountIdentifiers);
			link.setMode(Mode.BOTH.getMode());
			link.setStatus(Status.ACTIVE.getStatus());
			link.setRequestingOrganisation(requestingOrganisation);
			link.setRequestDate("2018-07-03T11:43:27.405Z");
			link.setCustomData(customDataList);

			accountLinkingRequest.setLink(link);

			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("accountid", "15523"));

			System.out.println("Please wait...");

			AsyncResponse sdkResponse = mmClient.addRequest(accountLinkingRequest)
					.createAccountLink(new Identifiers(identifierList));

			System.out.println(String.format("Account Link Creation Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
