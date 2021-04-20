/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailCampaignsResult {
    private Metadata metadata;
    @JsonAlias("campaignList")
    private List<EmailCampaign> emailCampaign;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<EmailCampaign> getEmailCampaign() {
        return emailCampaign;
    }

    public void setEmailCampaign(List<EmailCampaign> emailCampaign) {
        this.emailCampaign = emailCampaign;
    }    
}
