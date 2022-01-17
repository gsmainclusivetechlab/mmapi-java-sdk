package recurringpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

import base.SDKClient;

public class CreateMerchantTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

			List<AccountIdentifier> debitPartyList = new ArrayList<>();
	        List<AccountIdentifier> creditPartyList = new ArrayList<>();

	        debitPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));
	        creditPartyList.add(new AccountIdentifier("walletid", "1"));

	        Transaction transaction = new Transaction();
	        transaction.setDebitParty(debitPartyList);
	        transaction.setCreditParty(creditPartyList);
	        transaction.setAmount("16.00");
	        transaction.setCurrency("USD");
	        
	        recurringPaymentRequest.setTransaction(transaction);

			System.out.println("Please wait...");
			AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest).addCallBack(get("CALLBACK_URL"))
					.createMerchantTransaction();
		
			System.out.println(String.format("Create Merchant Transaction Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}

