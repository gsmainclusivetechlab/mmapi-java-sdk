package disbursement;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.TransactionFilter;
import com.mobilemoney.common.model.Transactions;
import com.mobilemoney.disbursement.request.DisbursementRequest;

import base.SDKClient;

public class ViewAccountTransactions extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			
			TransactionFilter filter = new TransactionFilter();
	        List<AccountIdentifier> identifierList = new ArrayList<>();

	        identifierList.add(new AccountIdentifier("accountid", "2999"));
	        filter.setLimit(10);
	        filter.setOffset(0);

	        System.out.println("Please wait...");
	        Transactions transactions = mmClient.addRequest(new DisbursementRequest()).viewAccountTransactions(new Identifiers(identifierList), filter);
			
	        if (transactions != null)
	        	System.out.println(String.format("Transactions Count: %d", transactions.getTransactions().size()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
