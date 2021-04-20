/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsReceivedResult;

public class SmsReceivedSearchResponse extends GenericResponse {

    private SmsReceivedResult smsReceivedResult;

    public SmsReceivedResult getSmsReceivedResult() {
        return smsReceivedResult;
    }

    public void setSmsReceivedResult(SmsReceivedResult smsReceivedResult) {
        this.smsReceivedResult = smsReceivedResult;
    }
}
