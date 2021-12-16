package com.mobilemoney.common.model;

/***
 * Class Balance
 */
public final class Balance {
	// Harmonised representation of the account status
	private String accountStatus;

	// Current outstanding balance
	private String currentBalance;

	// Balance that is able to be debited for an account
	private String availableBalance;

	// Portion of the balance that is reserved
	private String reservedBalance;

	// Sum of uncleared funds in an account
	private String unclearedBalance;

	// Currency for all returned balances
	private String currency;

	/***
	 * 
	 * @return
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/***
	 * 
	 * @param accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/***
	 * 
	 * @return
	 */
	public String getCurrentBalance() {
		return currentBalance;
	}

	/***
	 * 
	 * @param currentBalance
	 */
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	/***
	 * 
	 * @return
	 */
	public String getAvailableBalance() {
		return availableBalance;
	}

	/***
	 * 
	 * @param availableBalance
	 */
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}

	/***
	 * 
	 * @return
	 */
	public String getReservedBalance() {
		return reservedBalance;
	}

	/***
	 * 
	 * @param reservedBalance
	 */
	public void setReservedBalance(String reservedBalance) {
		this.reservedBalance = reservedBalance;
	}

	/***
	 * 
	 * @return
	 */
	public String getUnclearedBalance() {
		return unclearedBalance;
	}

	/***
	 * 
	 * @param unclearedBalance
	 */
	public void setUnclearedBalance(String unclearedBalance) {
		this.unclearedBalance = unclearedBalance;
	}

	/***
	 * 
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/***
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
