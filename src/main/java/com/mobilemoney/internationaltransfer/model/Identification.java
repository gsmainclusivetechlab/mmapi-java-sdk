package com.mobilemoney.internationaltransfer.model;

import java.io.Serializable;

/***
 * Class identification
 */
public class Identification implements Serializable {
    private static final long serialVersionUID = 5539217252061435005L;

    // Type of identification
    private String idType;

    // Reference pertaining to the type of identification
    private String idNumber;

    // Date of issue
    private String issueDate;

    // Date of expiry
    private String expiryDate;

    // Indicates the organisation/government entity
    private String issuer;

    // Place of issue
    private String issuerPlace;

    // Country where the identification type was issued
    private String issuerCountry;

    // ID Type of otherid is specified
    private String otherIddescription;

    /***
     * Constructor with argument
     *
     * @param idType
     */
    public Identification(final String idType) {
        this.idType = idType;
    }

    /***
     *
     * @return
     */
    public String getIdType() {
        return idType;
    }

    /***
     *
     * @param idType
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /***
     *
     * @return
     */
    public String getIdNumber() {
        return idNumber;
    }

    /***
     *
     * @param idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /***
     *
     * @return
     */
    public String getIssueDate() {
        return issueDate;
    }

    /***
     *
     * @param issueDate
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /***
     *
     * @return
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /***
     *
     * @param expiryDate
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /***
     *
     * @return
     */
    public String getIssuer() {
        return issuer;
    }

    /***
     *
     * @param issuer
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /***
     *
     * @return
     */
    public String getIssuerPlace() {
        return issuerPlace;
    }

    /***
     *
     * @param issuerPlace
     */
    public void setIssuerPlace(String issuerPlace) {
        this.issuerPlace = issuerPlace;
    }

    /***
     *
     * @return
     */
    public String getIssuerCountry() {
        return issuerCountry;
    }

    /***
     *
     * @param issuerCountry
     */
    public void setIssuerCountry(String issuerCountry) {
        this.issuerCountry = issuerCountry;
    }

    /***
     *
     * @return
     */
    public String getOtherIddescription() {
        return otherIddescription;
    }

    /***
     *
     * @param otherIddescription
     */
    public void setOtherIddescription(String otherIddescription) {
        this.otherIddescription = otherIddescription;
    }
}
