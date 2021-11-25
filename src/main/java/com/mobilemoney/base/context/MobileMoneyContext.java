package com.mobilemoney.base.context;

import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.common.constants.Environment;
import com.mobilemoney.base.constants.SecurityLevel;
import com.mobilemoney.base.util.EnumUtils;

import java.util.Map;

/***
 * Class MobileMoneyContext
 */
public class MobileMoneyContext {
    // Singleton instance
    private static MobileMoneyContext instance;

    // MobileMoneyAuthentication instance
    private MobileMoneyAuthentication credential;

    // Set client correlation ID, if required
    private boolean clientCorrelationIdRequired;

    // Set global callback url
    private String callBackUrl;

    /***
     * Pass the consumerKey, consumerSecret, apiKey, mode and securityLevel along with additional configurations
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     * @param mode
     * @param callBackUrl
     * @param securityLevel
     * @param configurations
     */
    private MobileMoneyContext(String consumerKey, String consumerSecret, String apiKey, Environment mode, String callBackUrl, SecurityLevel securityLevel, Map<String, String> configurations) {
        this.credential = new MobileMoneyAuthentication(consumerKey, consumerSecret, apiKey);
        if (configurations != null && configurations.size() > 0) {
            this.credential.addConfigurations(configurations);
        }

        this.setMode(mode);
        this.setSecurityLevel(securityLevel);
        this.callBackUrl = callBackUrl;

        try {
            this.credential.getAccessToken();
            instance = this;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param consumerKey
     * @param consumerSecret
     * @param apiKey
     * @param mode
     * @param callBackUrl
     * @param securityLevel
     * @param configurations
     */
    public static void createContext(String consumerKey, String consumerSecret, String apiKey, Environment mode, String callBackUrl, SecurityLevel securityLevel, Map<String, String> configurations) {
        if (instance == null) {
            synchronized (MobileMoneyContext.class) {
                if (instance == null) {
                    instance = new MobileMoneyContext(consumerKey, consumerSecret, apiKey, mode, callBackUrl, securityLevel, configurations);
                }
            }
        }
    }

    /***
     * Returns context instance
     *
     * @return
     */
    public static MobileMoneyContext getContext() {
        return instance;
    }

    /***
     * Returns access token
     *
     * @return
     */
    public String fetchAccessToken() {
        if (this.credential != null) {
            return this.credential.getAccessToken();
        }
        return null;
    }

    /***
     * Returns refresh token
     *
     * @return
     */
    public String getRefreshToken() {
        if (this.credential != null) {
            return this.credential.getRefreshToken();
        }
        return null;
    }

    /***
     * Returns configuration map
     *
     * @return
     */
    public Map<String, String> getConfigurationMap() {
        return this.credential.getConfigurations();
    }

    /***
     * Returns HTTP header map
     *
     * @return
     */
    public Map<String, String> getHTTPHeaders() {
        return this.credential.getHeaders();
    }

    /***
     * Returns HTTP Header value
     *
     * @param key
     * @return
     */
    public String getHTTPHeader(String key) {
        return this.credential.getHeader(key);
    }

    /***
     *
     * @return
     */
    public String getCallBackUrl() {
        return callBackUrl;
    }

    /***
     * Add HTTP Header value to existing list of HTTP Headers
     *
     * @param key
     * @param value
     * @return
     */
    public MobileMoneyContext addHTTPHeader(String key, String value) {
        this.credential.addHeader(key, value);
        return this;
    }

    /***
     * Sets mode to either `production` or `sandbox`
     *
     * @param mode
     * @return
     */
    public MobileMoneyContext setMode(Environment mode) {
        if (mode == null || !(mode.equals(Environment.PRODUCTION) || mode.equals(Environment.SANDBOX))) {
            throw new IllegalArgumentException(String.format("Mode needs to be either `%s` or `%s`.", Environment.SANDBOX, Environment.PRODUCTION));
        }
        this.credential.addConfiguration(Constants.MODE, mode.name());
        return this;
    }

    /***
     * Sets security level
     *
     * @param securityLevel
     * @return
     */
    public MobileMoneyContext setSecurityLevel(SecurityLevel securityLevel) {
        if (securityLevel == null || !EnumUtils.isInEnum(SecurityLevel.class, securityLevel.name())) {
            throw new IllegalArgumentException("Invalid security level");
        }
        this.credential.addConfiguration(Constants.SECURITY_LEVEL, securityLevel.name());
        return this;
    }

    /***
     *
     * @return
     */
    public boolean clientCorrelationIdRequired() {
        return clientCorrelationIdRequired;
    }

    /***
     *
     * @param clientCorrelationIdRequired
     */
    public void setClientCorrelationIdRequired(boolean clientCorrelationIdRequired) {
        this.clientCorrelationIdRequired = clientCorrelationIdRequired;
    }
}
