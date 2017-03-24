/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import it.smshosting.restapi.client.model.Estimate;
import it.smshosting.restapi.client.model.SendResult;
import it.smshosting.restapi.client.model.SmsReceivedSearchResult;
import it.smshosting.restapi.client.model.SmsReceivedSimResult;
import it.smshosting.restapi.client.model.SmsSearchResult;
import it.smshosting.restapi.client.model.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client Rest API Smshosting.it
 *
 * https://www.smshosting.it/it/docs/sms-rest-api/introduzione
 *
 */
public class SmsHostingClient {

    private static final Logger log = Logger.getLogger(SmsHostingClient.class.getName());

    public static final String DEFAULT_ENDPOINT = "https://api.smshosting.it/rest/api/";

    private String authKey;
    private String authSecret;

    public SmsHostingClient(String authKey, String authSecret) {
        this.authKey = authKey;
        this.authSecret = authSecret;

        //mapper for unirest
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();
           
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    ///////
    //sms requests
    //////
    public SendResult sendSms(String from,
            String to,
            String group,
            String text,
            Date sendDate,
            String transactionId,
            Boolean sandbox,
            String statusCallback,
            String encoding) {
        try {
            // SMS send
            HttpResponse<SendResult> response = Unirest.post(buildURL("sms/send"))
                    .basicAuth(authKey, authSecret)
                    .field("from", from)
                    .field("to", to)
                    .field("group", group)
                    .field("text", text)
                    .field("date", sendDate)
                    .field("transactionId", transactionId)
                    .field("sandbox", sandbox != null ? sandbox.toString() : null)
                    .field("statusCallback", statusCallback)
                    .field("encoding", encoding)
                    .asObject(SendResult.class);
            return response.getBody();

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public SendResult sendSmsBulk(String from,
            String to,
            String group,
            String text,
            Date sendDate,
            String transactionId,
            Boolean sandbox,
            String statusCallback,
            String transactionCallback,
            String encoding) {
        try {
            // SMS send
            HttpResponse<SendResult> response = Unirest.post(buildURL("sms/sendbulk"))
                    .basicAuth(authKey, authSecret)
                    .field("from", from)
                    .field("to", to)
                    .field("group", group)
                    .field("text", text)
                    .field("date", sendDate)
                    .field("transactionId", transactionId)
                    .field("sandbox", sandbox != null ? sandbox.toString() : null)
                    .field("statusCallback", statusCallback)
                    .field("transactionCallback", transactionCallback)
                    .field("encoding", encoding)
                    .asObject(SendResult.class);
            return response.getBody();

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public Estimate estimateSendSms(String from,
            String to,
            String group,
            String text,
            String encoding) {
        try {
            // SMS send
            HttpResponse<Estimate> response = Unirest.post(buildURL("sms/estimate"))
                    .basicAuth(authKey, authSecret)
                    .field("from", from)
                    .field("to", to)
                    .field("group", group)
                    .field("text", text)
                    .asObject(Estimate.class);
            return response.getBody();

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public boolean cancelSms(String id, String transactionId) {
        try {
            // SMS send
            HttpResponse<String> response = Unirest.post(buildURL("sms/cancel"))
                    .basicAuth(authKey, authSecret)
                    .field("id", id)
                    .field("transactionId", transactionId)
                    .asString();
            if (response != null && response.getStatus() >= 200 && response.getStatus() <= 299) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return false;
        }

    }

    public SmsSearchResult searchSms(String id,
            String transactionId,
            String msisdn,
            String fromDate,
            String toDate,
            String status,
            int offset,
            int limit) {
        try {

            HttpResponse<SmsSearchResult> response = Unirest.get(buildURL("sms/search"))
                    .basicAuth(authKey, authSecret)
                    .queryString("id", id)
                    .queryString("transactionId", transactionId)
                    .queryString("msisdn", msisdn)
                    .queryString("fromDate", fromDate)
                    .queryString("toDate", toDate)
                    .queryString("status", status)
                    .queryString("offset", offset)
                    .queryString("limit", limit)
                    .asObject(SmsSearchResult.class);

            return response.getBody();

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    ///////
    //sms received
    //////
    public SmsReceivedSearchResult searchSmsReceived(String from,
            String simIdRef,
            String fromDate,
            String toDate,
            int offset,
            int limit) {
        try {

            HttpResponse<SmsReceivedSearchResult> response = Unirest.get(buildURL("sms/received/search"))
                    .basicAuth(authKey, authSecret)
                    .queryString("from", from)
                    .queryString("simIdRef", simIdRef)
                    .queryString("fromDate", fromDate)
                    .queryString("toDate", toDate)
                    .queryString("offset", offset)
                    .queryString("limit", limit)
                    .asObject(SmsReceivedSearchResult.class);

            return response.getBody();

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public List<SmsReceivedSimResult> getSimForReceiveSmsList() {
        try {

            HttpResponse<String> response = Unirest.get(buildURL("sms/received/sim/list"))
                    .basicAuth(authKey, authSecret)
                    .asString();
            if (response != null) {
                TypeReference tr = new TypeReference<List<SmsReceivedSimResult>>() {};
                return new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.getBody(), tr);
            }
            return null;
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    ///////
    //user requests
    //////
    public User getUser() {
        try {
            HttpResponse<User> response = Unirest.get(buildURL("user"))
                    .basicAuth(authKey, authSecret)
                    .asObject(User.class);
            return response.getBody();
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    private String buildURL(String resource) {
        return DEFAULT_ENDPOINT + resource;
    }

}
