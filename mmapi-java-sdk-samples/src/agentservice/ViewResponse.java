package agentservice;

import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.Transaction;

import base.SDKClient;

public class ViewResponse extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AgentServiceRequest agentServiceRequest = new AgentServiceRequest();

			String clientCorrelationId = "56647bb2-24e7-43d5-8aa6-b70f568d53c2";

			System.out.println("Please wait...");
			Transaction transaction = mmClient.addRequest(agentServiceRequest).viewResponse(clientCorrelationId,
					Transaction.class);

			System.out.println(String.format("Transaction Amount: %s", transaction.getAmount()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
