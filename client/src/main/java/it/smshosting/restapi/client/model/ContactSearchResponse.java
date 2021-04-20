/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.ContactSearchResult;

public class ContactSearchResponse extends GenericResponse {

    private ContactSearchResult contactSearchResult;

    public ContactSearchResult getContactSearchResult() {
        return contactSearchResult;
    }

    public void setContactSearchResult(ContactSearchResult contactSearchResult) {
        this.contactSearchResult = contactSearchResult;
    }

}
