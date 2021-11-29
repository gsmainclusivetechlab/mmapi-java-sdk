package com.mobilemoney.common.model;

import com.mobilemoney.base.model.HttpErrorResponse;
import com.mobilemoney.base.model.SDKResponse;

import java.io.Serializable;

/***
 * Class AsyncResponse
 */
public final class AsyncResponse implements Serializable {
    private static final long serialVersionUID = 8362494148006663426L;

    // Number of poll attempts for the given request state
    private int pollLimit;

    // A unique identifier
    private String serverCorrelationId;

    // Reference to the subject resource
    private String objectReference;

    // Status of the request
    private String status;

    // Indicates whether a callback will be issued or whether the client will need to poll
    private String notificationMethod;

    // A textual description
    private String pendingReason;

    // Expiry time
    private String expiryTime;

    // Details of the error
    private HttpErrorResponse error;

    /***
     *
     * @return
     */
    public int getPollLimit() {
        return pollLimit;
    }

    /***
     *
     * @param pollLimit
     */
    public void setPollLimit(int pollLimit) {
        this.pollLimit = pollLimit;
    }

    /***
     *
     * @return
     */
    public String getServerCorrelationId() {
        return serverCorrelationId;
    }

    /***
     *
     * @param serverCorrelationId
     */
    public void setServerCorrelationId(String serverCorrelationId) {
        this.serverCorrelationId = serverCorrelationId;
    }

    /***
     *
     * @return
     */
    public String getObjectReference() {
        return objectReference;
    }

    /***
     *
     * @param objectReference
     */
    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    /***
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /***
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /***
     *
     * @return
     */
    public String getNotificationMethod() {
        return notificationMethod;
    }

    /***
     *
     * @param notificationMethod
     */
    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    /***
     *
     * @return
     */
    public String getPendingReason() {
        return pendingReason;
    }

    /***
     *
     * @param pendingReason
     */
    public void setPendingReason(String pendingReason) {
        this.pendingReason = pendingReason;
    }

    /***
     *
     * @return
     */
    public String getExpiryTime() {
        return expiryTime;
    }

    /***
     *
     * @param expiryTime
     */
    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    /***
     *
     * @return
     */
    public HttpErrorResponse getError() {
        return error;
    }

    /***
     *
     * @param error
     */
    public void setError(HttpErrorResponse error) {
        this.error = error;
    }
}
