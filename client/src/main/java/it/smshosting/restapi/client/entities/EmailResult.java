/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailResult {
    public final static String SEND_SANDBOX = "SANDBOX";
    public final static String SEND_STATUS_INSERTED = "INSERTED";
    public final static String SEND_STATUS_NOT_INSERTED = "NOT_INSERTED";

    private Integer campaignId;
    private int emailInserted;
    private int emailNotInserted;
    private List<Email> email;

    public static class Email {

        private Long id;
        private String to;
        private String status;
        private String statusDetail;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDetail() {
            return statusDetail;
        }

        public void setStatusDetail(String statusDetail) {
            this.statusDetail = statusDetail;
        }

    }

    public int getEmailInserted() {
        return emailInserted;
    }

    public void setEmailInserted(int emailInserted) {
        this.emailInserted = emailInserted;
    }

    public int getEmailNotInserted() {
        return emailNotInserted;
    }

    public void setEmailNotInserted(int emailNotInserted) {
        this.emailNotInserted = emailNotInserted;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }    
}
