package com.mobilemoney.base.util;

import com.mobilemoney.base.*;
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

/***
 * Class ResourceUtils
 */
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
	protected <T> T createRequest(HttpMethod httpMethod, String resourcePath, Class<T> responseObject)
			throws MobileMoneyException {
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
	protected <T> T createRequest(HttpMethod httpMethod, String resourcePath, String payLoad,
			NotificationType notificationType, String callBackURL, Class<T> responseObject)
			throws MobileMoneyException {
		T sdkResponse = null;

		HttpResponse requestResponse = requestExecute(httpMethod, resourcePath, payLoad, notificationType, callBackURL);

		if (requestResponse.getPayLoad() instanceof String) {
			sdkResponse = JSONFormatter.fromJSON((String) requestResponse.getPayLoad(), responseObject);
		}

		return sdkResponse;
	}

	/***
	 * Configure requests first and then execute
	 *
	 * @param httpMethod
	 * @param resourcePath
	 * @return
	 * @throws MobileMoneyException
	 */
	protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath)
			throws MobileMoneyException {
		return requestExecute(httpMethod, resourcePath, null, NotificationType.CALLBACK, null);
	}

	/***
	 * Configure requests first and then execute - with payload
	 *
	 * @param httpMethod
	 * @param resourcePath
	 * @param payLoad
	 * @return
	 * @throws MobileMoneyException
	 */
	protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath, String payLoad)
			throws MobileMoneyException {
		return requestExecute(httpMethod, resourcePath, payLoad, NotificationType.CALLBACK, null);
	}

	/***
	 * Configure requests first and then execute - with payload and callBack
	 *
	 * @param httpMethod
	 * @param resourcePath
	 * @param payLoad
	 * @param notificationType
	 * @param callBackURL
	 * @return
	 */
	protected static HttpResponse requestExecute(HttpMethod httpMethod, String resourcePath, String payLoad,
			NotificationType notificationType, String callBackURL) throws MobileMoneyException {
		HttpResponse responseData = null;
		MobileMoneyContext apiContext = MobileMoneyContext.getContext();
		Map<String, String> cMap;
		Map<String, String> headersMap;

		if (apiContext != null) {
			String accessToken = apiContext.fetchAccessToken();
			if (accessToken == null) {
				throw new IllegalArgumentException(Constants.EMPTY_ACCESS_TOKEN_MESSAGE);
			}

			if (apiContext.getHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER) == null) {
				apiContext.addHTTPHeader(Constants.HTTP_CONTENT_TYPE_HEADER, Constants.HTTP_CONTENT_TYPE_JSON);
			}

			// Set call back URL
			if (NotificationType.POLLING != notificationType && (!StringUtils.isNullOrEmpty(callBackURL)
					|| !StringUtils.isNullOrEmpty(apiContext.getCallBackUrl()))) {
				if (!StringUtils.isNullOrEmpty(callBackURL)) {
					apiContext.addHTTPHeader(Constants.CALL_BACK_URL, callBackURL);
				} else if (!StringUtils.isNullOrEmpty(apiContext.getCallBackUrl())) {
					apiContext.addHTTPHeader(Constants.CALL_BACK_URL, apiContext.getCallBackUrl());
				}
			} else {
				if (apiContext.getHTTPHeaders().containsKey(Constants.CALL_BACK_URL)) {
					apiContext.getHTTPHeaders().remove(Constants.CALL_BACK_URL);
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
				throw new MobileMoneyException(
						new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY,
								Constants.GENERIC_ERROR_CODE).errorDescription(Constants.GENRAL_ERROR).build());
			} else if (!responseData.isSuccess() && responseData.getPayLoad() instanceof String) {
				HttpErrorResponse errorResponse = JSONFormatter.fromJSON((String) responseData.getPayLoad(),
						HttpErrorResponse.class);
				throw new MobileMoneyException(errorResponse);
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
	protected static String getResourcePath(final String requestEndPoint, Identifiers identifiers)
			throws MobileMoneyException {
		if (identifiers == null || identifiers.getIdentifiers() == null || identifiers.getIdentifiers().isEmpty()) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY,
							Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
		}

		String resourcePath;
		if (identifiers.getIdentifiers().size() == 1) {
			resourcePath = requestEndPoint
					.replace(Constants.IDENTIFIER_TYPE, identifiers.getIdentifiers().get(0).getKey())
					.replace(Constants.IDENTIFIER, identifiers.getIdentifiers().get(0).getValue());
		} else {
			resourcePath = requestEndPoint.replace(Constants.MULTI_IDENTIFIER,
					identifiers.getIdentifiers().stream()
							.map(identifier -> identifier.getKey().concat("@").concat(identifier.getValue()))
							.collect(Collectors.joining("$")));
		}

		if (StringUtils.isNullOrEmpty(resourcePath)) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY,
							Constants.GENERIC_ERROR_CODE).errorDescription(Constants.NULL_VALUE_ERROR).build());
		}

		return resourcePath;
	}

	/***
	 * Configure HTTP headers for API request
	 * 
	 * @param httpMethod
	 * @param apiManager
	 * @return
	 */
	private static HttpConfiguration getHttpConfiguration(HttpMethod httpMethod, APIManager apiManager)
			throws MobileMoneyException {
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
	private static HttpResponse executeWithRetries(MobileMoneyContext apiContext, ExecuteTask task)
			throws MobileMoneyException {
		int count = 0;
		while (count < Constants.MAX_RETRIES) {
			try {
				return task.execute();
			} catch (UnauthorizedException e) {
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
	 * 
	 * @param apiManager
	 * @param httpConfiguration
	 * @return
	 */
	private static HttpResponse execute(APIManager apiManager, HttpConfiguration httpConfiguration)
			throws MobileMoneyException, UnauthorizedException {
		HttpResponse responseData;
		HttpConnection httpConnection;

		try {
			httpConnection = ConnectionManager.getInstance().getConnection();
			httpConnection.createAndConfigureHttpConnection(httpConfiguration);

			responseData = httpConnection.execute(httpConfiguration.getEndPointUrl(), apiManager.getPayLoad(),
					apiManager.getHeaderMap());

			// Token expired
			if (responseData.getResponseCode() == HttpStatusCode.UNAUTHORIZED) {
				throw new UnauthorizedException();
			}
		} catch (IOException e) {
			throw new MobileMoneyException(
					new HttpErrorResponse.HttpErrorResponseBuilder(Constants.INTERNAL_ERROR_CATEGORY,
							Constants.GENERIC_ERROR_CODE).errorDescription(Constants.GENRAL_ERROR).build());
		}

		return responseData;
	}
}
