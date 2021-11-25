package com.mobilemoney.base;

import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.constants.HttpStatusCode;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.model.HttpErrorResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/***
 * Class HttpConnection
 */
public abstract class HttpConnection {
    protected HttpConfiguration config;
    protected HttpURLConnection connection;

    /***
     * Default constructor
     */
    public HttpConnection() { }

    /***
     * Configure HTTP connection before process
     *
     * @param clientConfiguration
     * @throws IOException
     */
    public abstract void createAndConfigureHttpConnection(HttpConfiguration clientConfiguration) throws IOException;

    /***
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     * @throws UnsupportedEncodingException
     */
    public HttpResponse execute(String url, String payload, Map<String, String> headers) throws IOException, MobileMoneyException {
        BufferedReader reader = null;
        HttpResponse result = null;
        try {
            result = executeWithStream(url, payload, headers);

            if (result == null) {
                // TODO: Construct exception message object
                throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder("", "").errorDescription("").build());
            } else if (result.getPayLoad() instanceof  InputStream) {
                reader = new BufferedReader(new InputStreamReader((InputStream)result.getPayLoad(), Constants.ENCODING_FORMAT));
                result.setPayLoad(read(reader));
            } else {
                result.setPayLoad(null);
            }
        } catch (Exception e) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return result;
    }

    /***
     *
     * @param url
     * @param payload
     * @param headers
     * @return
     */
    public HttpResponse executeWithStream(String url, String payload, Map<String, String> headers) throws ProtocolException {
        HttpResponse requestResponse = null;
        OutputStreamWriter writer = null;

        this.overrideRequestMethod();

        if (payload != null) {
            this.connection.setRequestProperty("Content-Length", String.valueOf(payload.trim().length()));
        }

        try {
            setHttpHeaders(headers);

            int retry = 0;
            retryLoop: do {

                if (Arrays.asList("POST", "PUT", "PATCH").contains(this.config.getHttpMethod().toUpperCase())) {
                    writer = new OutputStreamWriter(this.connection.getOutputStream(), Charset.forName(Constants.ENCODING_FORMAT));
                    if (payload != null ) {
                        writer.write(payload);
                    }
                    writer.flush();
                }

                int responseCode = this.connection.getResponseCode();
                HttpStatusCode httpStatus = HttpStatusCode.getHttpStatus(responseCode);

                try {
                    switch (httpStatus) {
                        case OK:
                        case CREATED:
                        case ACCEPTED:
                            requestResponse = HttpResponse.createResponse(this.connection.getInputStream(), true, httpStatus, this.connection.getHeaderFields());
                            break;
                        case BAD_REQUEST:
                        case UNAUTHORIZED:
                        case NOT_FOUND:
                        case INTERNAL_SERVER_ERROR:
                        case SERVICE_UNAVAILABLE:
                            requestResponse = HttpResponse.createResponse(this.connection.getErrorStream(), false, httpStatus, this.connection.getHeaderFields());
                            break;
                        default:
                            requestResponse = HttpResponse.createResponse(this.connection.getErrorStream(), false, HttpStatusCode.BAD_REQUEST, this.connection.getHeaderFields());
                            break;
                    }
                } catch (IOException e) {
                    //requestResponse = HttpResponse.createResponse(null, false, HttpStatusCode.BAD_REQUEST);
                }
                break retryLoop;
            } while (retry < this.config.getMaxRetry());
        } catch(Exception e) {

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                } finally {
                    writer = null;
                }
            }
        }
        return requestResponse;
    }

    /***
     *
     * @param headers
     */
    protected void setHttpHeaders(Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            Iterator<Map.Entry<String, String>> itr = headers.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> pairs = itr.next();
                String key = pairs.getKey();
                String value = pairs.getValue();
                if (value != null) {
                    this.connection.setRequestProperty(key, value);
                }
            }
        }
    }

    /***
     *
     * @param reader
     * @return
     * @throws IOException
     */
    protected String read(BufferedReader reader) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        return response.toString();
    }

    /***
     *
     * @throws ProtocolException
     */
    private void overrideRequestMethod() throws ProtocolException {
        if (this.config.getHttpMethod().equals(HttpMethod.PATCH.toString())) {
            this.connection.setRequestProperty(Constants.HTTP_OVERRIDE_METHOD, HttpMethod.PATCH.toString());
            this.connection.setRequestMethod(HttpMethod.POST.toString());
        } else {
            this.connection.setRequestMethod(this.config.getHttpMethod());
        }
    }
}
