package com.mobilemoney.internationaltransfer.model;

import java.io.Serializable;

/***
 * Class PostalAddress
 */
public class PostalAddress implements Serializable {
    private static final long serialVersionUID = 4720501534498888977L;

    // First line of the address
    private String addressLine1;

    // Second line of the address
    private String addressLine2;

    // Third line of the address
    private String addressLine3;

    // City/Town
    private String city;

    // State or Province
    private String stateProvince;

    // Postal Code
    private String postalCode;

    // Country
    private String country;

    /***
     * Constructor with argument
     *
     * @param country
     */
    public PostalAddress(final String country) {
        this.country = country;
    }

    /***
     *
     * @return
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /***
     *
     * @param addressLine1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /***
     *
     * @return
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /***
     *
     * @param addressLine2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /***
     *
     * @return
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /***
     *
     * @param addressLine3
     */
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    /***
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /***
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /***
     *
     * @return
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /***
     *
     * @param stateProvince
     */
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    /***
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /***
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /***
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /***
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
