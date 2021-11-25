package com.mobilemoney.common.request;

import com.mobilemoney.base.HttpResponse;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.constants.HttpStatusCode;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.base.util.ResourceUtils;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.AsyncResponse;
import com.mobilemoney.common.model.ServiceStatusResponse;

/***
 * Class CommonRequest
 */
public class CommonRequest extends ResourceUtils {
    // Unique Id
    public String clientCorrelationId;

    // Call back URL
    public String callBackURL;

    // Callback/polling
    public NotificationType notificationType = NotificationType.CALLBACK;

    /***
     *
     * @param serverCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    public AsyncResponse viewRequestState(final String serverCorrelationId) throws MobileMoneyException {
        String resourcePath = API.CREATE_REQUEST_STATES.replace(Constants.SERVER_CORRELATION_ID, serverCorrelationId);
        return createRequest(HttpMethod.GET, resourcePath, null, null, null, AsyncResponse.class);
    }

    /***
     *
     * @param clientCorrelationId
     * @param objectReference
     * @param <T>
     * @return
     * @throws MobileMoneyException
     */
    public <T> T viewResponse(final String clientCorrelationId, final Class<T> objectReference) throws MobileMoneyException {
        T response = null;
        String retrieveResponse = processMissingAPI(clientCorrelationId);

        if (retrieveResponse != null) {
            response = JSONFormatter.fromJSON(retrieveResponse, objectReference);
        }

        return response;
    }

    /***
     *
     * @return
     * @throws MobileMoneyException
     */
    public ServiceStatusResponse viewServiceAvailability() throws MobileMoneyException {
        return createRequest(HttpMethod.GET, API.HEARTBEAT_REQUEST, null, null, null, ServiceStatusResponse.class);
    }

    /***
     *
     * @param clientCorrelationId
     * @return
     * @throws MobileMoneyException
     */
    private String processMissingAPI(String clientCorrelationId) throws MobileMoneyException {
        String retrieveResponse = null;
        String resourcePath = API.RETRIEVE_RESPONSE.replace(Constants.CLIENT_CORRELATION_ID, clientCorrelationId);
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resourcePath);

        if (requestResponse.getPayLoad() instanceof String) {
            if (requestResponse.getResponseCode().equals(HttpStatusCode.OK)) {
                MissingAPI apiLink = JSONFormatter.fromJSON((String) requestResponse.getPayLoad(), MissingAPI.class);
                if (!apiLink.getLink().isEmpty()) {
                    retrieveResponse = retrieveResponse(apiLink.getLink());
                }
            }
        }

        return retrieveResponse;
    }

    /***
     *
     * @param resource
     * @return
     * @throws MobileMoneyException
     */
    private String retrieveResponse(String resource) throws MobileMoneyException {
        HttpResponse requestResponse = requestExecute(HttpMethod.GET, resource);
        return requestResponse.getPayLoad() instanceof String ? (String)requestResponse.getPayLoad() : null;
    }

    // Inner class
    class MissingAPI {
        // URL to the resource
        private String link;

        /***
         *
         * @return
         */
        public String getLink() {
            return link;
        }
    }
}
