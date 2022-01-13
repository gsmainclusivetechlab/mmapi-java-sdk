package com.mobilemoney.disbursement.model;

import com.mobilemoney.base.util.JSONFormatter;
import java.io.Serializable;
import java.util.List;

/**
 * * Class BatchCompletions
 */
public class BatchCompletions implements Serializable {
	private static final long serialVersionUID = 6389783217406558331L;

	// List of BatchCompletion object
	List<BatchCompletion> batchCompletions;

	// item count of current list
	Integer availableCount;

	// total count of items
	Integer totalCount;

	/**
	 * *
	 *
	 * @return
	 */
	public List<BatchCompletion> getBatchCompletions() {
		return batchCompletions;
	}

	/**
	 * *
	 *
	 * @param batchCompletions
	 */
	public void setBatchCompletions(List<BatchCompletion> batchCompletions) {
		this.batchCompletions = batchCompletions;
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
