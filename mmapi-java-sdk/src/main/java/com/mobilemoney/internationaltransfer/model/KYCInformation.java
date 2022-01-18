package com.mobilemoney.internationaltransfer.model;

import com.mobilemoney.common.model.Name;

import java.io.Serializable;
import java.util.List;

/***
 * Class KYCInformation
 */
public class KYCInformation implements Serializable {
    private static final long serialVersionUID = 5971130314871094354L;

    // Country of birth of the KYCInformation subject
    private String birthCountry;

    // Contact phone number of the KYCInformation subject
    private String contactPhone;

    // Birth date of the KYCInformation subject
    private String dateOfBirth;

    // Email address of the KYCInformation subject
    private String emailAddress;

    // Employer name of the KYCInformation subject
    private String employerName;

    // Gender of the KYCInformation subject
    private String gender;

    // Nationality of the KYCInformation subject
    private String nationality;

    // Occupation of the KYCInformation subject
    private String occupation;

    // Postal address of the KYCInformation subject
    private Address postalAddress;

    // KYCInformation Person
    private Name subjectName;

    // Properties containing the forms of identification that are associated with the subject
    private List<IdDocument> idDocument;

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
    public Address getPostalAddress() {
        return postalAddress;
    }

    /***
     *
     * @param address
     */
    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    /***
     *
     * @return
     */
    public Name getSubjectName() {
        return subjectName;
    }

    /***
     *
     * @param subjectName
     */
    public void setSubjectName(Name subjectName) {
        this.subjectName = subjectName;
    }

    /***
     *
     * @return
     */
    public List<IdDocument> getIdDocument() {
        return idDocument;
    }

    /***
     *
     * @param idDocument
     */
    public void setIdDocument(List<IdDocument> idDocument) {
        this.idDocument = idDocument;
    }
}
