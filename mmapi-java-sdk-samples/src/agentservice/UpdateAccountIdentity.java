package agentservice;

import java.util.ArrayList;
import java.util.List;

import com.mobilemoney.agentservices.model.Account;
import com.mobilemoney.agentservices.model.Identity;
import com.mobilemoney.agentservices.request.AgentServiceRequest;
import com.mobilemoney.base.constants.OP;
import com.mobilemoney.base.constants.Value;
import com.mobilemoney.base.context.MMClient;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.common.model.AccountIdentifier;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.Fees;
import com.mobilemoney.common.model.Identifiers;
import com.mobilemoney.common.model.Name;
import com.mobilemoney.common.model.PatchData;
import com.mobilemoney.internationaltransfer.model.Address;
import com.mobilemoney.internationaltransfer.model.IdDocument;
import com.mobilemoney.internationaltransfer.model.KYCInformation;

import base.SDKClient;

public class UpdateAccountIdentity extends SDKClient {

	/***
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		try {
			MMClient mmClient = new MMClient(get("CONSUMER_KEY"), get("CONSUMER_SECRET"), get("API_KEY"));
			AgentServiceRequest agentServiceRequest = new AgentServiceRequest();
			
			Account account = new Account();

	        List<AccountIdentifier> identifierList = new ArrayList<>();
	        
	        String currentTimeMillis = Long.toString(System.currentTimeMillis());
	        identifierList.add(new AccountIdentifier("msisdn", "+44" + currentTimeMillis.substring(currentTimeMillis.length() - 10)));

	        List<Identity> identityList = new ArrayList<>();
	        Identity identity = new Identity();

	        List<CustomData> customDataList = new ArrayList<>();
	        customDataList.add(new CustomData("test", "custom"));

	        List<CustomData> customDataList1 = new ArrayList<>();
	        customDataList.add(new CustomData("test", "custom1"));

	        KYCInformation senderKyc = new KYCInformation();
	        List<IdDocument> identificationList = new ArrayList<>();

	        IdDocument idDocument = new IdDocument("passport");
	        idDocument.setIdType("passport");
	        idDocument.setIdNumber("111111");
	        idDocument.setIssuer("ABC");
	        idDocument.setIssuerPlace("DEF");
	        idDocument.setIssuerCountry("AD");
	        idDocument.setIssueDate("2018-11-20");
	        idDocument.setExpiryDate("2018-11-20");
//	        idDocument.setOtherIddescription("test");
	        identificationList.add(idDocument);

	        Address address = new Address("AD");
	        address.setAddressLine1("37");
	        address.setAddressLine2("ABC Drive");
	        address.setAddressLine3("string");
	        address.setCity("Berlin");
	        address.setStateProvince("string");
	        address.setPostalCode("AF1234");
	        address.setCountry("AD");

	        Name kycSubject = new Name();
	        kycSubject.setTitle("Mr");
	        kycSubject.setFirstName("H");
	        kycSubject.setMiddleName("I");
	        kycSubject.setLastName("J");
	        kycSubject.setFullName("H I J");
	        kycSubject.setNativeName("string");

	        senderKyc.setNationality("AD");
	        senderKyc.setBirthCountry("AD");
	        senderKyc.setOccupation("Miner");
	        senderKyc.setEmployerName("string");
	        senderKyc.setContactPhone("+447777777777");
	        senderKyc.setGender("m");
	        senderKyc.setDateOfBirth("2000-11-20");
	        senderKyc.setEmailAddress("xyz@xyz.com");
	        senderKyc.setIdDocument(identificationList);
	        senderKyc.setPostalAddress(address);
	        senderKyc.setSubjectName(kycSubject);

	        identity.setAccountRelationship("accountholder");
	        identity.setCustomData(customDataList);
	        identity.setIdentityKyc(senderKyc);
	        identity.setKycLevel(1);
	        identity.setKycVerificationEntity("ABC Agent");
	        identity.setKycVerificationStatus("verified");
	        identityList.add(identity);

	        List<Fees> feesList = new ArrayList<>();
	        Fees fees = new Fees();
	        fees.setFeeType("string");
	        fees.setFeeAmount("5.46");
	        fees.setFeeCurrency("AED");
	        feesList.add(fees);

	        account.setAccountIdentifiers(identifierList);
	        account.setIdentity(identityList);
	        account.setAccountType("string");
	        account.setCustomData(customDataList1);
	        account.setFees(feesList);
	        account.setRegisteringEntity("ABC Agent");
	        account.setRequestDate("2021-02-17T15:41:45.194Z");
	        
	        agentServiceRequest.setAccount(account);

			System.out.println("Please wait...");
			
			AsyncResponse sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(get("CALLBACK_URL")).createAccount();
			
			List<AccountIdentifier> identifierList1 = new ArrayList<>();
	        identifierList1.add(new AccountIdentifier("walletid", "1"));
	        
	        Account accountViewed = mmClient.addRequest(agentServiceRequest).viewAccount(new Identifiers(identifierList1));
	        
	        String identityId = "0";
	        
	        if (accountViewed.getIdentity().size() > 0) {
	            identityId = accountViewed.getIdentity().get(0).getIdentityId();
	        }
	        
	        List<PatchData> patchDataList = new ArrayList<>();
	        patchDataList.add(new PatchData(OP.REPLACE.getOP(), "/kycVerificationStatus", Value.VERIFIED.getValue()));
	        
	        agentServiceRequest.setPatchData(patchDataList);
	        sdkResponse = mmClient.addRequest(agentServiceRequest).addCallBack(get("CALLBACK_URL")).updateAccountIdentity(new Identifiers(identifierList1), identityId);
	        
			System.out.println(String.format("Update Account Identity Status: %s", sdkResponse.getStatus()));
		} catch (MobileMoneyException ex) {
			System.out.println(String.format("Mobile Money Exception: %s", ex.getError().getErrorDescription()));
		}
	}

}
