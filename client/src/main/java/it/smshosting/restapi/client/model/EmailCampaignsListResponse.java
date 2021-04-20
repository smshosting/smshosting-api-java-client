/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.EmailCampaignsResult;

public class EmailCampaignsListResponse extends GenericResponse {

    private EmailCampaignsResult emailCampaignsResult;

    public EmailCampaignsResult getEmailCampaignsResult() {
        return emailCampaignsResult;
    }

    public void setEmailCampaignsResult(EmailCampaignsResult emailCampaignsResult) {
        this.emailCampaignsResult = emailCampaignsResult;
    }
    
}
