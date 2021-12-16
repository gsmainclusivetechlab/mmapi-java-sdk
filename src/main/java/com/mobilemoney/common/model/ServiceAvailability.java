package com.mobilemoney.common.model;

/***
 * Class ServiceAvailability
 */
public final class ServiceAvailability {
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
	 * @param serviceStatus
	 */
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
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
	 * @param plannedRestorationTime
	 */
	public void setPlannedRestorationTime(String plannedRestorationTime) {
		this.plannedRestorationTime = plannedRestorationTime;
	}

	/***
	 * 
	 * @return
	 */
	public long getDelay() {
		return delay;
	}

	/***
	 * 
	 * @param delay
	 */
	public void setDelay(long delay) {
		this.delay = delay;
	}

    
}
