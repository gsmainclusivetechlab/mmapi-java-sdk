package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class AuthorisationCodeResponse
 */
public class AuthorisationCodeResponse extends AuthorisationCodeRequest implements Serializable {
    private static final long serialVersionUID = 8664931778688158227L;

    // The code that will be presented to the other party for redemption
    private String authorisationCode;

    // State of the authorisation code
    private String codeState;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    /***
     *
     * @return
     */
    public String getAuthorisationCode() {
        return authorisationCode;
    }

    /***
     *
     * @param authorisationCode
     */
    public void setAuthorisationCode(String authorisationCode) {
        this.authorisationCode = authorisationCode;
    }

    /***
     *
     * @return
     */
    public String getCodeState() {
        return codeState;
    }

    /***
     *
     * @param codeState
     */
    public void setCodeState(String codeState) {
        this.codeState = codeState;
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
}
