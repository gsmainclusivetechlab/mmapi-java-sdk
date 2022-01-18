package recurringpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.recurringpayment.model.DebitMandate;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

import base.SDKClient;

public class ViewAccountDebitMandate extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

			List<AccountIdentifier> identifierList = new ArrayList<>();

			identifierList.add(new AccountIdentifier("walletid", "1"));

			System.out.println("Please wait...");

			String debitMandateReference = "REF-1642175609824";
			DebitMandate debitMandateResponse = mmClient.addRequest(recurringPaymentRequest)
					.viewAccountDebitMandate(new Identifiers(identifierList), debitMandateReference);

			System.out.println(String.format("Debit Mandate Status: %s", debitMandateResponse.getMandateStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
