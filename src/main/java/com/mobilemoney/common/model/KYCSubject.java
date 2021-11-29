package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class KYCSubject
 */
public class KYCSubject implements Serializable {
    private static final long serialVersionUID = -810970211056063834L;

    // Title of the KYC subject
    private String title;

    // First Name of the KYC subject
    private String firstName;

    // Middle Name of the KYC subject
    private String middleName;

    // Last Name of the KYC subject
    private String lastName;

    // Full Name of the KYC subject
    private String fullName;

    // Native Name of the KYC subject
    private String nativeName;

    /***
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /***
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /***
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /***
     *
     * @return
     */
    public String getMiddleName() {
        return middleName;
    }

    /***
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /***
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /***
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /***
     *
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /***
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /***
     *
     * @return
     */
    public String getNativeName() {
        return nativeName;
    }

    /***
     *
     * @param nativeName
     */
    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
