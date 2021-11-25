package com.mobilemoney.common.model;

/***
 * Class ServiceStatusResponse
 */
public final class ServiceStatusResponse {
    // Status of the requested service
    private String serviceStatus;

    // Planned restoration time
    private String plannedRestorationTime;

    // Anticipated processing delay in milliseconds
    private long delay;

    /***
     *
     * @return
     */
    public String getServiceStatus() {
        return serviceStatus;
    }

    /***
     *
     * @return
     */
    public String getPlannedRestorationTime() {
        return plannedRestorationTime;
    }

    /***
     *
     * @return
     */
    public long getDelay() {
        return delay;
    }
}
