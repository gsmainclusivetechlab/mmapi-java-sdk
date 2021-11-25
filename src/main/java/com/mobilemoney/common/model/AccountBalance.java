package com.mobilemoney.common.model;

/***
 * Class AccountBalance
 */
public final class AccountBalance {
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
     * @return
     */
    public String getCurrentBalance() {
        return currentBalance;
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
     * @return
     */
    public String getReservedBalance() {
        return reservedBalance;
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
     * @return
     */
    public String getCurrency() {
        return currency;
    }
}
