/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import java.util.List;

public class SmsSearchResult extends GenericResponse{
    
    private Metadata metadata;
    private List<SmsInfo> smsList;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<SmsInfo> getSmsList() {
        return smsList;
    }

    public void setSmsList(List<SmsInfo> smsList) {
        this.smsList = smsList;
    }
    
}
