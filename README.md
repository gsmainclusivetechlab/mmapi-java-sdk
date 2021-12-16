# GSMA Mobile Money Payments Java SDK

This SDK provides for an easy way to connect to [GSMA Mobile Money API](https://developer.mobilemoneyapi.io/1.2).

Please refer to the following documentation for installation instructions and usage information.

-   [API Documentation](https://developer.mobilemoneyapi.io/1.2)
-   [Java SDK Documentation](docs/)

## Index

This document contains the following sections:

-  [Requirements](#requirements)
-  [Getting Started](#getting-started)
     -  [Installation](#installation)
     -  [Development and Testing](#development-and-testing)
-  [Setting Up](#setting-up)
-  [Use Cases](#use-cases)
-  [Testing](#testing)

## Requirements

-   Java JDK-1.8 or higher
-   Apache Maven 3 or higher

## Getting Started

### Installation

1. Build the jar file using  'mvn install' command
2. Copy 'mmapi-java-sdk' jar file to your project's classpath

In order to build the SDK from the source code you need to use Apache Maven and Java 1.8+

- Run 'mvn install' to build jar file

### Development and Testing

1. Tests for the SDK are in the src/test/java package.
2. Copy the config.properties.sample file to config.properties and enter your credentials in the appropriate fields.
3. From the test package, run JUnit test for each test classes

## Setting Up

### Initialization of Java SDK

All Java code snippets are listed [here](/docs). Assumes that you have initialized the Java SDK before using them in your Development Environment. 
This section details the initialization of the Java SDK.

To initialize the Java SDK, create an instance of the `MMClient` with `consumerKey`, `counsumerSecret` and `apiKey`

```java
MMClient mmClient = new MMClient("<Place your consumerKey>", "<Place your counsumerSecret>", "<Place your apiKey>");
```

You always need to create `MMClient` instance before making any SDK method calls.

By default, this would be in **SANDBOX** mode with **SecurityLevel.DEVELOPMENT**

1. `consumerKey` the API consumer key (can be obtained from developer portal)
2. `counsumerSecret` the API consumer secret (can be obtained from developer portal)
3. `apiKey` the API Key (can be obtained from developer portal)

Optional parameters available for `MMClient` class are:

1. `Environment` value can be one of the following
    - `Environment.SANDBOX` for Sandbox
    - `Environment.PRODUCTION` for Production
2. `SecurityLevel` value can be one of the following
    - `SecurityLevel.DEVELOPMENT` for Basic authentication requests
    - `SecurityLevel.STANDARD` for OAuth2 authentication requests

`MMClient` class has one optional function:

- `addCallBackUrl()` - URL for your application where you want MobileMoney API to push data as a `PUT` request. If you wish to specify different callback urls for different use cases, you can pass the callback url with each request seperately.

You can override the default configuration of `MMClient` by using either of the following ways:

```java
MMClient mmClient = new MMClient("<Place your consumerKey>", "<Place your counsumerSecret>", "<Place your apiKey>", "<mode>");
```
Or
```java
MMClient mmClient = new MMClient("<Place your consumerKey>", "<Place your counsumerSecret>", "<Place your apiKey>", "<mode>", "<security level>");
```
For example:
```java
MMClient mmClient = new MMClient("<Place your consumerKey>", "<Place your counsumerSecret>", "<Place your apiKey>", Environment.SANDBOX, SecurityLevel.DEVELOPMENT);
```


## Use Cases

-   [Merchant Payments](#merchant-payments)
-   [Disbursements](#disbursements)
-   [International Transfers](#international-transfers)
-   [P2P Transfers](#p2p-transfers) 

### Merchant Payments

<table>
<thead>
  <tr>
    <th>Scenarios</th>
    <th>API</th>
    <th>Function</th>
    <th>Parameters</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Payee-Initiated Merchant Payment</td>
    <td><a href="docs/merchantPayment/createMerchantTransaction.Readme.md">Payee Initiated Merchant Payment</a></td>
    <td>createMerchantTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td rowspan="3">Payee-Initiated Merchant Payment using the Polling Method</td>
    <td><a href="docs/merchantPayment/createMerchantTransaction.Readme.md">Payee Initiated Merchant Payment</a></td>
    <td>createMerchantTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="docs/merchantPayment/viewRequestState.Readme.md">Poll to Determine the Request State</a></td>
    <td>viewRequestState</td>
    <td>String serverCorrelationId</td>
  </tr>
  <tr>
    <td><a href="docs/merchantPayment/viewTransaction.Readme.md">Retrieve a Transaction</a></td>
    <td>viewTransaction</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Payer-Initiated Merchant Payment</td>
    <td><a href="docs/merchantPayment/createMerchantTransaction.Readme.md">Payer Initiated Merchant Payment</a></td>
    <td>createMerchantTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td rowspan="3">Payee-Initiated Merchant Payment using a Pre-authorised Payment Code</td>
    <td><a href="docs/merchantPayment/createAuthorisationCode.Readme.md">Obtain an Authorisation Code</a></td>
    <td>createAuthorisationCode</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td><a href="docs/merchantPayment/createMerchantTransaction.Readme.md">Perform a Merchant Payment</a></td>
    <td>createMerchantTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="docs/merchantPayment/viewAuthorisationCode.Readme.md">View An Authorisation Code</a></td>
    <td>viewAuthorisationCode</td>
    <td>Identifiers identifiers, String authorisationCode</td>
  </tr>
  <tr>
    <td>Merchant Payment Refund</td>
    <td><a href="docs/merchantPayment/createRefundTransaction.Readme.md">Perform a Merchant Payment Refund</a></td>
    <td>createRefundTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Merchant Payment Reversal</td>
    <td><a href="docs/merchantPayment/createReversal.Readme.md">Perform a Merchant Payment Reversal</a></td>
    <td>createReversal</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Obtain a Merchant Balance</td>
    <td><a href="docs/merchantPayment/viewAccountBalance.Readme.md">Get an Account Balance</a></td>
    <td>viewAccountBalance</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td>Retrieve Payments for a Merchant</td>
    <td><a href="docs/merchantPayment/viewAccountTransactions.Readme.md">Retrieve a Set of Transactions for an Account</a></td>
    <td>viewAccountTransactions</td>
    <td>Identifiers identifiers, TransactionFilter filter</td>
  </tr>
  <tr>
    <td>Check for Service Availability</td>
    <td><a href="docs/merchantPayment/viewServiceAvailability.Readme.md">Check for Service Availability</a></td>
    <td>viewServiceAvailability</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Retrieve a Missing API Response</td>
    <td><a href="docs/merchantPayment/viewResponse.Readme.md">Retrieve a Missing Response</a></td>
    <td>viewResponse</td>
    <td>String clientCorrelationId, Class<T> objectReference</td>
  </tr>
</tbody>
</table>

### Disbursements

<table>
<thead>
  <tr>
    <th>Scenarios</th>
    <th>API</th>
    <th>Function</th>
    <th>Parameters</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Individual Disbursement</td>
    <td><a href="docs/disbursement/createDisbursementTransaction.Readme.md">Create a Individual Disbursement request </a></td>
    <td>createDisbursementTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td rowspan="4">Bulk Disbursement</td>
    <td><a href="docs/disbursement/createBatchTransaction.Readme.md">Create A Transaction Batch</a></td>
    <td>createBatchTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchTransaction.Readme.md">View A Transaction Batch</a></td>
    <td>viewBatchTransaction</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchCompletions.Readme.md">View Batch Completions</a></td>
    <td>viewBatchCompletions</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchRejections.Readme.md">View Batch Rejections</a></td>
    <td>viewBatchRejections</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td rowspan="5">Bulk Disbursement with Maker / Checker</td>
    <td><a href="docs/disbursement/createBatchTransaction.Readme.md">Create A Transaction Batch</a></td>
    <td>createBatchTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/updateBatchTransaction.Readme.md">Update A Transaction Batch</a></td>
    <td>updateBatchTransaction</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchTransaction.Readme.md">View A Transaction Batch</a></td>
    <td>viewBatchTransaction</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchCompletions.Readme.md">View Batch Completions</a></td>
    <td>viewBatchCompletions</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewBatchRejections.Readme.md">View Batch Rejections</a></td>
    <td>viewBatchRejections</td>
    <td>String batchId</td>
  </tr>
  <tr>
    <td rowspan="3">Individual Disbursement Using the Polling Method</td>
    <td><a href="docs/disbursement/createDisbursementTransaction.Readme.md">Create a Individual Disbursement request </a></td>
    <td>createDisbursementTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewRequestState.Readme.md">Poll to Determine the Request State</a></td>
    <td>viewRequestState</td>
    <td>String serverCorrelationId</td>
  </tr>
  <tr>
    <td><a href="docs/disbursement/viewTransaction.Readme.md">Retrieve a Transaction</a></td>
    <td>viewTransaction</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Disbursement Reversal</td>
    <td><a href="docs/disbursement/createReversal.Readme.md">Perform a Disbursement Reversal</a></td>
    <td>createReversal</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Obtain a Disbursement Organisation Balance</td>
    <td><a href="docs/disbursement/viewAccountBalance.Readme.md">Get an Account Balance</a></td>
    <td>viewAccountBalance</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td>Retrieve Transactions for a Disbursement Organisation</td>
    <td><a href="docs/disbursement/viewAccountTransactions.Readme.md">Retrieve a Set of Transactions for an Account</a></td>
    <td>viewAccountTransactions</td>
    <td>Identifiers identifiers, TransactionFilter filter</td>
  </tr>
  <tr>
    <td>Check for Service Availability</td>
    <td><a href="docs/disbursement/viewServiceAvailability.Readme.md">Check for Service Availability</a></td>
    <td>viewServiceAvailability</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Retrieve a Missing API Response</td>
    <td><a href="docs/disbursement/viewResponse.Readme.md">Retrieve a Missing Response</a></td>
    <td>viewResponse</td>
    <td>String clientCorrelationId, Class<T> objectReference</td>
  </tr>
</tbody>
</table>

### International Transfers

<table>
<thead>
  <tr>
    <th>Scenarios</th>
    <th>API</th>
    <th>Function</th>
    <th>Parameters</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td rowspan="3">International Transfer via Hub</td>
    <td><a href="/docs/internationalTransfer/createQuotation.Readme.md">Request a International Transfer Quotation</a></td>
    <td>createQuotation</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="/docs/internationalTransfer/createInternationalTransaction.Readme.md">Perform an International Transfer</a></td>
    <td>createInternationalTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Optional <a href="/docs/internationalTransfer/viewQuotation.Readme.md">View A Quotation</a></td>
    <td>viewQuotation</td>
    <td>String quotationReference</td>
  </tr>
  <tr>
    <td rowspan="3">Bilateral International Transfer</td>
    <td><a href="/docs/internationalTransfer/createQuotation.Readme.md">Request a International Transfer Quotation</a></td>
    <td>createQuotation</td>
    <td>NA</td>
  </tr>

 <tr>
    <td><a href="/docs/internationalTransfer/createInternationalTransaction.Readme.md">Perform an International Transfer</a></td>
    <td>createInternationalTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Optional <a href="/docs/internationalTransfer/viewQuotation.Readme.md">View A Quotation</a></td>
    <td>viewQuotation</td>
    <td>String quotationReference</td>
  </tr>
  <tr>
  <tr>
    <td>Get a specific request state</td>
    <td><a href="docs/internationalTransfer/viewRequestState.Readme.md">Poll to Determine the Request State</a></td>
    <td>viewRequestState</td>
    <td>String serverCorrelationId</td>
  </tr>
  <tr>
    <td>Get details of a transaction</td>
    <td><a href="docs/internationalTransfer/viewTransaction.Readme.md">Retrieve a Transaction</a></td>
    <td>viewTransaction</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>International Transfer Reversal</td>
    <td><a href="/docs/internationalTransfer/createReversal.Readme.md">Perform a Transaction Reversal</a></td>
    <td>createReversal</td>
    <td>String transactionReference
  </tr>
  <tr>
    <td>Obtain an FSP Balance</td>
    <td><a href="/docs/internationalTransfer/viewAccountBalance.Readme.md">Get an Account Balance</a></td>
    <td>viewAccountBalance</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td>Retrieve Transactions for an FSP</td>
    <td><a href="/docs/internationalTransfer/viewAccountTransactions.Readme.md">Retrieve a Set of Transactions for an Account</a></td>
    <td>viewAccountTransactions</td>
    <td>Identifiers identifiers, TransactionFilter filter</td>
  </tr>
  <tr>
    <td>Check for Service Availability</td>
    <td><a href="/docs/internationalTransfer/viewServiceAvailability.Readme.md">Check for Service Availability</a></td>
    <td>viewServiceAvailability</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Retrieve a Missing API Response</td>
    <td><a href="/docs/internationalTransfer/viewResponse.Readme.md">Retrieve a Missing Response</a></td>
    <td>viewResponse</td>
    <td>String correlationId, Class<T> objectReference</td>
  </tr>
</tbody>
</table>

### P2P Transfers

<table>
<thead>
  <tr>
    <th>Scenarios</th>
    <th>API</th>
    <th>Function</th>
    <th>Parameters</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td rowspan="3">P2P Transfer via Switch</td>
    <td><a href="/docs/p2pTransfer/viewAccountName.Readme.md">Retrieve the Name of the Recipient</a></td>
    <td>viewAccountName</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td><a href="/docs/p2pTransfer/createQuotation.Readme.md">Request a P2P Quotation</a></td>
    <td>createQuotation</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="/docs/p2pTransfer/createTransferTransaction.Readme.md">Perform a P2P Transfer</a></td>
    <td>createTransferTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td rowspan="2">Bilateral P2P Transfer</td>
    <td><a href="/docs/p2pTransfer/viewAccountName.Readme.md">Retrieve the Name of the Recipient</a></td>
    <td>viewAccountName</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td><a href="/docs/p2pTransfer/createTransferTransaction.Readme.md">Perform a P2P Transfer</a></td>
    <td>createTransferTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td rowspan="3">'On-us' P2P Transfer Initiated by a Third Party Provider</td>
    <td><a href="/docs/p2pTransfer/viewAccountName.Readme.md">Retrieve the Name of the Recipient</a></td>
    <td>viewAccountName</td>
    <td>Identifiers identifiers</td>
  </tr>
  <tr>
    <td><a href="/docs/p2pTransfer/createQuotation.Readme.md">Request a P2P Quotation</a></td>
    <td>createQuotation</td>
    <td>NA</td>
  </tr>
  <tr>
    <td><a href="/docs/p2pTransfer/createTransferTransaction.Readme.md">Perform a P2P Transfer</a></td>
    <td>createTransferTransaction</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Get a specific request state</td>
    <td><a href="docs/p2pTransfer/viewRequestState.Readme.md">Poll to Determine the Request State</a></td>
    <td>viewRequestState</td>
    <td>String serverCorrelationId</td>
  </tr>
  <tr>
    <td>Get details of a transaction</td>
    <td><a href="docs/p2pTransfer/viewTransaction.Readme.md">Retrieve a Transaction</a></td>
    <td>viewTransaction</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Get details of a specific quotation</td>
    <td>Optional <a href="/docs/p2pTransfer/viewQuotation.Readme.md">View A Quotation</a></td>
    <td>viewQuotation</td>
    <td>String quotationReference</td>
  </tr>
  <tr>
    <td>P2P Transfer Reversal</td>
    <td><a href="/docs/p2pTransfer/createReversal.Readme.md">Perform a Transaction Reversal</a></td>
    <td>createReversal</td>
    <td>String transactionReference</td>
  </tr>
  <tr>
    <td>Obtain an FSP Balance</td>
    <td><a href="/docs/p2pTransfer/viewAccountBalance.Readme.md">Get an Account Balance</a></td>
    <td>viewAccountBalance</td>
    <td>Identifiers identifiers</td>
  </tr>
   <tr>
    <td>Retrieve Transactions for an FSP</td>
    <td><a href="/docs/p2pTransfer/viewAccountTransactions.Readme.md">Retrieve a Set of Transactions for an Account</a></td>
    <td>viewAccountTransactions</td>
    <td>Identifiers identifiers, TransactionFilter filter</td>
  </tr>
  <tr>
    <td>Check for Service Availability</td>
    <td><a href="/docs/p2pTransfer/viewServiceAvailability.Readme.md">Check for Service Availability</a></td>
    <td>viewServiceAvailability</td>
    <td>NA</td>
  </tr>
  <tr>
    <td>Retrieve a Missing API Response</td>
    <td><a href="/docs/p2pTransfer/viewResponse.Readme.md">Retrieve a Missing Response</a></td>
    <td>viewResponse</td>
    <td>String clientCorrelationId, Class<T> objectReference</td>
  </tr>
</tbody>
</table>

## Testing

Tests for the SDK are in the src/test/java package. These tests are mainly for SDK development.

-   Copy the config.properties.sample file to config.properties and enter your credentials in the appropriate fields.