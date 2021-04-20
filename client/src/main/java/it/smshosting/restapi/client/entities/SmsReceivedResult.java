/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsReceivedResult {

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
