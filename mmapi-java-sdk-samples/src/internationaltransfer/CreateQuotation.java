package internationaltransfer;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import com.mobilemoney.internationaltransfer.model.Quotation;
import com.mobilemoney.internationaltransfer.request.InternationalTransferRequest;

import base.SDKClient;

public class CreateQuotation extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			InternationalTransferRequest internationalTransferRequest = new InternationalTransferRequest();

			System.out.println("Please wait...");

			IdDocument idDocument = new IdDocument("nationalidcard");
			Address address = new Address("GB");
			Name kycSubject = new Name();
			KYCInformation senderKyc = new KYCInformation();

			List<AccountIdentifier> debitPartyList = new ArrayList<>();
			List<AccountIdentifier> creditPartyList = new ArrayList<>();
			List<CustomData> customDataList = new ArrayList<>();
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

			debitPartyList.add(new AccountIdentifier("accountid", "2999"));
			creditPartyList.add(new AccountIdentifier("accountid", "2000"));
			customDataList.add(new CustomData("keytest", "keyvalue"));

			Quotation quotation = new Quotation("75.30", "RWF", creditPartyList, debitPartyList);
			quotation.setSubType("abc");
			quotation.setChosenDeliveryMethod("agent");
			quotation.setSendingServiceProviderCountry("AD");
			quotation.setOriginCountry("AD");
			quotation.setReceivingCountry("AD");
			quotation.setRequestDate("2018-07-03T11:43:27.405Z");
			quotation.setSenderKyc(senderKyc);
			quotation.setCustomData(customDataList);

			internationalTransferRequest.setQuotation(quotation);

			AsyncResponse sdkResponse = mmClient.addRequest(internationalTransferRequest)
					.addCallBack(get("CALLBACK_URL")).createQuotation();

			System.out.println(String.format("Create Quotation Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}
}
