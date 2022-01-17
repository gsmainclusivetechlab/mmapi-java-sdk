package billpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Transaction;

import base.SDKClient;

public class CreateBillTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			BillPaymentRequest billPaymentRequest = new BillPaymentRequest();

			List<AccountIdentifier> debitPartyList = new ArrayList<>();
			List<AccountIdentifier> creditPartyList = new ArrayList<>();

			debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
			creditPartyList.add(new AccountIdentifier("walletid", "1"));

			Transaction transaction = new Transaction();
			transaction.setDebitParty(debitPartyList);
			transaction.setCreditParty(creditPartyList);
			transaction.setAmount("16.00");
			transaction.setCurrency("USD");
			
			billPaymentRequest.setTransaction(transaction);

			System.out.println("Please wait...");

			AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(get("CALLBACK_URL"))
					.createBillTransaction();

			System.out.println(String.format("Transaction Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
