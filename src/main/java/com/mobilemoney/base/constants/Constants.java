package com.mobilemoney.base.constants;

public final class Constants {
    // Maximum retry value
    public static final int MAX_RETRIES = 3;

    // Encoding format
    public static final String ENCODING_FORMAT = "UTF-8";

    // Mode(sandbox/live)
    public static final String MODE = "mode";

    // SecurityLevel(None/Development/Standard/Enhanced)
    public static final String SECURITY_LEVEL = "securityLevel";

    // REST API resource endpoint
    public static final String ENDPOINT = "resource.endpoint";

    // Transaction Type Parameter Key
    public static final String TRANSACTION_TYPE = "{transactionType}";

    // Transaction Reference
    public static final String TRANSACTION_REFERENCE = "{transactionReference}";

    // Quotation Reference
    public static final String QUOTATION_REFERENCE = "{quotationReference}";

    // Batch Id
    public static final String BATCH_ID = "{batchId}";

    // Invalid REST endpoint
    public static final String INVALID_REST_ENDPOINT = "REST API endpoint could not be fetched properly.";

    // REST API Sandbox Endpoint
    public static final String REST_API_SANDBOX_ENDPOINT = "https://sandbox.mobilemoneyapi.io/2/oauth/simulator/v1.2/mm";

    // REST API Production Endpoint
    public static final String REST_API_PRODUCTION_ENDPOINT = "";

    // Authorization Header
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // Bearer
    public static final String BEARER = "Bearer ";

    // Basic
    public static final String BASIC = "Basic ";

    // API Key
    public static final String API_KEY = "X-API-Key";

    // Call Back URL
    public static final String CALL_BACK_URL = "X-Callback-URL";

    // Http override method
    public static final String HTTP_OVERRIDE_METHOD = "X-HTTP-Method-Override";

    // Correlation Id
    public static final String CORRELATION_ID = "X-CorrelationID";

    // Server Correlation Id
    public static final String SERVER_CORRELATION_ID = "{serverCorrelationId}";

    // Client Correlation Id
    public static final String CLIENT_CORRELATION_ID = "{clientCorrelationId}";

    // Identifier Type
    public static final String IDENTIFIER_TYPE = "{identifierType}";

    // Identifier
    public static final String IDENTIFIER = "{identifier}";

    // Multiple Identifier
    public static final String MULTI_IDENTIFIER = "{identifierType}/{identifier}";

    // Authorisation Code
    public static final String AUTHORISATION_CODE = "{authorisationCode}";

    // Default SDK configuration file name
    public static final String DEFAULT_CONFIGURATION_FILE = "sdk_config.properties";

    // SSLUtil Protocol
    public static final String SSLUTIL_PROTOCOL = "sslutil.protocol";

    // Access Token
    public static final String ACCESS_TOKEN = "access_token";

    // Invalid access token message
    public static final String EMPTY_ACCESS_TOKEN_MESSAGE = "AccessToken cannot be null or empty";

    // HTTP Method Default
    public static final String HTTP_CONFIG_DEFAULT_HTTP_METHOD = "POST";

    // OAuth End point
    public static final String OAUTH_ENDPOINT = "oauth.EndPoint";

    // HTTP Connection Timeout
    public static final String HTTP_CONNECTION_TIMEOUT = "http.ConnectionTimeOut";

    // HTTP Connection Retry
    public static final String HTTP_CONNECTION_RETRY = "http.Retry";

    // HTTP Read timeout
    public static final String HTTP_CONNECTION_READ_TIMEOUT = "http.ReadTimeOut";

    // HTTP Max Connections
    public static final String HTTP_CONNECTION_MAX_CONNECTION = "http.MaxConnection";

    // HTTP Device IP Address Key
    public static final String DEVICE_IP_ADDRESS = "http.IPAddress";

    // HTTP Accept Header
    public static final String HTTP_ACCEPT_HEADER = "Accept";

    // HTTP Content Type JSON
    public static final String HTTP_CONTENT_TYPE_JSON = "application/json";

    // HTTP Content-Type Header
    public static final String HTTP_CONTENT_TYPE_HEADER = "Content-Type";

    // HTTP Content Type Default
    public static final String HTTP_CONFIG_DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded";

}
