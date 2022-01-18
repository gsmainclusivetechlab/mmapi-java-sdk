package com.mobilemoney.base;

import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.exception.SSLConfigurationException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/***
 * Class SSLUtil
 */
public abstract class SSLUtil {
    private static final Map<String, String> CONFIG_MAP;

    static {
        CONFIG_MAP = SDKUtil.combineDefaultMap(ConfigManager.getInstance().getConfigurationMap());
    }

    /***
     *
     * @param keyManagers
     * @return
     * @throws SSLConfigurationException
     */
    public static SSLContext getSSLContext(KeyManager[] keyManagers) throws SSLConfigurationException {
        try {
            SSLContext ctx;
            String protocol = CONFIG_MAP.get(Constants.SSLUTIL_PROTOCOL);
            try {
                ctx = SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException e) {
                ctx = SSLContext.getInstance(protocol);
            }
            ctx.init(keyManagers, null, null);
            return ctx;
        } catch (Exception e) {
            throw new SSLConfigurationException(e.getMessage(), e);
        }
    }
}
