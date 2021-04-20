/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsResult {

    public final static String SEND_SANDBOX = "SANDBOX";
    public final static String SEND_STATUS_INSERTED = "INSERTED";
    public final static String SEND_STATUS_NOT_INSERTED = "NOT_INSERTED";

    private String from;
    private String text;
    private String transactionId;
    private int smsInserted;
    private int smsNotInserted;
    private List<Sms> sms;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getSmsInserted() {
        return smsInserted;
    }

    public void setSmsInserted(int smsInserted) {
        this.smsInserted = smsInserted;
    }

    public int getSmsNotInserted() {
        return smsNotInserted;
    }

    public void setSmsNotInserted(int smsNotInserted) {
        this.smsNotInserted = smsNotInserted;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Sms> getSms() {
        return sms;
    }

    public void setSms(List<Sms> sms) {
        this.sms = sms;
    }

    public static class Sms {

        private String id;
        private String to;
        private String status;
        private String statusDetail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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
}
