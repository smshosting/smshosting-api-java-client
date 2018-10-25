/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

public class SmsReceivedInfo {
    private Integer simId;
    private String from;
    private String text; 
    private String receiveDate;
  
    public SmsReceivedInfo() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    public Integer getSimId() {
        return simId;
    }

    public void setSimId(Integer simId) {
        this.simId = simId;
    }
    

    
}
