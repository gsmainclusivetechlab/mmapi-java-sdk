package recurringpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.recurringpayment.model.DebitMandate;
import com.mobilemoney.recurringpayment.request.RecurringPaymentRequest;

import base.SDKClient;

public class CreateAccountDebitMandate extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));

			DebitMandate debitMandate = new DebitMandate();
			List<AccountIdentifier> payee = new ArrayList<>();
			List<CustomData> customData = new ArrayList<>();

			payee.add(new AccountIdentifier("walletid", "1"));
			customData.add(new CustomData("keytest", "keyvalue"));

			debitMandate.setRequestDate("2018-07-03T10:43:27.405Z");
			debitMandate.setStartDate("2018-07-03T10:43:27.405Z");
			debitMandate.setEndDate("2028-07-03T10:43:27.405Z");
			debitMandate.setCurrency("USD");
			debitMandate.setAmountLimit("1000.00");
			debitMandate.setNumberOfPayments(2);
			debitMandate.setFrequencyType("sixmonths");
			debitMandate.setPayee(payee);
			debitMandate.setCustomData(customData);

			RecurringPaymentRequest recurringPaymentRequest = new RecurringPaymentRequest();

			List<AccountIdentifier> identifierList = new ArrayList<>();
			identifierList.add(new AccountIdentifier("walletid", "1"));

			recurringPaymentRequest.setDebitMandate(debitMandate);

			System.out.println("Please wait...");

			AsyncResponse sdkResponse = mmClient.addRequest(recurringPaymentRequest)
					.createAccountDebitMandate(new Identifiers(identifierList));

			System.out.println(String.format("Create Account Debit Mandate Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
