package com.mobilemoney.common.model;

import com.mobilemoney.base.util.JSONFormatter;
import java.io.Serializable;
import java.util.List;

/**
 * * Class Transactions
 */
public class Transactions implements Serializable {
	private static final long serialVersionUID = 2988838396873424385L;

	// List of Transaction object
	List<Transaction> transactions;

	// item count of current list
	Integer availableCount;

	// total count of items
	Integer totalCount;

	/**
	 * *
	 *
	 * @return
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * *
	 *
	 * @param transactions
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
