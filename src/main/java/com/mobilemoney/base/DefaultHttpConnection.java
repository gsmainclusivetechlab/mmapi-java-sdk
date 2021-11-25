package com.mobilemoney.base;

import com.mobilemoney.base.exception.SSLConfigurationException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/***
 * Class DefaultHttpConnection
 */
public class DefaultHttpConnection extends HttpConnection {
    // SSL
    private SSLContext sslContext;

    /***
     *
     * @param clientConfiguration
     * @throws IOException
     */
    @Override
    public void createAndConfigureHttpConnection(HttpConfiguration clientConfiguration) throws IOException {
        this.config = clientConfiguration;
        URL url = new URL(this.config.getEndPointUrl());

        this.connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

        if (this.connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) this.connection).setSSLSocketFactory(this.sslContext.getSocketFactory());
        }

        System.setProperty("http.maxConnections",String.valueOf(this.config.getMaxHttpConnection()));
        System.setProperty("sun.net.http.errorstream.enableBuffering", "true");

        this.connection.setDoInput(true);
        this.connection.setDoOutput(true);
        //setRequestMethodViaJreBugWorkaround(this.connection, config.getHttpMethod());
        this.connection.setConnectTimeout(this.config.getConnectionTimeout());
        this.connection.setReadTimeout(this.config.getReadTimeout());
    }

    /***
     *
     */
    public DefaultHttpConnection() {
        try {
            sslContext = SSLUtil.getSSLContext(null);
        } catch (SSLConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     *
     * @param sslContext
     */
    public DefaultHttpConnection(SSLContext sslContext) {
        this.sslContext = sslContext;
    }
}
