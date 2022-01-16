package p2ptransfer;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Balance;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class ViewAccountBalance extends SDKClient {

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
			Balance balance = mmClient.addRequest(new P2PTransferRequest())
					.viewAccountBalance(new Identifiers(identifierList));

			System.out.println(String.format("Available Balance: %s", balance.getAvailableBalance()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
