/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsSearchResult;

public class SmsSearchResponse extends GenericResponse {

    private SmsSearchResult smsSearchResult;

    public SmsSearchResult getSmsSearchResult() {
        return smsSearchResult;
    }

    public void setSmsSearchResult(SmsSearchResult smsSearchResult) {
        this.smsSearchResult = smsSearchResult;
    }

}
