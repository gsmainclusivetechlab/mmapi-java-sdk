package com.mobilemoney.common.model;

import java.io.Serializable;

public class RequestingOrganisation implements Serializable {
    private static final long serialVersionUID = -4293682665395526535L;

    // Identifies the identifier type of the requesting organisation
    private String requestingOrganisationIdentifierType;

    // Contains the requesting organisation identifier
    private String requestingOrganisationIdentifier;

    public String getRequestingOrganisationIdentifierType() {
        return requestingOrganisationIdentifierType;
    }

    public void setRequestingOrganisationIdentifierType(String requestingOrganisationIdentifierType) {
        this.requestingOrganisationIdentifierType = requestingOrganisationIdentifierType;
    }

    public String getRequestingOrganisationIdentifier() {
        return requestingOrganisationIdentifier;
    }

    public void setRequestingOrganisationIdentifier(String requestingOrganisationIdentifier) {
        this.requestingOrganisationIdentifier = requestingOrganisationIdentifier;
    }
}
