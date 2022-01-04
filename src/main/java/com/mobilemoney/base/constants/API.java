package com.mobilemoney.base.constants;

public final class API {

    private API() {}

    // Sandbox Environment URL
    public static final String SANDBOX_URL = "https://sandbox.mobilemoneyapi.io/2/oauth/simulator/v1.2/mm";

    // Product Environment URL
    public static final String PRODUCTION_URL = "TBD";

    // REST Sandbox Access Token Endpoint
    public static final String SANDBOX_ACCESS_TOKEN_URL = "https://sandbox.mobilemoneyapi.io/v1/oauth/accesstoken";

    // REST Production Access Token Endpoint
    public static final String PRODUCTION_ACCESS_TOKEN_URL = "TBD";

    // REST Endpoint: Create Transaction
    public static  final  String TRANSACTION_TYPE = "/transactions/type/{transactionType}";

    // REST Endpoint: Create Transaction
    public static  final  String RETRIEVE_TRANSACTION = "/transactions/{transactionReference}";

    // REST Endpoint: Create Transaction Reversal
    public static final String CREATE_TRANSACTION_REVERSAL = "/transactions/{transactionReference}/{transactionType}";

    // REST Endpoint: Create Transaction Reversal
    public static final String CREATE_BATCH_TRANSACTIONS = "/batchtransactions";

    // REST Endpoint: View Batch Transaction
    public static final String VIEW_BATCH_TRANSACTION = "/batchtransactions/{batchId}";

    // REST Endpoint: Update Batch Transaction
    public static final String UPDATE_BATCH_TRANSACTION = "/batchtransactions/{batchId}";

    // REST Endpoint: View Completed Transactions
    public static final String VIEW_BATCH_TRANSACTION_COMPLETED = "/batchtransactions/{batchId}/completions";

    // REST Endpoint: View Rejected Transactions
    public static final String VIEW_BATCH_TRANSACTION_REJECTED = "/batchtransactions/{batchId}/rejections";

    // REST Endpoint: Create Transaction
    public static  final  String CREATE_REQUEST_STATES = "/requeststates/{serverCorrelationId}";

    // REST Endpoint: Retrieve Missing API Link
    public static  final  String RETRIEVE_RESPONSE = "/responses/{clientCorrelationId}";

    // REST Endpoint: Heartbeat Request
    public static  final  String HEARTBEAT_REQUEST = "/heartbeat";

    // REST Endpoint: Quotation Request
    public static final String QUOTATION_REQUEST = "/quotations";

    // REST Endpoint: Quotation Request
    public static final String VIEW_QUOTATION = "/quotations/{quotationReference}";

    // REST Endpoint: Merchant Balance Request
    public static  final  String ACCOUNT_BALANCE_REQUEST = "/accounts/{identifierType}/{identifier}/balance";

    // REST Endpoint: Merchant Transactions
    public static  final  String ACCOUNT_TRANSACTIONS = "/accounts/{identifierType}/{identifier}/transactions";

    // REST Endpoint: Authorisation Code
    public static  final  String ACCOUNT_AUTHORISATION_CODE = "/accounts/{identifierType}/{identifier}/authorisationcodes";

    // REST Endpoint: Authorisation Code
    public static  final  String VIEW_ACCOUNT_AUTHORISATION_CODE = "/accounts/{identifierType}/{identifier}/authorisationcodes/{authorisationCode}";

    // REST Endpoint: Account Name
    public static final String VIEW_ACCOUNT_NAME = "/accounts/{identifierType}/{identifier}/accountname";

    // REST Endpoint: Debit Mandates
    public static final String CREATE_DEBIT_MANDATES = "/accounts/{identifierType}/{identifier}/debitmandates";

    // REST Endpoint: View Debit Mandates
    public static final String VIEW_DEBIT_MANDATES = "/accounts/{identifierType}/{identifier}/debitmandates/{debitMandateReference}";
    
    // REST Endpoint: Create Account Link
    public static final String CREATE_ACCOUNT_LINKS = "/accounts/{identifierType}/{identifier}/links";
    
    // REST Endpoint: View Account Link
    public static final String VIEW_ACCOUNT_LINK = "/accounts/{accountId}/links/{linkReference}";
    
    // REST Endpoint: Create Bill Payments
    public static final String CREATE_BILL_PAYMENT = "/accounts/{identifierType}/{identifier}/bills/{billReference}/payments";
    
    // REST Endpoint: View Account Bills
    public static final String VIEW_ACCOUNT_BILLS = "/accounts/{identifierType}/{identifier}/bills";
    
    // REST Endpoint: View Bill Payments
    public static final String VIEW_BILL_PAYMENTS = "/accounts/{identifierType}/{identifier}/bills/{billReference}/payments";
    
    // REST Endpoint: Create an Account
    public static final String VIEW_ACCOUNT_IDENTITY_TYPE = "/accounts/{identityType}";
    
    // REST Endpoint: View an Account
    public static final String VIEW_ACCOUNT_IDENTIFIER = "/accounts/{identifierType}/{identifier}";
    
    // REST Endpoint: Update an Account Identity
    public static final String VIEW_ACCOUNT_IDENTITY_ID = "/accounts/{identifierType}/{identifier}/identities/{identityId}";

}
