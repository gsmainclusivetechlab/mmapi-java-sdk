package p2ptransfer;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.InternationalTransferInformation;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.RequestingOrganisation;
import com.mobilemoney.common.model.Transaction;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.p2ptransfer.request.P2PTransferRequest;

import base.SDKClient;

public class CreateTransferTransaction extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			P2PTransferRequest p2PTransferRequest = new P2PTransferRequest();
			Transaction transaction = new Transaction();
			KYCInformation senderKyc = new KYCInformation();
			Name kycSubject = new Name();
			RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
			Address address = new Address("GB");
			IdDocument idDocument = new IdDocument("nationalidcard");
			InternationalTransferInformation transferInformation = new InternationalTransferInformation("GB");

			List<AccountIdentifier> debitPartyList = new ArrayList<>();
			List<AccountIdentifier> creditPartyList = new ArrayList<>();
			List<IdDocument> identificationList = new ArrayList<>();

			idDocument.setIdNumber("1234567");
			idDocument.setIssuer("UKPA");
			idDocument.setIssuerPlace("GB");
			idDocument.setIssuerCountry("GB");
			idDocument.setIssueDate("2018-07-03T11:43:27.405Z");
			idDocument.setExpiryDate("2021-07-03T11:43:27.405Z");
			idDocument.setOtherIddescription("test");
			identificationList.add(idDocument);

			kycSubject.setTitle("Mr");
			kycSubject.setFirstName("Luke");
			kycSubject.setMiddleName("R");
			kycSubject.setLastName("Skywalker");
			kycSubject.setFullName("Luke R Skywalker");
			kycSubject.setNativeName("ABC");

			senderKyc.setNationality("GB");
			senderKyc.setBirthCountry("GB");
			senderKyc.setOccupation("Manager");
			senderKyc.setEmployerName("MFX");
			senderKyc.setContactPhone("+447125588999");
			senderKyc.setGender("m");
			senderKyc.setDateOfBirth("1970-07-03T11:43:27.405Z");
			senderKyc.setEmailAddress("luke.skywalkeraaabbb@gmail.com");
			senderKyc.setIdDocument(identificationList);
			senderKyc.setPostalAddress(address);
			senderKyc.setSubjectName(kycSubject);

			requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");
			requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");

			debitPartyList.add(new AccountIdentifier("walletid", "1"));
			creditPartyList.add(new AccountIdentifier("msisdn", "+44012345678"));

			transaction.setAmount("16.00");
			transaction.setCurrency("USD");
			transaction.setInternationalTransferInformation(transferInformation);
			transaction.setSenderKyc(senderKyc);
			transaction.setRequestingOrganisation(requestingOrganisation);
			transaction.setCreditParty(creditPartyList);
			transaction.setDebitParty(debitPartyList);

			p2PTransferRequest.setTransaction(transaction);

			System.out.println("Please wait...");
			AsyncResponse sdkResponse = mmClient.addRequest(p2PTransferRequest).addCallBack(get("CALLBACK_URL"))
					.createTransferTransaction();

			System.out.println(String.format("Create Transfer Transaction Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
