/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsInfo;
import java.util.List;

public class CancelSmsResponse extends GenericResponse{
    
    private List<SmsInfo> smsList;

    public List<SmsInfo> getSmsList() {
        return smsList;
    }

    public void setSmsList(List<SmsInfo> smsList) {
        this.smsList = smsList;
    }
    
}
