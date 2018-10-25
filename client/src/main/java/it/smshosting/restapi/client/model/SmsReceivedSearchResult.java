/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import java.util.List;

public class SmsReceivedSearchResult {
    
    private Metadata metadata;
    private List<SmsReceivedInfo> smsList;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<SmsReceivedInfo> getSmsList() {
        return smsList;
    }

    public void setSmsList(List<SmsReceivedInfo> smsList) {
        this.smsList = smsList;
    }
}
