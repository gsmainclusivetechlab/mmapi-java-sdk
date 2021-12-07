package com.mobilemoney.accountlinking.model;

import com.mobilemoney.base.util.JSONFormatter;
import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.DebitParty;
import com.mobilemoney.common.model.RequestingOrganisation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Class AccountLink
 */
public class AccountLink implements Serializable {
    private static final long serialVersionUID = -1455607870159785699L;

    // A collection of key/value pairs that enable the party to be identified. Keys include MSISDN and Wallet Identifier
    private List<DebitParty> sourceAccountIdentifiers = new ArrayList<>();

    // Indicates the mode of operation for the Link
    private String mode;

    // Indicates the status of the Link
    private String status;

    // An object that details the originating organisation of the request
    private RequestingOrganisation requestingOrganisation;

    // The date and time of the request as supplied by the client
    private String requestDate;

    // A collection of key/value pairs. These can be used to populate provider specific fields.
    private List<CustomData> customData;

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
    
    /***
     * Returns JSON object
     * @return
     */
    public String toJSON() {
        return JSONFormatter.toJSON(this);
    }
}
