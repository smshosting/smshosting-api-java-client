/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.EmailTemplatesResult;

public class EmailTemplatesListResponse extends GenericResponse {

    private EmailTemplatesResult emailTemplatesResult;

    public EmailTemplatesResult getEmailTemplatesResult() {
        return emailTemplatesResult;
    }

    public void setEmailTemplatesResult(EmailTemplatesResult emailTemplatesResult) {
        this.emailTemplatesResult = emailTemplatesResult;
    }      

}
