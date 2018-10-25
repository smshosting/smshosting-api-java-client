/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import java.util.List;

public class SendResult extends GenericResponse{

    public final static String SEND_SANDBOX="SANDBOX";
    public final static String SEND_STATUS_INSERTED="INSERTED";
    public final static String SEND_STATUS_NOT_INSERTED="NOT_INSERTED";
    
    private String from;
    private String text;
    private String transactionId;
    private int smsInserted;
    private int smsNotInserted;
    private List<Sms> sms;
    
    //caso misto
    private Integer pushInserted;

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

    public Integer getPushInserted() {
        return pushInserted;
    }

    public void setPushInserted(Integer pushInserted) {
        this.pushInserted = pushInserted;
    }

    public static class Sms {

        private Integer id;
        private String to;
        private String status;
        private String statusDetail;
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
