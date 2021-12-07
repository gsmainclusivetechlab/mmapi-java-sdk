/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilemoney.accountlinking.model;

import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.DebitParty;
import com.mobilemoney.common.model.RequestingOrganisation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Class AccountLinkResponse
 */
public class AccountLinkResponse implements Serializable{
    private static final long serialVersionUID = -8703333476110629289L;
    
    //Indicates the Link reference. This enables a linked account to be uniquely identified.
    private String linkReference;
    
    //A collection of key/value pairs that enable the party to be identified. Keys include MSISDN and Wallet Identifier.
    private List<DebitParty> sourceAccountIdentifiers = new ArrayList<>();
    
    //Indicates the Link mode of operation. This can be set to 'pull' (The link can be used by the client to debit the target account held by the provider), 'push' (The link can be used by the client to credit the target account held by the provider) or 'both'.
    private String mode;
    
    //Indicates the status of the Link.
    private String status;
    
    //An object that details the originating organisation of the request.
    private RequestingOrganisation requestingOrganisation;
    
    //Date and time when the object was created by the API Provider.
    private String creationDate;
    
    //Date and time when the object was modified by the API Provider.
    private String modificationDate;
    
    //The date and time of the request as supplied by the client.
    private String requestDate;
    
    //A collection of key/value pairs. These can be used to populate provider specific fields.
    private List<CustomData> customData;

    /***
     *
     * @return
     */
    public String getLinkReference() {
        return linkReference;
    }

    /***
     *
     * @param linkReference 
     */
    public void setLinkReference(String linkReference) {
        this.linkReference = linkReference;
    }

    /***
     *
     * @return
     */
    public List<DebitParty> getSourceAccountIdentifiers() {
        return sourceAccountIdentifiers;
    }

    /***
     *
     * @param sourceAccountIdentifiers 
     */
    public void setSourceAccountIdentifiers(List<DebitParty> sourceAccountIdentifiers) {
        this.sourceAccountIdentifiers = sourceAccountIdentifiers;
    }

    /***
     *
     * @return
     */
    public String getMode() {
        return mode;
    }

    /***
     *
     * @param mode 
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /***
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /***
     *
     * @param status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /***
     *
     * @return
     */
    public RequestingOrganisation getRequestingOrganisation() {
        return requestingOrganisation;
    }

    /***
     *
     * @param requestingOrganisation 
     */
    public void setRequestingOrganisation(RequestingOrganisation requestingOrganisation) {
        this.requestingOrganisation = requestingOrganisation;
    }

    /***
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /***
     *
     * @param creationDate 
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /***
     *
     * @return
     */
    public String getModificationDate() {
        return modificationDate;
    }

    /***
     *
     * @param modificationDate 
     */
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    /***
     *
     * @return
     */
    public String getRequestDate() {
        return requestDate;
    }

    /***
     *
     * @param requestDate 
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /***
     *
     * @return
     */
    public List<CustomData> getCustomData() {
        return customData;
    }

    /***
     *
     * @param customData 
     */
    public void setCustomData(List<CustomData> customData) {
        this.customData = customData;
    }
    
}
