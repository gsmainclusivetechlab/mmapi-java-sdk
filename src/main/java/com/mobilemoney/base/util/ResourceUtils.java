package com.mobilemoney.base.util;

import com.mobilemoney.base.*;
import com.mobilemoney.base.constants.API;
import com.mobilemoney.base.constants.Constants;
import com.mobilemoney.base.constants.HttpMethod;
import com.mobilemoney.base.constants.HttpStatusCode;
import com.mobilemoney.base.context.MobileMoneyContext;
import com.mobilemoney.base.exception.MobileMoneyException;
import com.mobilemoney.base.exception.UnauthorizedException;
import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.common.constants.NotificationType;
import com.mobilemoney.common.model.Identifiers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceUtils {

    // Used for dynamic configuration
    private static Map<String, String> configurationMap;

    /***
     * Process requests
     *
     * @param httpMethod
     * @param resourcePath
     * @param responseObject
     * @param <T>
     * @return
     * @throws MobileMoneyException
     */
    protected <T> T createRequest(HttpMethod httpMethod, String resourcePath, Class<T> responseObject) throws MobileMoneyException {
        return createRequest(httpMethod, resourcePath, null, null, null, responseObject);
    }

    /***
     * Process requests
     *
     * @param httpMethod
     * @param resourcePath
     * @param payLoad
     * @param notificationType
     * @param callBackURL
     * @param responseObject
     * @param <T>
     * @return
     * @throws MobileMoneyException
     */
    protected <T> T createRequest(HttpMethod httpMethod, String resourcePath, String payLoad, NotificationType notificationType, String callBackURL, Class<T> responseObject) throws MobileMoneyException {
        T sdkResponse = null;

        HttpResponse requestResponse = requestExecute(httpMethod, resourcePath, payLoad, notificationType, callBackURL);

        if (requestResponse.getPayLoad() instanceof String) {
            sdkResponse = JSONFormatter.fromJSON((String) requestResponse.getPayLoad(), responseObject);
        }

        return sdkResponse;
    }

    /***
     * Configure requests first and then execute
     * @param httpMethod
     * @param resourcePath
     * @return
     * @throws MobileMoneyException
     */
    protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath) throws MobileMoneyException {
        return requestExecute(httpMethod, resourcePath, null, NotificationType.CALLBACK,null);
    }

    /***
     * Configure requests first and then execute - with payload
     * @param httpMethod
     * @param resourcePath
     * @param payLoad
     * @return
     * @throws MobileMoneyException
     */
    protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath, String payLoad) throws MobileMoneyException {
        return requestExecute(httpMethod, resourcePath, payLoad, NotificationType.CALLBACK, null);
    }

    /***
     * Configure requests first and then execute - with payload and callBack
     * @param httpMethod
     * @param resourcePath
     * @param payLoad
     * @param notificationType
     * @param callBackURL
     * @return
     */
    protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath, String payLoad, NotificationType notificationType, String callBackURL) throws MobileMoneyException {
        HttpResponse responseData = null;
        MobileMoneyContext apiContext = MobileMoneyContext.getContext();
        Map<String, String> cMap;
        Map<String, String> headersMap;

        if (apiContext != null) {
            String accessToken = apiContext.fetchAccessToken();
            //String accessToken = "eyJraWQiOiJcL0V4YXNlMmpqdkVtcUtLNTdmNEwyMkUyUUx2MDhndkFqTGlZVHl3bFhzUT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI1OXZ0aG1xM2Y2aTE1djZqbWNqc2tma21oIiwidG9rZW5fdXNlIjoiYWNjZXNzIiwic2NvcGUiOiJvYXV0aFwvcmVhZCIsImF1dGhfdGltZSI6MTYzNzIxNzEwMywiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmV1LXdlc3QtMi5hbWF6b25hd3MuY29tXC9ldS13ZXN0LTJfTWJqdUR6cUJjIiwiZXhwIjoxNjM3MjIwNzAzLCJpYXQiOjE2MzcyMTcxMDMsInZlcnNpb24iOjIsImp0aSI6IjBiOTRkZTdiLTViMDMtNDhlYS1hNTU4LTNjM2U1NTBjZjAwZiIsImNsaWVudF9pZCI6IjU5dnRobXEzZjZpMTV2NmptY2pza2ZrbWgifQ.KmNAlBezmEw6lC_LxiRqSTZZ6b4Pbxk2tuxWCn23FhtAArCZx8IRZcu6VABNXZf6qi6zwgG_3SmsX3rMhrou2GPRg92cKa_ojJYuKAMDWffRS9Zu9dG4OrUaUJrjXrpIqWoHAnkumwRK-lWgNRS2nZ0WMGSySB_qePcnkxSC6aGm11XZ2YxVMA5lu5PfSFUvxxATlRxfsM68dk1ecyxOFDhV32Uts1ENoTnirof35RGDcKJ6H5j4fe9-2ZXonwzZOYbnIhGJC8YDzAoxfjkxP1eKyUlA9HD4ZyjCevm6Iuvh-9Qt91nNS-SpzqZMlfkFDtPt0XoagyM5DCB5zk6ECw";
            if (accessToken == null) {
                throw new IllegalArgumentException(Constants.EMPTY_ACCESS_TOKEN_MESSAGE);
            }

            if (apiContext.getHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER) == null) {
                apiContext.addHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
            }

            // Set call back URL
            if (NotificationType.POLLING != notificationType) {
                if (callBackURL != null) {
                    apiContext.addHTTPHeader(Constants.CALL_BACK_URL, callBackURL);
                } else if (apiContext.getCallBackUrl() != null) {
                    apiContext.addHTTPHeader(Constants.CALL_BACK_URL, apiContext.getCallBackUrl());
                }
            }

            if (apiContext.getConfigurationMap() != null) {
                cMap = SDKUtil.combineDefaultMap(apiContext.getConfigurationMap());
            } else {
                initializeToDefault();

                // Merge with existing configuration map
                cMap = new HashMap<>(configurationMap);
            }

            headersMap = apiContext.getHTTPHeaders();
            headersMap.put(Constants.AUTHORIZATION_HEADER, Constants.BEARER + accessToken);

            APIManager apiManager = new APIManager(cMap, headersMap);

            // Need to change
            apiManager.setResourcePoint(resourcePath);

            if (payLoad != null) {
                apiManager.setPayLoad(payLoad);
            }

            HttpConfiguration httpConfiguration = getHttpConfiguration(httpMethod, apiManager);

            responseData = executeWithRetries(apiContext, () -> execute(apiManager, httpConfiguration));

            if (responseData == null || responseData.getPayLoad() == null) {
                //TODO: Construct null exception message object
                throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder("", "").errorDescription("").build());
            } else if (!responseData.isSuccess() && responseData.getPayLoad() instanceof String) {
                throw new MobileMoneyException((String)responseData.getPayLoad());
            }
        }

        return responseData;
    }

    /***
     * Initialize to default properties
     */
    protected static void initializeToDefault() {
        configurationMap = SDKUtil.combineDefaultMap(ConfigManager.getInstance().getConfigurationMap());
    }

    /***
     * Returns resource path, which is created using identifier list
     *
     * @param requestEndPoint
     * @param identifiers
     * @return
     * @throws MobileMoneyException
     */
    protected static String getResourcePath(final String requestEndPoint, Identifiers identifiers) throws MobileMoneyException {
        // TODO: Exception object
        if (identifiers == null || identifiers.getIdentifiers() == null || identifiers.getIdentifiers().isEmpty()) throw new MobileMoneyException();

        String resourcePath;
        if (identifiers.getIdentifiers().size() == 1) {
            resourcePath = requestEndPoint.replace(Constants.IDENTIFIER_TYPE, identifiers.getIdentifiers().get(0).getKey()).replace(Constants.IDENTIFIER, identifiers.getIdentifiers().get(0).getValue());
        } else {
            resourcePath = requestEndPoint.replace(Constants.MULTI_IDENTIFIER, identifiers.getIdentifiers().stream().map(identifier -> identifier.getKey().concat("@").concat(identifier.getValue())).collect(Collectors.joining("$")));
        }

        // TODO: Exception object
        if (StringUtils.isNullOrEmpty(resourcePath)) throw new MobileMoneyException();

        return resourcePath;
    }

    /***
     * Configure HTTP headers for API request
     * @param httpMethod
     * @param apiManager
     * @return
     */
    private static HttpConfiguration getHttpConfiguration(HttpMethod httpMethod, APIManager apiManager) throws MobileMoneyException {
        String endpoint = apiManager.getResourceEndPoint();

        if (endpoint == null || endpoint.isEmpty()) {
            throw new MobileMoneyException(Constants.INVALID_REST_ENDPOINT);
        }

        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setHttpMethod(httpMethod.toString());
        httpConfiguration.setEndPointUrl(endpoint);

        return httpConfiguration;
    }

    /***
     * Executes request trigger, re-trigger same call if token expired
     *
     * @param apiContext
     * @param task
     * @return
     */
    private static HttpResponse executeWithRetries(MobileMoneyContext apiContext, ExecuteTask task) throws MobileMoneyException {
        int count = 0;
        while(count < Constants.MAX_RETRIES) {
            try {
                return task.execute();
            } catch(UnauthorizedException e) {
                String accessToken = apiContext.getRefreshToken();
                if (accessToken == null) {
                    throw new IllegalArgumentException(Constants.EMPTY_ACCESS_TOKEN_MESSAGE);
                }
                apiContext.getHTTPHeaders().put(Constants.AUTHORIZATION_HEADER, Constants.BEARER + accessToken);

                if (++count >= Constants.MAX_RETRIES)
                    return null;
            }
        }
        return null;
    }

    /***
     * Execute the REST API call and return response
     * @param apiManager
     * @param httpConfiguration
     * @return
     */
    private static HttpResponse execute(APIManager apiManager, HttpConfiguration httpConfiguration) throws MobileMoneyException, UnauthorizedException {
        HttpResponse responseData;
        HttpConnection httpConnection;

        try {
            httpConnection = ConnectionManager.getInstance().getConnection();
            httpConnection.createAndConfigureHttpConnection(httpConfiguration);

            responseData = httpConnection.execute(httpConfiguration.getEndPointUrl(), apiManager.getPayLoad(), apiManager.getHeaderMap());

            // Token expired
            if (responseData.getResponseCode() == HttpStatusCode.UNAUTHORIZED) {
                throw new UnauthorizedException();
            }
        } catch (IOException e) {
            // TODO: Construct exception message object
            throw new MobileMoneyException(new HttpErrorResponse.HttpErrorResponseBuilder("", "").errorDescription("").build());
        }

        return responseData;
    }
}
