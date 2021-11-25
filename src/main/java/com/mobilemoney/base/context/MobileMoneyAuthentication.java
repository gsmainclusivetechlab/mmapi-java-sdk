package com.mobilemoney.base.context;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mobilemoney.base.ConnectionManager;
import com.mobilemoney.base.HttpConfiguration;
import com.mobilemoney.base.HttpConnection;
import com.mobilemoney.base.HttpResponse;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.common.constants.Environment;
import com.mobilemoney.base.constants.SecurityLevel;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuthTokenCredential is used for generation of OAuth Token used by MobileMoney
 * REST API service. ConsumerKey and ConsumerSecret are required by the class to
 * generate OAuth Token, the resulting token is of the form "Basic xxxxxx".
 */
public final class MobileMoneyAuthentication {
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> configurationMap;

    private String consumerKey;
    private String consumerSecret;
    private String apiKey;

    private String accessToken;

    /**
     * Pass consumerKey, consumerSecret and apiKey to OAuthTokenCredential.
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     */
    public MobileMoneyAuthentication(String consumerKey, String consumerSecret, String apiKey) {
        super();
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.apiKey = apiKey;
    }

    /***
     *
     * @param key
     * @param value
     * @return
     */
    public MobileMoneyAuthentication addConfiguration(String key, String value) {
        if (this.configurationMap == null) {
            this.configurationMap = new HashMap<>();
        }
        this.configurationMap.put(key, value);
        return this;
    }

    /***
     * Adds configurations to list of configurations
     *
     * @param configurations
     * @return
     */
    public MobileMoneyAuthentication addConfigurations(Map<String, String> configurations) {
        if (this.configurationMap == null) {
            this.configurationMap = new HashMap<>();
        }
        this.configurationMap.putAll(configurations);
        return this;
    }

    /***
     *
     * @return
     */
    public String getAccessToken() {
        if (this.accessToken == null) {
            return generateAccessToken();
        }
        return this.accessToken;
    }

    /***
     *
     * @return
     */
    public String getRefreshToken() {
        this.accessToken = null;
        return getAccessToken();
    }

