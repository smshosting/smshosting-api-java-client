/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailCampaign {

    private Integer id;
    private String campaignTitle;
    private Integer templateId;
    private String from;
    private String fromName;
    private String group;
    private String subject;
    private String subjectPreviewText;
    private boolean enableOpenTracking;
    private boolean enableLinkTracking;
    private String deferredDate;
    private MailSenderCampaignStats stats;

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectPreviewText() {
        return subjectPreviewText;
    }

    public void setSubjectPreviewText(String subjectPreviewText) {
        this.subjectPreviewText = subjectPreviewText;
    }

    public boolean isEnableOpenTracking() {
        return enableOpenTracking;
    }

    public void setEnableOpenTracking(boolean enableOpenTracking) {
        this.enableOpenTracking = enableOpenTracking;
    }

    public boolean isEnableLinkTracking() {
        return enableLinkTracking;
    }

    public void setEnableLinkTracking(boolean enableLinkTracking) {
        this.enableLinkTracking = enableLinkTracking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeferredDate() {
        return deferredDate;
    }

    public void setDeferredDate(String deferredDate) {
        this.deferredDate = deferredDate;
    }

    public MailSenderCampaignStats getStats() {
        return stats;
    }

    public void setStats(MailSenderCampaignStats stats) {
        this.stats = stats;
    }

    public static class MailSenderCampaignStats {

        private int countEmailInserted;
        private int countEmailStatusPending;
        private int countEmailStatusNoSent;
        private int countEmailTotSent;
        private int countEmailStatusBounced;

        private int countEmailOpenedUnique;
        private int countEmailClickUnique;
        private int countEmailStatusSpamReport;
        private int countEmailCauseUnsubscribed;

        public int getCountEmailInserted() {
            return countEmailInserted;
        }

        public void setCountEmailInserted(int countEmailInserted) {
            this.countEmailInserted = countEmailInserted;
        }

        public int getCountEmailStatusPending() {
            return countEmailStatusPending;
        }

        public void setCountEmailStatusPending(int countEmailStatusPending) {
            this.countEmailStatusPending = countEmailStatusPending;
        }

        public int getCountEmailStatusNoSent() {
            return countEmailStatusNoSent;
        }

        public void setCountEmailStatusNoSent(int countEmailStatusNoSent) {
            this.countEmailStatusNoSent = countEmailStatusNoSent;
        }

        public int getCountEmailTotSent() {
            return countEmailTotSent;
        }

        public void setCountEmailTotSent(int countEmailTotSent) {
            this.countEmailTotSent = countEmailTotSent;
        }

        public int getCountEmailStatusBounced() {
            return countEmailStatusBounced;
        }

        public void setCountEmailStatusBounced(int countEmailStatusBounced) {
            this.countEmailStatusBounced = countEmailStatusBounced;
        }

        public int getCountEmailOpenedUnique() {
            return countEmailOpenedUnique;
        }

        public void setCountEmailOpenedUnique(int countEmailOpenedUnique) {
            this.countEmailOpenedUnique = countEmailOpenedUnique;
        }

        public int getCountEmailClickUnique() {
            return countEmailClickUnique;
        }

        public void setCountEmailClickUnique(int countEmailClickUnique) {
            this.countEmailClickUnique = countEmailClickUnique;
        }

        public int getCountEmailStatusSpamReport() {
            return countEmailStatusSpamReport;
        }

        public void setCountEmailStatusSpamReport(int countEmailStatusSpamReport) {
            this.countEmailStatusSpamReport = countEmailStatusSpamReport;
        }

        public int getCountEmailCauseUnsubscribed() {
            return countEmailCauseUnsubscribed;
        }

        public void setCountEmailCauseUnsubscribed(int countEmailCauseUnsubscribed) {
            this.countEmailCauseUnsubscribed = countEmailCauseUnsubscribed;
        }
    }    
}
