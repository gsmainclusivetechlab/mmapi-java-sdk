package com.mobilemoney.billpayment.model;

import com.mobilemoney.base.util.JSONFormatter;
import java.io.Serializable;
import java.util.List;

/**
 * * Class BillPayments
 */
public class BillPayments implements Serializable {
	private static final long serialVersionUID = 3236737905843913075L;

	// List of BillPay object
	List<BillPay> billPayments;

	// item count of current list
	Integer availableCount;

	// total count of items
	Integer totalCount;

	/**
	 * *
	 *
	 * @return
	 */
	public List<BillPay> getBillPayments() {
		return billPayments;
	}

	/**
	 * *
	 *
	 * @param billPayments
	 */
	public void setBillPayments(List<BillPay> billPayments) {
		this.billPayments = billPayments;
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
