package com.mobilemoney.common.model;

import java.io.Serializable;
import java.util.List;

/***
 * Class Identifiers
 */
public class Identifiers implements Serializable {
    private static final long serialVersionUID = 3045928371007947053L;

    // List of identifiers
    private List<AccountIdentifier> identifiers;

    /***
     * Constructor with arguments
     *
     * @param identifiers
     */
    public Identifiers(List<AccountIdentifier> identifiers) {
        this.identifiers = identifiers;
    }

    /***
     *
     * @return
     */
    public List<AccountIdentifier> getIdentifiers() {
        return identifiers;
    }
}
