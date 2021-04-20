/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.SmsBulkResult;

public class SendSmsBulkResponse extends GenericResponse {
    private SmsBulkResult smsBulkResult;

    public SmsBulkResult getSmsBulkResult() {
        return smsBulkResult;
    }

    public void setSmsBulkResult(SmsBulkResult smsBulkResult) {
        this.smsBulkResult = smsBulkResult;
    }
}
