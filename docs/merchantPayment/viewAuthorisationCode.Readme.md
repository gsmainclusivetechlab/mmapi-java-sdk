# View an Authorisation Code

`Here, viewAuthorisationCode(Identifiers identifiers, String authorisationCode) creates a GET request to /accounts/{identifierType}/{identifier}/authorisationcodes/{authorisationCode}`

> `This endpoint returns a specific Authorisation Code linked to an account.`

### Usage/Examples

```java
MMClient mmClient = new MMClient("<Place your consumer key>", "<Place your consumer secret>", "<Place your API key>");
MerchantPaymentRequest merchantPaymentRequest = new MerchantPaymentRequest();
AuthorisationCode authorisationCode = new AuthorisationCode();
List<AccountIdentifier> identifierList = new ArrayList<>();

identifierList.add(new AccountIdentifier("<identifier type>", "<identifier>"));

authorisationCode.setCodeLifetime("<code expiry time in seconds>");
authorisationCode.setAmount("<amount>");
authorisationCode.setCurrency("<currency>");

merchantPaymentRequest.setAuthorisationCodeRequest(authorisationCode);

AsyncResponse sdkResponse = mmClient.addRequest(merchantPaymentRequest).addCallBack("<Place your callback URL>").createAuthorisationCode(new Identifiers(identifierList));
```

Obtain generated pre-authorized code to perform merchant payment

```java
AuthorisationCode authorisationCodeResponse = mmClient.addRequest(merchantPaymentRequest).viewAuthorisationCode(new Identifiers(identifierList), sdkResponse.getObjectReference());

```

### Response Example

