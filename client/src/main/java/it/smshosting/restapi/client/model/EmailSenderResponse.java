/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.EmailSender;
import java.util.List;

public class EmailSenderResponse extends GenericResponse {
    
    private List<EmailSender> emailSenderList;

    public List<EmailSender> getEmailSenderList() {
        return emailSenderList;
    }

    public void setEmailSenderList(List<EmailSender> emailSenderList) {
        this.emailSenderList = emailSenderList;
    }
}
