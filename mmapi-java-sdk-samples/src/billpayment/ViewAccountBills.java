package billpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.billpayment.model.Bills;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;

import base.SDKClient;

public class ViewAccountBills extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			BillPaymentRequest billPaymentRequest = new BillPaymentRequest();
			
			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("walletid", "1"));

			System.out.println("Please wait...");

			Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

			if (bills != null)
				System.out.println(String.format("Bills Count: %d", bills.getBills().size()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
