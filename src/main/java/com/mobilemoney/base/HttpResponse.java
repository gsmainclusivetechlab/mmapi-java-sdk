package com.mobilemoney.base;

import com.mobilemoney.base.constants.HttpStatusCode;

import java.util.List;
import java.util.Map;

/***
 * Class HttpResponse
 */
public final class HttpResponse<T> {
    // Request response data
    private T payLoad;

    // Response status(success/failure)
    private boolean success;

    // Response code
    private HttpStatusCode responseCode;

    // Response headers
    private Map<String, List<String>> responseHeader;

    /***
     * Private constructor with parameters
     *
     * @param payLoad
     * @param success
     * @param responseCode
     * @param responseHeader
     */
    private HttpResponse(T payLoad, boolean success, HttpStatusCode responseCode, Map<String, List<String>> responseHeader) {
        this.payLoad = payLoad;
        this.success = success;
        this.responseCode = responseCode;
        this.responseHeader = responseHeader;
    }

    /***
     * Creates HTTP response object
     *
     * @param payLoad
     * @param success
     * @param responseCode
     * @param responseHeader
     * @return
     */
    public static <T> HttpResponse createResponse(T payLoad, boolean success, HttpStatusCode responseCode, Map<String, List<String>> responseHeader) {
        return new HttpResponse(payLoad, success, responseCode, responseHeader);
    }

    /***
     * Set payLoad
     *
     * @param payLoad
     */
    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }

    /***
     * Returns request payload data
     *
     * @return
     */
    public T getPayLoad() {
        return payLoad;
    }

    /***
     * Returns request status
     *
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    /***
     * Returns response code
     *
     * @return
     */
    public HttpStatusCode getResponseCode() {
        return responseCode;
    }

    /***
     * 
     * @return
     */
	public Map<String, List<String>> getResponseHeader() {
		return responseHeader;
	}
}
