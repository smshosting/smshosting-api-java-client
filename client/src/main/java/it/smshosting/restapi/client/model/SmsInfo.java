/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

public class SmsInfo {
    
    public static final String TYPE_SMS = "SMS";
    public static final String TYPE_PUSH = "PUSH";
    
    private Integer id;
    private String to;
    private String text;
    private String from;  
    private String status;
    private String statusDetail;
    private String insertDate;
    private String sentDate;
    private String deliveryDate;
    private String transactionId;
    private String type;
    private MultimediaContent multimediaContent;
    private Double price;
    
    
    /** Creates a new instance of Result */
    public SmsInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultimediaContent getMultimediaContent() {
        return multimediaContent;
    }

    public void setMultimediaContent(MultimediaContent multimediaContent) {
        this.multimediaContent = multimediaContent;
    }
    
    public static class MultimediaContent {
        
        public static final String MULTIMEDIA_TYPE = "IMAGE";
        
        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        
        
    }
            
}
