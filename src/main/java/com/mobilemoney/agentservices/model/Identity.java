/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.agentservices.model;

import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.internationaltransfer.model.KYCInformation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Class Identity
 */
public class Identity implements Serializable {

    private static final long serialVersionUID = 7018025644682054103L;

    //KYC information
    private KYCInformation identityKyc;

    //Describes the relationship that the identity holds with the account.
    private String accountRelationship;

    //Indicates the status of the identity’s KYC verification.
    private String kycVerificationStatus;

    //Indicates the entity (e.g. mobile money agent) that has verified the KYC of the identity.
    private String kycVerificationEntity;

    //Indicates the KYC level that the identity is associated with.
    private Integer kycLevel;

    //A collection of key/value pairs. These can be used to populate provider specific fields.
    private List<CustomData> customData = new ArrayList<>();

    /**
     * *
     *
     * @return
     */
    public KYCInformation getIdentityKyc() {
        return identityKyc;
    }

    /**
     * *
     *
     * @param identityKyc
     */
    public void setIdentityKyc(KYCInformation identityKyc) {
        this.identityKyc = identityKyc;
    }

    /**
     * *
     *
     * @return
     */
    public String getAccountRelationship() {
        return accountRelationship;
    }

    /**
     * *
     *
     * @param accountRelationship
     */
    public void setAccountRelationship(String accountRelationship) {
        this.accountRelationship = accountRelationship;
    }

    /**
     * *
     *
     * @return
     */
    public String getKycVerificationStatus() {
        return kycVerificationStatus;
    }

    /**
     * *
     *
     * @param kycVerificationStatus
     */
    public void setKycVerificationStatus(String kycVerificationStatus) {
        this.kycVerificationStatus = kycVerificationStatus;
    }

    /**
     * *
     *
     * @return
     */
    public String getKycVerificationEntity() {
        return kycVerificationEntity;
    }

    /**
     * *
     *
     * @param kycVerificationEntity
     */
    public void setKycVerificationEntity(String kycVerificationEntity) {
        this.kycVerificationEntity = kycVerificationEntity;
    }

    /**
     * *
     *
     * @return
     */
    public Integer getKycLevel() {
        return kycLevel;
    }

    /**
     * *
     *
     * @param kycLevel
     */
    public void setKycLevel(Integer kycLevel) {
        this.kycLevel = kycLevel;
    }

    /**
     * *
     *
     * @return
     */
    public List<CustomData> getCustomData() {
        return customData;
    }

    /**
     * *
     *
     * @param customData
     */
    public void setCustomData(List<CustomData> customData) {
        this.customData = customData;
    }

}
