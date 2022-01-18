package com.mobilemoney.base.context;

import com.mobilemoney.common.constants.Environment;
import com.mobilemoney.base.constants.SecurityLevel;

import java.util.Map;

/***
 * Class MMClient
 */
public final class MMClient {
    private String consumerKey;
    private String consumerSecret;
    private String apiKey;
    private String callBackUrl;
    private Environment mode;
    private SecurityLevel securityLevel;
    private Map<String, String> configurations;

    /***
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     */
    public MMClient(String consumerKey, String consumerSecret, String apiKey) {
        this(consumerKey, consumerSecret, apiKey, Environment.SANDBOX);
    }

    /***
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     * @param mode
     */
    public MMClient(String consumerKey, String consumerSecret, String apiKey, Environment mode) {
        this(consumerKey, consumerSecret, apiKey, mode, SecurityLevel.DEVELOPMENT);
    }

    /***
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     * @param mode
     * @param securityLevel
     */
    public MMClient(String consumerKey, String consumerSecret, String apiKey, Environment mode, SecurityLevel securityLevel) {
        this(consumerKey, consumerSecret, apiKey, mode, securityLevel, null);
    }

    /***
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     * @param mode
     * @param securityLevel
     * @param configurations
     */
    public MMClient(String consumerKey, String consumerSecret, String apiKey, Environment mode, SecurityLevel securityLevel, Map<String, String> configurations) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.apiKey = apiKey;
        this.mode = mode;
        this.securityLevel = securityLevel;
        this.configurations = configurations;
    }

    /***
     *
     * @param callBackUrl
     * @return
     */
    public MMClient addCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
        return this;
    }

    /***
     *
     * @param executeObject
     * @param <T>
     * @return
     */
    public <T> T addRequest(T executeObject) {
        MobileMoneyContext.createContext(this.consumerKey, this.consumerSecret, this.apiKey, this.mode, this.callBackUrl, this.securityLevel, this.configurations);
        return executeObject;
    }
}
