package com.mobilemoney.common.model;

import com.mobilemoney.common.model.Name;

import java.io.Serializable;

/***
 * Class AccountHolderName
 */
public class AccountHolderName implements Serializable {
    private static final long serialVersionUID = 9030744848411379852L;

    // KYCInformation subject details
    private Name name;

    // Legal Entity Identifier of the organisation holding the account
    private String lei;

    /***
     *
     * @return
     */
    public Name getName() {
        return name;
    }

    /***
     *
     * @return
     */
    public String getLei() {
        return lei;
    }
}
