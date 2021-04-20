/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.EmailResult;

public class SendEmailCampaignResponse extends GenericResponse {

    private EmailResult emailResult;

    public EmailResult getEmailResult() {
        return emailResult;
    }

    public void setEmailResult(EmailResult emailResult) {
        this.emailResult = emailResult;
    }
}
