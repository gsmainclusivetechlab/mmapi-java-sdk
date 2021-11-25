package com.mobilemoney.common.model;

import java.io.Serializable;
import java.util.List;

/***
 * Class Identifiers
 */
public class Identifiers implements Serializable {
    private static final long serialVersionUID = 3045928371007947053L;

    // List of identifiers
    private List<IdentifierData> identifiers;

    /***
     * Constructor with arguments
     *
     * @param identifiers
     */
    public Identifiers(List<IdentifierData> identifiers) {
        this.identifiers = identifiers;
    }

    /***
     *
     * @return
     */
    public List<IdentifierData> getIdentifiers() {
        return identifiers;
    }
}
