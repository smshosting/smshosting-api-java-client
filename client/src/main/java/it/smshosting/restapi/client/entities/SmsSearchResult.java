/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsSearchResult {

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
