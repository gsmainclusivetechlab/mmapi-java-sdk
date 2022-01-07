package com.mobilemoney.disbursement.model;

import com.mobilemoney.base.util.JSONFormatter;
import java.io.Serializable;
import java.util.List;

/**
 * * Class BatchRejections
 */
public class BatchRejections implements Serializable {
	private static final long serialVersionUID = 7810478442629822149L;

	// List of BatchRejection object
	List<BatchRejection> batchRejections;

	// item count of current list
	Integer availableCount;

	// total count of items
	Integer totalCount;

	/**
	 * *
	 *
	 * @return
	 */
	public List<BatchRejection> getBatchRejections() {
		return batchRejections;
	}

	/**
	 * *
	 *
	 * @param batchRejections
	 */
	public void setBatchRejections(List<BatchRejection> batchRejections) {
		this.batchRejections = batchRejections;
	}

	/**
	 * *
	 *
	 * @return
	 */
	public Integer getAvailableCount() {
		return availableCount;
	}

	/**
	 * *
	 *
	 * @param availableCount
	 */
	public void setAvailableCount(Integer availableCount) {
		this.availableCount = availableCount;
	}

	/**
	 * *
	 *
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * *
	 *
	 * @param totalCount
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * * Returns JSON object
	 *
	 * @return
	 */
	public String toJSON() {
		return JSONFormatter.toJSON(this);
	}
}
