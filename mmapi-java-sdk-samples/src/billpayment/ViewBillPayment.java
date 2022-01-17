package billpayment;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.billpayment.model.BillPay;
import com.mobilemoney.billpayment.model.BillPayments;
import com.mobilemoney.billpayment.model.Bills;
import com.mobilemoney.billpayment.request.BillPaymentRequest;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.Identifiers;

import base.SDKClient;

public class ViewBillPayment extends SDKClient {

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

			Bills bills = mmClient.addRequest(billPaymentRequest).viewAccountBills(new Identifiers(identifierList));

			BillPay billPayment = new BillPay();
			billPayment.setCurrency("USD");
			billPayment.setAmountPaid("16.00");
			
			billPaymentRequest.setBillPayment(billPayment);
			
			System.out.println("Please wait...");

			AsyncResponse sdkResponse = mmClient.addRequest(billPaymentRequest).addCallBack(get("CALLBACK_URL"))
					.createBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());
			
			sdkResponse = mmClient.addRequest(billPaymentRequest).viewRequestState(sdkResponse.getServerCorrelationId());

			BillPayments billPayments = mmClient.addRequest(billPaymentRequest)
					.viewBillPayment(new Identifiers(identifierList), bills.getBills().get(0).getBillReference());

			if (billPayments != null)
				System.out.println(String.format("Bill Payments Count: %d", billPayments.getBillPayments().size()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
