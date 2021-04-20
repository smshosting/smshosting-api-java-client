/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailTemplatesResult {
    private Metadata metadata;
    @JsonAlias("templates")
    private List<EmailTemplate> emailTemplatesList;

    public List<EmailTemplate> getEmailTemplatesList() {
        return emailTemplatesList;
    }

    public void setEmailTemplatesList(List<EmailTemplate> emailTemplatesList) {
        this.emailTemplatesList = emailTemplatesList;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }    
}