    /***
     * Generate access token
     *
     */
    private synchronized String generateAccessToken() {
        HttpConnection connection;
        HttpConfiguration httpConfiguration;

        try {
            connection = ConnectionManager.getInstance().getConnection();
            httpConfiguration = getOAuthHttpConfiguration();
            connection.createAndConfigureHttpConnection(httpConfiguration);

            // Sets authorization header
            setAuthHeaders();

            this.headers.put(Constants.HTTP_ACCEPT_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
            this.headers.put(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONFIG_DEFAULT_CONTENT_TYPE);

            String postRequest = getRequestPayload();

            HttpResponse jsonResponse = connection.execute(httpConfiguration.getEndPointUrl(), postRequest, this.headers);

            if (jsonResponse.getPayLoad() instanceof String) {
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse((String)jsonResponse.getPayLoad());
                this.accessToken = jsonElement.getAsJsonObject().get(Constants.ACCESS_TOKEN).getAsString();
                //this.accessToken = "eyJraWQiOiJcL0V4YXNlMmpqdkVtcUtLNTdmNEwyMkUyUUx2MDhndkFqTGlZVHl3bFhzUT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI1OXZ0aG1xM2Y2aTE1djZqbWNqc2tma21oIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJvYXV0aFwvcmVhZCIsImF1dGhfdGltZSI6MTYzNzIxNzEwMywiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LXdlc3QtMi5hbWF6b25hd3MuY29tXC9ldS13ZXN0LTJfTWJqdUR6cUJjIiwiZXhwIjoxNjM3MjIwNzAzLCJpYXQiOjE2MzcyMTcxMDMsInZlcnNpb24iOjIsImp0aSI6IjBiOTRkZTdiLTViMDMtNDhlYS1hNTU4LTNjM2U1NTBjZjAwZiIsImNsaWVudF9pZCI6IjU5dnRobXEzZjZpMTV2NmptY2pza2ZrbWgifQ.KmNAlBezmEw6lC_LxiRqSTZZ6b4Pbxk2tuxWCn23FhtAArCZx8IRZcu6VABNXZf6qi6zwgG_3SmsX3rMhrou2GPRg92cKa_ojJYuKAMDWffRS9Zu9dG4OrUaUJrjXrpIqWoHAnkumwRK-lWgNRS2nZ0WMGSySB_qePcnkxSC6aGm11XZ2YxVMA5lu5PfSFUvxxATlRxfsM68dk1ecyxOFDhV32Uts1ENoTnirof35RGDcKJ6H5j4fe9-2ZXonwzZOYbnIhGJC8YDzAoxfjkxP1eKyUlA9HD4ZyjCevm6Iuvh-9Qt91nNS-SpzqZMlfkFDtPt0XoagyM5DCB5zk6ECw";
                return this.accessToken;
            }

            return null;
        } catch(Exception e) {

        } finally {
            // Replace the headers back to JSON for any future use.
            this.headers.put(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
        }
        return null;
    }

    /***
     * Sets authorization header based on security level
     *
     * @throws Exception
     */
    protected void setAuthHeaders() throws Exception {
        switch (SecurityLevel.valueOf(this.configurationMap.get(Constants.SECURITY_LEVEL))) {
            case NONE: break;
            case DEVELOPMENT: {
                this.headers.put(Constants.API_KEY, this.apiKey);
                this.headers.put(Constants.AUTHORIZATION_HEADER, Constants.BASIC + generateBase64String());
                break;
            }
            case STANDARD: {
                this.headers.put(Constants.API_KEY, this.apiKey);
                this.headers.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER + getAccessToken());
                break;
            }
            case ENHANCED: break;
            default: throw new Exception("Undefined security level: " + SecurityLevel.values());
        }
    }

    /***
     *
     * @return
     * @throws MalformedURLException
     */
    protected HttpConfiguration getOAuthHttpConfiguration() throws MalformedURLException {
        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setHttpMethod(Constants.HTTP_CONFIG_DEFAULT_HTTP_METHOD);

        Environment mode = Environment.valueOf(this.configurationMap.get(Constants.MODE));

        // Default to Endpoint param.
        String endPointUrl = this.configurationMap.get(Constants.OAUTH_ENDPOINT);

        if (endPointUrl == null || endPointUrl.trim().isEmpty()) {
            switch(mode) {
                case SANDBOX:
                    endPointUrl = API.SANDBOX_ACCESS_TOKEN_URL; break;
                case PRODUCTION:
                    endPointUrl = API.SANDBOX_ACCESS_TOKEN_URL; break;
                default:
                    endPointUrl = "";
            }
        }

        // If none of the option works, throw exception.
        if (endPointUrl == null || endPointUrl.trim().length() <= 0) {
            throw new MalformedURLException("not configured to sandbox/production ");
        }

        endPointUrl = (endPointUrl.endsWith("/")) ? endPointUrl.substring(0, endPointUrl.length() - 1) : endPointUrl;

        httpConfiguration.setEndPointUrl(endPointUrl);
        //httpConfiguration.setConnectionTimeout(Integer.parseInt(configurationMap.get(Constants.HTTP_CONNECTION_TIMEOUT)));
        /*httpConfiguration.setMaxRetry(Integer.parseInt(configurationMap.get(Constants.HTTP_CONNECTION_RETRY)));
        httpConfiguration.setReadTimeout(Integer.parseInt(configurationMap.get(Constants.HTTP_CONNECTION_READ_TIMEOUT)));
        httpConfiguration.setMaxHttpConnection(Integer.parseInt(configurationMap.get(Constants.HTTP_CONNECTION_MAX_CONNECTION)));
        httpConfiguration.setIpAddress(configurationMap.get(Constants.DEVICE_IP_ADDRESS));*/

        return httpConfiguration;
    }

    /***
     * Returns list of configurations
     *
     * @return
     */
    public Map<String, String> getConfigurations() {
        return this.configurationMap;
    }

    /***
     * Returns HTTP headers
     *
     * @return
     */
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /***
     * Returns the header value
     *
     * @param key
     * @return
     */
    public String getHeader(String key) {
        return this.headers.get(key);
    }

    /***
     * Add header value to existing list of headers
     *
     * @param key
     * @param value
     * @return
     */
    public MobileMoneyAuthentication addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    /***
     *
     * @return
     */
    protected String getRequestPayload() {
        //if (this.refreshToken != null) {
            //return String.format("grant_type=refresh_token&refresh_token=%s", this.refreshToken);
        //} else {
            return "grant_type=client_credentials";
        //}
    }

    /***
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private String generateBase64String() throws UnsupportedEncodingException {
        String base64ClientID;
        byte[] encoded;
        try {
            encoded = Base64.getEncoder().encode((consumerKey + ":" + consumerSecret).getBytes());
            base64ClientID = new String(encoded);
        } catch (Exception e) {
            throw new UnsupportedEncodingException(e.getMessage());
        }
        return base64ClientID;
    }
}
