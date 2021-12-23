# 
View A Link

`Here, viewAccountLink(Identifiers identifiers, String linkReference) creates a GET request to /accounts/{accountId}/links/{linkReference}`

> `This endpoint returns a specific link.`

### Usage/Examples

```java
AccountLinkRequest accountLinkRequest = new AccountLinkRequest();

List<AccountIdentifier> sourceAccountIdentifiers = new ArrayList<>();
RequestingOrganisation requestingOrganisation = new RequestingOrganisation();
List<CustomData> customDataList = new ArrayList<>();

sourceAccountIdentifiers.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

customDataList.add(new CustomData("keytest", "keyvalue"));

requestingOrganisation.setRequestingOrganisationIdentifierType("organisationid");
requestingOrganisation.setRequestingOrganisationIdentifier("testorganisation");

AccountLink accountLink = new AccountLink();
accountLink.setSourceAccountIdentifiers(sourceAccountIdentifiers);
accountLink.setMode("active");
accountLink.setStatus("both");
accountLink.setRequestingOrganisation(requestingOrganisation);
accountLink.setRequestDate("2018-07-03T11:43:27.405Z");
accountLink.setCustomData(customDataList);

accountLinkRequest.setAccountLink(accountLink);

List<AccountIdentifier> identifierList = new ArrayList<>();
identifierList.add(new AccountIdentifier("accountid", "<Place your account id of debit party here>"));

AsyncResponse sdkResponse = mmClient.addRequest(accountLinkRequest).createAccountLink(new Identifiers(identifierList));

sdkResponse = mmClient.addRequest(accountLinkRequest).viewRequestState(sdkResponse.getServerCorrelationId());

String linkRef = sdkResponse.getObjectReference();
AccountLink accountLinkResponse = mmClient.addRequest(accountLinkRequest).viewAccountLink(new Identifiers(identifierList), linkRef);
```

### Response Example

```java
[  
    {
        "transactionReference": "REF-1620028406917",
        "creditParty": [
            {
                "key": "accountid",
                "value": "2000"
            },
            {
                "key": "linkref",
                "value": "REF-1621839627337"
            },
            {
                "key": "linkref",
                "value": "REF-1635445811066"
            }
        ],
        "debitParty": [
            {
                "key": "accountid",
                "value": "2999"
            }
        ],
        "type": "inttransfer",
        "transactionStatus": "completed",
        "amount": "100.00",
        "currency": "GBP",
        "internationalTransferInformation": {
            "originCountry": "GB",
            "quotationReference": "{{quotationReference}}",
            "quoteId": "{{quoteId}}",
            "deliveryMethod": "agent",
            "receivingCountry": "RW",
            "relationshipSender": "none",
            "remittancePurpose": "personal",
            "sendingServiceProviderCountry": "AD"
        },
        "senderKyc": {
            "nationality": "GB",
            "dateOfBirth": "1970-07-03",
            "occupation": "Manager",
            "employerName": "MFX",
            "contactPhone": "+447125588999",
            "gender": "m",
            "idDocument": [
                {
                    "idType": "nationalidcard",
                    "idNumber": "1234567",
                    "issueDate": "2018-07-03",
                    "expiryDate": "2021-07-03",
                    "issuer": "UKPA",
                    "issuerPlace": "GB",
                    "issuerCountry": "GB"
                }
            ],
            "postalAddress": {
                "addressLine1": "111 ABC Street",
                "city": "New York",
                "stateProvince": "New York",
                "postalCode": "ABCD",
                "country": "GB"
            },
            "subjectName": {
                "title": "Mr",
                "firstName": "Luke",
                "middleName": "R",
                "lastName": "Skywalker",
                "fullName": "Luke R Skywalker",
                "nativeName": "ABC"
            },
            "emailAddress": "luke.skywalkeraaabbb@gmail.com",
            "birthCountry": "GB"
        },
        "requestingOrganisation": {
            "requestingOrganisationIdentifierType": "organisationid",
            "requestingOrganisationIdentifier": "testorganisation"
        },
        "creationDate": "2021-05-03T08:53:27",
        "modificationDate": "2021-05-03T08:53:27",
        "requestDate": "2021-05-03T08:53:27"
    },
    ...,
    {
        "transactionReference": "REF-1620133857481",
        "creditParty": [
            {
                "key": "accountid",
                "value": "2000"
            },
            {
                "key": "linkref",
                "value": "REF-1621839627337"
            },
            {
                "key": "linkref",
                "value": "REF-1635445811066"
            }
        ],
        "debitParty": [
            {
                "key": "accountid",
                "value": "2999"
            }
        ],
        "type": "inttransfer",
        "transactionStatus": "pending",
        "amount": "100.00",
        "currency": "GBP",
        "internationalTransferInformation": {
            "originCountry": "GB",
            "quotationReference": "{{quotationReference}}",
            "quoteId": "{{quoteId}}",
            "deliveryMethod": "agent",
            "receivingCountry": "RW",
            "relationshipSender": "none",
            "remittancePurpose": "personal",
            "sendingServiceProviderCountry": "AD"
        },
        "senderKyc": {
            "nationality": "GB",
            "dateOfBirth": "1970-07-03",
            "occupation": "Manager",
            "employerName": "MFX",
            "contactPhone": "+447125588999",
            "gender": "m",
            "idDocument": [
                {
                    "idType": "nationalidcard",
                    "idNumber": "1234567",
                    "issueDate": "2018-07-03",
                    "expiryDate": "2021-07-03",
                    "issuer": "UKPA",
                    "issuerPlace": "GB",
                    "issuerCountry": "GB"
                }
            ],
            "postalAddress": {
                "addressLine1": "111 ABC Street",
                "city": "New York",
                "stateProvince": "New York",
                "postalCode": "ABCD",
                "country": "GB"
            },
            "subjectName": {
                "title": "Mr",
                "firstName": "Luke",
                "middleName": "R",
                "lastName": "Skywalker",
                "fullName": "Luke R Skywalker",
                "nativeName": "ABC"
            },
            "emailAddress": "luke.skywalkeraaabbb@gmail.com",
            "birthCountry": "GB"
        },
        "requestingOrganisation": {
            "requestingOrganisationIdentifierType": "organisationid",
            "requestingOrganisationIdentifier": "testorganisation"
        },
        "creationDate": "2021-05-04T14:10:57",
        "modificationDate": "2021-05-04T14:10:57",
        "requestDate": "2021-05-04T14:10:57"
    }
]
```