package com.mobilemoney.internationaltransfer.model;

import com.mobilemoney.common.model.KYCSubject;

import java.io.Serializable;
import java.util.List;

/***
 * Class KYC
 */
public class KYC implements Serializable {
    private static final long serialVersionUID = 5971130314871094354L;

    // Country of birth of the KYC subject
    private String birthCountry;

    // Contact phone number of the KYC subject
    private String contactPhone;

    // Birth date of the KYC subject
    private String dateOfBirth;

    // Email address of the KYC subject
    private String emailAddress;

    // Employer name of the KYC subject
    private String employerName;

    // Gender of the KYC subject
    private String gender;

    // Nationality of the KYC subject
    private String nationality;

    // Occupation of the KYC subject
    private String occupation;

    // Postal address of the KYC subject
    private PostalAddress postalAddress;

    // KYC Person
    private KYCSubject subjectName;

    // Properties containing the forms of identification that are associated with the subject
    private List<Identification> idDocument;

    /***
     *
     * @return
     */
    public String getBirthCountry() {
        return birthCountry;
    }

    /***
     *
     * @param birthCountry
     */
    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    /***
     *
     * @return
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /***
     *
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /***
     *
     * @return
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /***
     *
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /***
     *
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /***
     *
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /***
     *
     * @return
     */
    public String getEmployerName() {
        return employerName;
    }

    /***
     *
     * @param employerName
     */
    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    /***
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /***
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /***
     *
     * @return
     */
    public String getNationality() {
        return nationality;
    }

    /***
     *
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /***
     *
     * @return
     */
    public String getOccupation() {
        return occupation;
    }

    /***
     *
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /***
     *
     * @return
     */
    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    /***
     *
     * @param postalAddress
     */
    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    /***
     *
     * @return
     */
    public KYCSubject getSubjectName() {
        return subjectName;
    }

    /***
     *
     * @param subjectName
     */
    public void setSubjectName(KYCSubject subjectName) {
        this.subjectName = subjectName;
    }

    /***
     *
     * @return
     */
    public List<Identification> getIdDocument() {
        return idDocument;
    }

    /***
     *
     * @param idDocument
     */
    public void setIdDocument(List<Identification> idDocument) {
        this.idDocument = idDocument;
    }
}
