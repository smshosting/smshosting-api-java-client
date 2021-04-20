/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsReceivedSimResponse;
import java.util.List;

public class SmsReceivedSearchResponseList extends GenericResponse {

    private List<SmsReceivedSimResponse> smsReceivedSimResponseList;

    public List<SmsReceivedSimResponse> getSmsReceivedSimResponseList() {
        return smsReceivedSimResponseList;
    }

    public void setSmsReceivedSimResponseList(List<SmsReceivedSimResponse> SmsReceivedSimResponseList) {
        this.smsReceivedSimResponseList = SmsReceivedSimResponseList;
    }
}
