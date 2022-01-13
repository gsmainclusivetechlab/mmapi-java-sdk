package com.mobilemoney.billpayment.model;

import com.mobilemoney.base.util.JSONFormatter;
import java.io.Serializable;
import java.util.List;

/**
 * * Class Bills
 */
public class Bills implements Serializable {
	private static final long serialVersionUID = -5723044348394618219L;

	// List of Bill object
	List<Bill> bills;

	// item count of current list
	Integer availableCount;

	// total count of items
	Integer totalCount;

	/**
	 * *
	 *
	 * @return
	 */
	public List<Bill> getBills() {
		return bills;
	}

	/**
	 * *
	 *
	 * @param bills
	 */
	public void setBills(List<Bill> bills) {
		this.bills = bills;
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
