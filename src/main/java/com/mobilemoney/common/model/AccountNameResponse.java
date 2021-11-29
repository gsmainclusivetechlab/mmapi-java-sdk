package com.mobilemoney.common.model;

import com.mobilemoney.common.model.KYCSubject;

import java.io.Serializable;

/***
 * Class AccountNameResponse
 */
public class AccountNameResponse implements Serializable {
    private static final long serialVersionUID = 9030744848411379852L;

    // KYC subject details
    private KYCSubject name;

    // Legal Entity Identifier of the organisation holding the account
    private String lei;

    /***
     *
     * @return
     */
    public KYCSubject getName() {
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