```java
{
  "authorisationCode": "8216be45-6353-477d-a79a-d162b8cd97f6",
  "codeState": "active",
  "amount": "1000.00",
  "currency": "GBP",
  "codeLifetime": 1,
  "holdFundsIndicator": false,
  "redemptionAccountIdentifiers": [
    {
      "key": "msisdn",
      "value": "+123456789102345"
    },
    {
      "key": "walletid",
      "value": "3355544"
    },
    {
      "key": "accountid",
      "value": "15523"
    },
    {
      "key": "organisationid",
      "value": "155423"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637842500748"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637900348226"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637903966999"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637905596564"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637906193835"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637906276192"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637906394938"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909724748"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909728207"
    },
    {
      "key": "mandatereference",
      "value": "REF-1637909741842"
    },
    {
      "key": "linkref",
      "value": "REF-1638255745281"
    },
    {
      "key": "linkref",
      "value": "REF-1638255863864"
    },
    {
      "key": "linkref",
      "value": "REF-1638255911597"
    },
    {
      "key": "linkref",
      "value": "REF-1638261429975"
    },
    {
      "key": "linkref",
      "value": "REF-1638261457517"
    },
    {
      "key": "linkref",
      "value": "REF-1638261458376"
    },
    {
      "key": "linkref",
      "value": "REF-1638261497750"
    },
    {
      "key": "linkref",
      "value": "REF-1638262197421"
    },
    {
      "key": "linkref",
      "value": "REF-1638262219434"
    },
    {
      "key": "linkref",
      "value": "REF-1638262272437"
    },
    {
      "key": "linkref",
      "value": "REF-1638262273373"
    },
    {
      "key": "linkref",
      "value": "REF-1638262292896"
    },
    {
      "key": "linkref",
      "value": "REF-1638262317276"
    },
    {
      "key": "linkref",
      "value": "REF-1638262350313"
    },
    {
      "key": "linkref",
      "value": "REF-1638262365414"
    },
    {
      "key": "linkref",
      "value": "REF-1638262366281"
    },
    {
      "key": "linkref",
      "value": "REF-1638262445031"
    },
    {
      "key": "linkref",
      "value": "REF-1638272486114"
    },
    {
      "key": "linkref",
      "value": "REF-1638272627500"
    },
    {
      "key": "linkref",
      "value": "REF-1638272628424"
    },
    {
      "key": "linkref",
      "value": "REF-1638273606275"
    },
    {
      "key": "linkref",
      "value": "REF-1638273607160"
    },
    {
      "key": "linkref",
      "value": "REF-1638273756935"
    },
    {
      "key": "linkref",
      "value": "REF-1638273816818"
    },
    {
      "key": "linkref",
      "value": "REF-1638273817872"
    },
    {
      "key": "linkref",
      "value": "REF-1638273958952"
    },
    {
      "key": "linkref",
      "value": "REF-1638274003050"
    },
    {
      "key": "linkref",
      "value": "REF-1638274090033"
    },
    {
      "key": "linkref",
      "value": "REF-1638274121454"
    },
    {
      "key": "linkref",
      "value": "REF-1638274237512"
    },
    {
      "key": "linkref",
      "value": "REF-1638274274976"
    },
    {
      "key": "linkref",
      "value": "REF-1638274289644"
    },
    {
      "key": "linkref",
      "value": "REF-1638274316058"
    },
    {
      "key": "linkref",
      "value": "REF-1638274562460"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638282123590"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330248859"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330253103"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638330268782"
    },
    {
      "key": "linkref",
      "value": "REF-1638347775503"
    },
    {
      "key": "linkref",
      "value": "REF-1638347805698"
    },
    {
      "key": "linkref",
      "value": "REF-1638347806557"
    },
    {
      "key": "linkref",
      "value": "REF-1638347843281"
    },
    {
      "key": "linkref",
      "value": "REF-1638347844080"
    },
    {
      "key": "linkref",
      "value": "REF-1638356475497"
    },
    {
      "key": "linkref",
      "value": "REF-1638356476318"
    },
    {
      "key": "linkref",
      "value": "REF-1638357117477"
    },
    {
      "key": "linkref",
      "value": "REF-1638357118209"
    },
    {
      "key": "linkref",
      "value": "REF-1638357261590"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360506968"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360510524"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638360527412"
    },
    {
      "key": "linkref",
      "value": "REF-1638427452231"
    },
    {
      "key": "linkref",
      "value": "REF-1638427452992"
    },
    {
      "key": "linkref",
      "value": "REF-1638427455257"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444904222"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444907317"
    },
    {
      "key": "mandatereference",
      "value": "REF-1638444919066"
    },
    {
      "key": "linkref",
      "value": "REF-1638444961737"
    },
    {
      "key": "linkref",
      "value": "REF-1638444962533"
    },
    {
      "key": "linkref",
      "value": "REF-1638444964708"
    },
    {
      "key": "linkref",
      "value": "REF-1638445110684"
    },
    {
      "key": "linkref",
      "value": "REF-1638854623292"
    },
    {
      "key": "linkref",
      "value": "REF-1638855391840"
    },
    {
      "key": "linkref",
      "value": "REF-1638855404487"
    },
    {
      "key": "linkref",
      "value": "REF-1638855451856"
    },
    {
      "key": "linkref",
      "value": "REF-1638856008993"
    },
    {
      "key": "linkref",
      "value": "REF-1638856081439"
    },
    {
      "key": "linkref",
      "value": "REF-1638858498583"
    },
    {
      "key": "linkref",
      "value": "REF-1638858602039"
    },
    {
      "key": "linkref",
      "value": "REF-1638858649341"
    },
    {
      "key": "linkref",
      "value": "REF-1638858690541"
    },
    {
      "key": "linkref",
      "value": "REF-1638858717152"
    },
    {
      "key": "linkref",
      "value": "REF-1638859816636"
    },
    {
      "key": "linkref",
      "value": "REF-1638859897546"
    },
    {
      "key": "linkref",
      "value": "REF-1638860357564"
    },
    {
      "key": "linkref",
      "value": "REF-1638861354268"
    },
    {
      "key": "linkref",
      "value": "REF-1638863952734"
    },
    {
      "key": "linkref",
      "value": "REF-1638874201989"
    },
    {
      "key": "linkref",
      "value": "REF-1638875028251"
    },
    {
      "key": "linkref",
      "value": "REF-1638875049176"
    },
    {
      "key": "linkref",
      "value": "REF-1638875124246"
    },
    {
      "key": "linkref",
      "value": "REF-1638875149627"
    },
    {
      "key": "linkref",
      "value": "REF-1638876615363"
    },
    {
      "key": "linkref",
      "value": "REF-1638876616193"
    },
    {
      "key": "linkref",
      "value": "REF-1638877282882"
    },
    {
      "key": "linkref",
      "value": "REF-1638877283682"
    },
    {
      "key": "linkref",
      "value": "REF-1638877385942"
    },
    {
      "key": "linkref",
      "value": "REF-1638877386678"
    },
    {
      "key": "linkref",
      "value": "REF-1638877471019"
    },
    {
      "key": "linkref",
      "value": "REF-1638877471816"
    },
    {
      "key": "linkref",
      "value": "REF-1638877715389"
    },
    {
      "key": "linkref",
      "value": "REF-1638877716189"
    },
    {
      "key": "linkref",
      "value": "REF-1638879081074"
    },
    {
      "key": "linkref",
      "value": "REF-1638879114053"
    },
    {
      "key": "linkref",
      "value": "REF-1638879136125"
    },
    {
      "key": "linkref",
      "value": "REF-1638879136923"
    },
    {
      "key": "linkref",
      "value": "REF-1638879230447"
    },
    {
      "key": "linkref",
      "value": "REF-1638879231232"
    },
    {
      "key": "linkref",
      "value": "REF-1638879262420"
    },
    {
      "key": "linkref",
      "value": "REF-1638879263265"
    },
    {
      "key": "linkref",
      "value": "REF-1638879443103"
    },
    {
      "key": "linkref",
      "value": "REF-1638879443822"
    },
    {
      "key": "linkref",
      "value": "REF-1638879519928"
    },
    {
      "key": "linkref",
      "value": "REF-1638879520669"
    },
    {
      "key": "linkref",
      "value": "REF-1638879588902"
    },
    {
      "key": "linkref",
      "value": "REF-1638879589646"
    },
    {
      "key": "linkref",
      "value": "REF-1638935625910"
    },
    {
      "key": "linkref",
      "value": "REF-1638935627483"
    },
    {
      "key": "linkref",
      "value": "REF-1638937003862"
    },
    {
      "key": "linkref",
      "value": "REF-1638937005203"
    },
    {
      "key": "linkref",
      "value": "REF-1638937136441"
    },
    {
      "key": "linkref",
      "value": "REF-1638937137699"
    },
    {
      "key": "linkref",
      "value": "REF-1638940366611"
    },
    {
      "key": "linkref",
      "value": "REF-1638940508906"
    },
    {
      "key": "linkref",
      "value": "REF-1638940918533"
    },
    {
      "key": "linkref",
      "value": "REF-1638947218333"
    },
    {
      "key": "linkref",
      "value": "REF-1638947245602"
    },
    {
      "key": "linkref",
      "value": "REF-1638947247259"
    },
    {
      "key": "linkref",
      "value": "REF-1638947386981"
    },
    {
      "key": "linkref",
      "value": "REF-1638947388264"
    },
    {
      "key": "linkref",
      "value": "REF-1638947555116"
    },
    {
      "key": "linkref",
      "value": "REF-1638948043261"
    },
    {
      "key": "linkref",
      "value": "REF-1638948044547"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023778555"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023782800"
    },
    {
      "key": "mandatereference",
      "value": "REF-1639023798519"
    },
    {
      "key": "linkref",
      "value": "REF-1639114919116"
    },
    {
      "key": "linkref",
      "value": "REF-1639115324940"
    },
    {
      "key": "linkref",
      "value": "REF-1639116020125"
    },
    {
      "key": "linkref",
      "value": "REF-1639118385226"
    },
    {
      "key": "linkref",
      "value": "REF-1639118585241"
    },
    {
      "key": "linkref",
      "value": "REF-1639127267170"
    }
  ],
  "creationDate": "2021-12-10T09:36:21",
  "modificationDate": "2021-12-10T09:36:21",
  "requestDate": "2021-12-10T09:36:21"
}
```