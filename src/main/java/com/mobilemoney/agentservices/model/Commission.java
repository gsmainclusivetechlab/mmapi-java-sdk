/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.agentservices.model;

import java.io.Serializable;

/**
 *
 * Class Commission
 */
public class Commission implements Serializable {

    private static final long serialVersionUID = 452599719495453175L;

    //Defines the type of commission.
    private String commissionType;

    //Defines the amount of the commission.
    private String commissionAmount;

    //Defines the currency for the given commission.
    private String commissionCurrency;

    /**
     * *
     *
     * @return
     */
    public String getCommissionType() {
        return commissionType;
    }

    /**
     * *
     *
     * @param commissionType
     */
    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    /**
     * *
     *
     * @return
     */
    public String getCommissionAmount() {
        return commissionAmount;
    }

    /**
     * *
     *
     * @param commissionAmount
     */
    public void setCommissionAmount(String commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    /**
     * *
     *
     * @return
     */
    public String getCommissionCurrency() {
        return commissionCurrency;
    }

    /**
     * *
     *
     * @param commissionCurrency
     */
    public void setCommissionCurrency(String commissionCurrency) {
        this.commissionCurrency = commissionCurrency;
    }

}
