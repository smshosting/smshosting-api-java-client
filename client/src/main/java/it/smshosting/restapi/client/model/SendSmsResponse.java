/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsResult;

public class SendSmsResponse extends GenericResponse {

    private SmsResult smsResult;

    public SmsResult getSmsResult() {
        return smsResult;
    }

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
