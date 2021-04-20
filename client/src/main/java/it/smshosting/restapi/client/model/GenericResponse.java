/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse {
    @JsonAlias({ "errorCode" })
    private Integer statusCode;
    @JsonAlias({ "errorMsg" })
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        boolean res = false;
        if (statusCode != null) {
            if (statusCode < 200 || statusCode > 299) {
                res = true;
            }
        }
        return res;
    }

}
