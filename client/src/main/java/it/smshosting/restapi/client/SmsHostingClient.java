/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.smshosting.restapi.client.entities.Alias;
import it.smshosting.restapi.client.entities.Estimate;
import it.smshosting.restapi.client.model.AliasResponse;
import it.smshosting.restapi.client.model.AliasListResponse;
import it.smshosting.restapi.client.entities.Contact;
import it.smshosting.restapi.client.entities.ContactSearchResult;
import it.smshosting.restapi.client.entities.Email;
import it.smshosting.restapi.client.entities.EmailCampaign;
import it.smshosting.restapi.client.entities.EmailCampaignsResult;
import it.smshosting.restapi.client.entities.EmailResult;
import it.smshosting.restapi.client.entities.EmailSender;
import it.smshosting.restapi.client.entities.EmailTemplate;
import it.smshosting.restapi.client.entities.EmailTemplatesResult;
import it.smshosting.restapi.client.model.EstimateResponse;
import it.smshosting.restapi.client.model.GenericResponse;
import it.smshosting.restapi.client.entities.Group;
import it.smshosting.restapi.client.entities.SmsBulkResult;
import it.smshosting.restapi.client.entities.SmsInfo;
import it.smshosting.restapi.client.entities.SmsResult;
import it.smshosting.restapi.client.model.GroupListResponse;
import it.smshosting.restapi.client.model.SendSmsResponse;
import it.smshosting.restapi.client.entities.SmsReceivedResult;
import it.smshosting.restapi.client.entities.SmsSearchResult;
import it.smshosting.restapi.client.model.ContactResponse;
import it.smshosting.restapi.client.model.ContactSearchResponse;
import it.smshosting.restapi.client.model.GroupResponse;
import it.smshosting.restapi.client.model.SmsReceivedSearchResponse;
import it.smshosting.restapi.client.model.SmsReceivedSearchResponseList;
import it.smshosting.restapi.client.entities.SmsReceivedSimResponse;
import it.smshosting.restapi.client.model.SmsSearchResponse;
import it.smshosting.restapi.client.entities.User;
import it.smshosting.restapi.client.model.CancelSmsResponse;
import it.smshosting.restapi.client.model.EmailCampaignResponse;
import it.smshosting.restapi.client.model.EmailCampaignsListResponse;
import it.smshosting.restapi.client.model.EmailResponse;
import it.smshosting.restapi.client.model.EmailSenderResponse;
import it.smshosting.restapi.client.model.EmailTemplateResponse;
import it.smshosting.restapi.client.model.EmailTemplatesListResponse;
import it.smshosting.restapi.client.model.SendEmailCampaignResponse;
import it.smshosting.restapi.client.model.SendSmsBulkResponse;
import it.smshosting.restapi.client.model.UserResponse;
import it.smshosting.restapi.client.utilities.Errors;
import it.smshosting.restapi.client.utilities.ValidationUtils;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Client Rest API Smshosting.it
 * More informations at https://help.smshosting.it/it/sms-rest-api
 * 
 * @author Smshosting
 * @author www.smshosting.it
 */
public class SmsHostingClient {

    private static final Logger log = Logger.getLogger(SmsHostingClient.class.getName());        
    public static final String DEFAULT_ENDPOINT = "https://api.smshosting.it/rest/api/";
    private SmsHostingClient clientSmsh;
    private OkHttpClient clientOkHttp;
    private String SMSH_API_KEY;
    private String SMSH_SECRET_KEY;
    private String baseUrl = DEFAULT_ENDPOINT;      
            
    /**
     * Create an instance of the client.
     * 
     * @author Smshosting
     * 
     * @param authKey  Smshosting Api key
     * @param authSecret  Smshosting Secret key
     */

    public SmsHostingClient(String authKey, String authSecret) {   
        this(authKey, authSecret, null);        
    }
    
    /**
     * Create an instance of the client.
     * 
     * @author Smshosting
     * 
     * @param authKey  Smshosting Api key
     * @param authSecret  Smshosting Secret key
     * @param baseUrl Base URL
     */

    public SmsHostingClient(String authKey, String authSecret, String baseUrl) {   
        
        clientOkHttp = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .build();                         
        
        setSMSH_API_KEY(authKey);
        setSMSH_SECRET_KEY(authSecret);   
        if (baseUrl != null) {
            setBaseUrl(baseUrl);
        }
    }    
    
    /**
     * Create and retrieve a new Alias
     * 
     * @author Smshosting
     * 
     * @param alias         String that identify the alias. Required.
     * @param businessname  Required.
     * @param address       Required.
     * @param city          Required.
     * @param postcode      Required.
     * @param province      Required.
     * @param country       Required.   
     * @param vatnumber     Required.
     * @param email         Required.
     * @param phone         Required.
     * @param taxcode       Required.     
     * @param pec           Required.
     * @return AliasResponse object containing the creted alias along with status code and error messages.
     */
     public AliasResponse createAlias(String alias,
            String businessname,
            String address,
            String city,
            String postcode,
            String province,
            String country,
            String vatnumber,
            String email,
            String phone,
            String taxcode,            
            String pec) {
        try {
            
            AliasResponse aliasResponse = new AliasResponse();
            
            String error = ValidationUtils.validateCreateAlias(alias, businessname, address, city, postcode, province, country, vatnumber, email, phone, taxcode, pec, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                aliasResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                aliasResponse.setMessage(error);
                return aliasResponse;
            }
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);          
                              
            if (alias != null)           requestBody.add("alias",alias);
            if (businessname != null)    requestBody.add("businessname",businessname);
            if (address != null)         requestBody.add("address",address);
            if (city != null)            requestBody.add("city",city);
            if (postcode != null)        requestBody.add("postcode",postcode);
            if (province != null)        requestBody.add("province",province);
            if (country != null)         requestBody.add("country",country);
            if (vatnumber != null)       requestBody.add("vatnumber",vatnumber);
            if (email != null)           requestBody.add("email",email);
            if (phone != null)           requestBody.add("phone",phone);
            if (taxcode != null)         requestBody.add("taxcode",taxcode);            
            if (pec != null)             requestBody.add("pec",pec);                     

            Request request = new Request.Builder()
                    .url(buildURL("alias"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            aliasResponse.setStatusCode(response.code());
            ObjectMapper objectMapper = new ObjectMapper();
            
            if (response.code() >= 200 && response.code() <= 299) {
                Alias newalias = new Alias();
                newalias = objectMapper.readValue(responseBody.string(), Alias.class);
                aliasResponse.setAlias(newalias);
            } else {
                aliasResponse = objectMapper.readValue(responseBody.string(), AliasResponse.class);
            }
            return aliasResponse;           

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Get a list of the current registered aliases
     * 
     * @author Smshosting
     * 
     * @return AliasListResponse object containing the list of registered alias along with status code and, eventually error messages
     */ 
    public AliasListResponse getAlias() {
        try {
            AliasListResponse aliasListResponse = new AliasListResponse();

            String error = ValidationUtils.validateGetAlias(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                aliasListResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                aliasListResponse.setMessage(error);
                return aliasListResponse;
            }

            Request request = new Request.Builder()
                    .url(buildURL("alias/list"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            aliasListResponse.setStatusCode(response.code());

            if (response.code() >= 200 && response.code() <= 299) {
                TypeReference tr = new TypeReference<List<Alias>>() {
                };
                aliasListResponse.setAliasList((List<Alias>) new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.body().string(), tr));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                aliasListResponse = objectMapper.readValue(responseBody.string(), AliasListResponse.class);
            }

            return aliasListResponse;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }     
    
    /**
     * Delete the specified Alias
     * 
     * @author Smshosting
     * 
     * @param id            ID of the alias. Required.
     * @return GenericResponse Object containing status code and error messages
     */
     public GenericResponse deleteAlias(
            String id) {
        try {
            GenericResponse result = new GenericResponse();

            String error = ValidationUtils.validateDeleteAlias(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                result.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                result.setMessage(error);
                return result;
            }

            Request request = new Request.Builder()
                    .url(buildURL("alias/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .delete()
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            result.setStatusCode(response.code());

            if (response.code() < 200 || response.code() > 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.readValue(responseBody.string(), GenericResponse.class);
            }
            
            return result;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }     

    /**
     * Send an SMS
     * 
     * @author Smshosting
     * 
     * 
     * @param from           Sender of the SMS
     * @param to             Recipient. The value must be a valid phone number including the international prefix, or a comma-separated list of numbers. One between this paramter and "group" Parameter must be set
     * @param group          Recipient Group ID, or comma separated list of group IDs. One between this paramter and "to" Parameter must be set
     * @param text           Text of the SMS. Required.
     * @param sendDate       Optional date for the scheduled SMS. The format must be yyyy-MM-ddTHH:mm:ssZ
     * @param transactionId  Optional custom transaction-ID. Max 60 characters.
     * @param sandbox        Set to true to test the function without sending the SMS
     * @param statusCallback Optional URL to be notified with the SMS Delivery   
     * @param encoding       Encoding to be used. Possible values: 7BIT, UCS2 or AUTO
     * @return               SendSmsResponse object with the result of the sending.
     */
    public SendSmsResponse sendSms(String from,
            String to,
            String group,
            String text,
            String sendDate,
            String transactionId,
            Boolean sandbox,
            String statusCallback,
            String encoding) {
        try {
            
            SendSmsResponse sendSmsResponse = new SendSmsResponse();
            
            String error = ValidationUtils.validateSendSms(from, to, group, text, sendDate, transactionId, sandbox, statusCallback, encoding, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                sendSmsResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                sendSmsResponse.setMessage(error);
                return sendSmsResponse;
            }              
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);                       

            if(from != null)            requestBody.add("from",from);
            if(to != null)              requestBody.add("to",to);
            if(group != null)           requestBody.add("group",group);
            if(text != null)            requestBody.add("text",text);
            if(sendDate != null)        requestBody.add("date",sendDate);
            if(transactionId != null)   requestBody.add("transactionId",transactionId);
            if(sandbox != null)         requestBody.add("sandbox",sandbox.toString());
            if(statusCallback != null)  requestBody.add("statusCallback",statusCallback);
            if(encoding != null)        requestBody.add("encoding",encoding);                       

            Request request = new Request.Builder()
                    .url(buildURL("sms/send"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();

            if (response.code() >= 200 && response.code() <= 299) {
                sendSmsResponse.setStatusCode(response.code());
                SmsResult smsResult = new SmsResult();
                smsResult = objectMapper.readValue(responseBody.string(), SmsResult.class);
                sendSmsResponse.setSmsResult(smsResult);
            } else {
                sendSmsResponse = objectMapper.readValue(responseBody.string(), SendSmsResponse.class);
            }
            
            return sendSmsResponse;


        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Async send. Use this function to send SMS to 1000 or more recipients. 
     * 
     * @author Smshosting
     * 
     * @param from           Sender of the SMS
     * @param to             Recipient. The value must be a valid phone number including the international prefix, or a comma-separated list of numbers. One between this paramter and "group" Parameter must be set
     * @param group          Recipient Group ID, or comma separated list of group IDs. One between this paramter and "to" Parameter must be set
     * @param text           Text of the SMS. Required.
     * @param sendDate       Optional date for the scheduled SMS. The format must be yyyy-MM-ddTHH:mm:ssZ
     * @param transactionId  Optional custom transaction-ID. Max 60 characters.
     * @param sandbox        Set to true to test the function without sending the SMS
     * @param statusCallback Optional URL to be notified with the SMS Delivery   
     * @param transactionCallback Optional URL to be notified with the general status of the sending
     * @param encoding       Encoding to be used. Possible values: 7BIT, UCS2 or AUTO
     * @return               SendSmsResponse object with the result of the sending.
     */
    public SendSmsBulkResponse sendSmsBulk(String from,
            String to,
            String group,
            String text,
            String sendDate,
            String transactionId,
            Boolean sandbox,
            String statusCallback,
            String transactionCallback,
            String encoding) {
        try {                         
            
            SendSmsBulkResponse sendSmsResponse = new SendSmsBulkResponse();
            
            String error = ValidationUtils.validateSendSms(from, to, group, text, sendDate, transactionId, sandbox, statusCallback, encoding, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                sendSmsResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                sendSmsResponse.setMessage(error);
                return sendSmsResponse;
            }             
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);             
            
            if(from != null)                requestBody.add("from",from);
            if(to != null)                  requestBody.add("to",to);
            if(group != null)               requestBody.add("group",group);  
            if(text != null)                requestBody.add("text",text);
            if(sendDate != null)            requestBody.add("date",sendDate);
            if(transactionId != null)       requestBody.add("transactionId",transactionId);  
            if(sandbox != null)             requestBody.add("sandbox",sandbox.toString());
            if(statusCallback != null)      requestBody.add("statusCallback",statusCallback);
            if(transactionCallback != null) requestBody.add("transactionCallback",transactionCallback);            
            if(encoding != null)            requestBody.add("encoding",encoding);                       
            
            Request request = new Request.Builder()
                    .url(buildURL("sms/sendbulk"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();

            if (response.code() >= 200 && response.code() <= 299) {
                sendSmsResponse.setStatusCode(response.code());
                SmsBulkResult smsResult = new SmsBulkResult();
                smsResult = objectMapper.readValue(responseBody.string(), SmsBulkResult.class);
                sendSmsResponse.setSmsBulkResult(smsResult);
            } else {
                sendSmsResponse = objectMapper.readValue(responseBody.string(), SendSmsBulkResponse.class);
            }
            
            return sendSmsResponse;           
            

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Calculate the cost of an SMS campaign
     * 
     * @author Smshosting
     * 
     * 
     * @param from           Sender of the SMS
     * @param to             Recipient. The value must be a valid phone number including the international prefix, or a comma-separated list of numbers. One between this paramter and "group" Parameter must be set
     * @param group          Recipient Group ID, or comma separated list of group IDs. One between this paramter and "to" Parameter must be set
     * @param text           Text of the SMS. Required.
     * @param encoding       Encoding to be used. Possible values: 7BIT, UCS2 or AUTO
     * @return               EstimateResponse object with the estimate.
     */
    public EstimateResponse estimateSendSms(String from,
            String to,
            String group,
            String text,
            String encoding) {
        try {
            
            EstimateResponse estimateResponse = new EstimateResponse();
            
            String error = ValidationUtils.validateEstimateSendSms(from, to, group, text, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                estimateResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                estimateResponse.setMessage(error);
                return estimateResponse;
            }              
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);               

            if(from != null)    requestBody.add("from",from);
            if(to != null)      requestBody.add("to",to);
            if(group != null)   requestBody.add("group",group);
            if(text != null)    requestBody.add("text",text);                                   
            
            Request request = new Request.Builder()
                    .url(buildURL("sms/estimate"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();

            ResponseBody responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
                estimateResponse.setStatusCode(response.code());
                Estimate estimateResult = new Estimate();
                estimateResult = objectMapper.readValue(responseBody.string(), Estimate.class);
                estimateResponse.setEstimate(estimateResult);
            } else {
                estimateResponse = objectMapper.readValue(responseBody.string(), EstimateResponse.class);
            }

            return estimateResponse;            

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Cancel a pending SMS
     * 
     * @author Smshosting
     * 
     * @param id            ID of the SMS. One value between this and "transactionId" must be set.
     * @param transactionId TransactionID of the SMS. One value between this and "id" must be set.
     * @return               GenericResponse with the result of the call.
     */
    public CancelSmsResponse cancelSms(
            String id, 
            String transactionId) {
        try {
            
            CancelSmsResponse cancelSmsResponse = new CancelSmsResponse();
                     
            String error = ValidationUtils.validateCancelSms(id, transactionId, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                cancelSmsResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                cancelSmsResponse.setMessage(error);
                return cancelSmsResponse;
            }                   
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);             
           
            if(id != null)              requestBody.add("id",id);   
            if(transactionId != null)   requestBody.add("transactionId",transactionId);                         
            
            Request request = new Request.Builder()
                    .url(buildURL("sms/cancel"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            ObjectMapper objectMapper = new ObjectMapper();

            if (response.code() >= 200 && response.code() <= 299) {
                cancelSmsResponse.setStatusCode(response.code());
                TypeReference tr = new TypeReference<List<SmsInfo>>() {
                };
                cancelSmsResponse.setSmsList((List<SmsInfo>) new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.body().string(), tr));
            } else {
                cancelSmsResponse = objectMapper.readValue(responseBody.string(), CancelSmsResponse.class);
            }

            return cancelSmsResponse;    
            

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Search and get details of the SMS.
     * One parameters between "id","transactionId","msisdn","fromDate","status" must be set
     * 
     * @author Smshosting
     * 
     * @param id            ID or comma-separated list of IDs.
     * @param transactionId TransactionID associated with the sending
     * @param msisdn        Recipient of the SMS
     * @param fromDate      Earliest sending date of the SMS
     * @param toDate        Latter sending date of the SMS
     * @param status        Status of the sms.
     * @param offset        Offset for pagination
     * @param limit         Limit for pagination
     * @return               SmsSearchResponse with the result of the search.
     */
    public SmsSearchResponse searchSms(String id,
            String transactionId,
            String msisdn,
            String fromDate,
            String toDate,
            String status,
            Integer offset,
            Integer limit) {
        try {

            SmsSearchResponse smsSearchResponse = new SmsSearchResponse();
            
            String error = ValidationUtils.validateSearchSms(id, transactionId, msisdn, fromDate, toDate, status, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                smsSearchResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                smsSearchResponse.setMessage(error);
                return smsSearchResponse;
            }             
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("sms/search")).newBuilder();      
            
            if(id != null)              urlBuilder.addQueryParameter("id",id);
            if(transactionId != null)   urlBuilder.addQueryParameter("transactionId",transactionId);
            if(msisdn != null)          urlBuilder.addQueryParameter("msisdn",msisdn); 
            if(fromDate != null)        urlBuilder.addQueryParameter("fromDate",fromDate);
            if(toDate != null)          urlBuilder.addQueryParameter("toDate",toDate);
            if(status != null)          urlBuilder.addQueryParameter("status",status);
            if(offset != null)          urlBuilder.addQueryParameter("offset",String.valueOf(offset));
            if(limit != null)           urlBuilder.addQueryParameter("limit",String.valueOf(limit));                        
            
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            smsSearchResponse.setStatusCode(response.code());
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
                SmsSearchResult smsSearchResult = new SmsSearchResult();
                smsSearchResult = objectMapper.readValue(responseBody.string(), SmsSearchResult.class);
                smsSearchResponse.setSmsSearchResult(smsSearchResult);
            } else {
                smsSearchResponse = objectMapper.readValue(responseBody.string(), SmsSearchResponse.class);
            }

            return smsSearchResponse;           

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Retrieve a list of SMS received from the selected SIM
     * One parameter between "from","simIdRef","fromDate","toDate" must be set.
     * 
     * @author Smshosting
     * 
     * @param from          Sender or comma-separated list of Senders of the SMS received
     * @param simIdRef      ID of the Forward Service
     * @param fromDate      Earliest receiving date of the SMS
     * @param toDate        Latter receiving date of the SMS
     * @param offset        Offset for pagination.
     * @param limit         Limit for pagination.
     * @return              SmsReceivedSearchResponse with the result of the search.
     */
    public SmsReceivedSearchResponse searchSmsReceived(String from,
            String simIdRef,
            String fromDate,
            String toDate,
            Integer offset,
            Integer limit) {
        try {
            
            SmsReceivedSearchResponse smsReceivedSearchResponse = new SmsReceivedSearchResponse();
            
            String error = ValidationUtils.validateSearchSmsReceived(from, simIdRef, fromDate, toDate, offset, limit, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                smsReceivedSearchResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                smsReceivedSearchResponse.setMessage(error);
                return smsReceivedSearchResponse;
            }             
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("sms/received/search")).newBuilder();

            if(from != null)        urlBuilder.addQueryParameter("from",from);
            if(simIdRef != null)    urlBuilder.addQueryParameter("simIdRef",simIdRef);    
            if(fromDate != null)    urlBuilder.addQueryParameter("fromDate",fromDate);
            if(toDate != null)      urlBuilder.addQueryParameter("toDate",toDate);
            if(offset != null)      urlBuilder.addQueryParameter("offset",String.valueOf(offset));
            if(limit != null)       urlBuilder.addQueryParameter("limit",String.valueOf(limit));                        
            
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            smsReceivedSearchResponse.setStatusCode(response.code());
            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
                SmsReceivedResult smsReceivedResult = new SmsReceivedResult();
                smsReceivedResult = objectMapper.readValue(responseBody.string(), SmsReceivedResult.class);
                smsReceivedSearchResponse.setSmsReceivedResult(smsReceivedResult);
            } else {
                smsReceivedSearchResponse = objectMapper.readValue(responseBody.string(), SmsReceivedSearchResponse.class);
            }

            return smsReceivedSearchResponse;                       

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Retrieve the list of active forward services.
     * 
     * @author Smshosting
     * 
     * @return              SmsReceivedSearchResponseList with the result of the search.
     */
    public SmsReceivedSearchResponseList getSimForReceiveSmsList() {
        try {

            SmsReceivedSearchResponseList smsReceivedSearchResponseList = new SmsReceivedSearchResponseList();
            
            String error = ValidationUtils.validateGetSimForReceiveSmsList(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                smsReceivedSearchResponseList.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                smsReceivedSearchResponseList.setMessage(error);
                return smsReceivedSearchResponseList;
            }             
            
            Request request = new Request.Builder()
                    .url(buildURL("sms/received/sim/list"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            smsReceivedSearchResponseList.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                TypeReference tr = new TypeReference<List<SmsReceivedSimResponse>>() {
                };
                smsReceivedSearchResponseList.setSmsReceivedSimResponseList((List<SmsReceivedSimResponse>) new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.body().string(), tr));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                smsReceivedSearchResponseList = objectMapper.readValue(responseBody.string(), SmsReceivedSearchResponseList.class);
            }

            return smsReceivedSearchResponseList;          
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Retrieve a list of the phonebook groups.
     * 
     * @author Smshosting
     * 
     * @return              GroupListResponse with the list of groups.
     */
    public GroupListResponse getGroupList() {
        try {

            GroupListResponse groupListResponse = new GroupListResponse();
            
            String error = ValidationUtils.validateGetGroupList(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                groupListResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                groupListResponse.setMessage(error);
                return groupListResponse;
            }             
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/group/list"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            groupListResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                TypeReference tr = new TypeReference<List<Group>>() {
                };
                groupListResponse.setGroupList((List<Group>) new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.body().string(), tr));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                groupListResponse = objectMapper.readValue(responseBody.string(), GroupListResponse.class);
            }

            return groupListResponse;            

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Retrieve the group with the specified ID
     * 
     * @author Smshosting
     * 
     * @param id            Group ID. Required. 
     * @return              GroupResponse containing the required group detail.
     */
    public GroupResponse getGroup(String id) {
        try {
            
            GroupResponse groupResponse = new GroupResponse();
            
            String error = ValidationUtils.validateGetGroup(id,getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                groupResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                groupResponse.setMessage(error);
                return groupResponse;
            }             
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/group/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            groupResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                Group groupDetail = new Group();
                groupDetail = objectMapper.readValue(responseBody.string(), Group.class);
                groupResponse.setGroup(groupDetail);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                groupResponse = objectMapper.readValue(responseBody.string(), GroupResponse.class);
            }

            return groupResponse;            
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
    
    /**
     * Retrieve the contacts of a specified group
     * 
     * @author Smshosting
     * 
     * @param id            Group ID. Required.   
     * @param offset        Offset for pagination
     * @param limit         Limit for pagination
     * @return              ContactSearchResponse containing the list of contacts.
     */
    public ContactSearchResponse getGroupContacts(
            String id,
            Integer offset,
            Integer limit) {
        try {
            
            ContactSearchResponse contactSearchResponse = new ContactSearchResponse();
            
            String error = ValidationUtils.validateGetGroup(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                contactSearchResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                contactSearchResponse.setMessage(error);
                return contactSearchResponse;
            }             
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("phonebook/group/"+id+"/contacts")).newBuilder();
            
            if(offset != null)      urlBuilder.addQueryParameter("offset",String.valueOf(offset));
            if(limit != null)       urlBuilder.addQueryParameter("offset",String.valueOf(limit));                        
            
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            contactSearchResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                ContactSearchResult searchResult = new ContactSearchResult();
                searchResult = objectMapper.readValue(responseBody.string(), ContactSearchResult.class);
                contactSearchResponse.setContactSearchResult(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                contactSearchResponse = objectMapper.readValue(responseBody.string(), ContactSearchResponse.class);
            }

            return contactSearchResponse;             
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
    
    /**
     * Create a new group
     * 
     * @author Smshosting
     * 
     * @param name          Name of the new group. Required. 
     * @return              GroupResponse containing the created group.
     */
    public GroupResponse addGroup(
            String name) {
        try {
                    
            GroupResponse groupResponse = new GroupResponse();
            
            String error = ValidationUtils.validateAddGroup(name, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                groupResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                groupResponse.setMessage(error);
                return groupResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);             
           
            if (name != null)    requestBody.add("name",name);                                        
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/group"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();

            groupResponse.setStatusCode(response.code());
            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                Group groupDetail = new Group();
                groupDetail = objectMapper.readValue(responseBody.string(), Group.class);
                groupResponse.setGroup(groupDetail);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                groupResponse = objectMapper.readValue(responseBody.string(), GroupResponse.class);
            }
            return groupResponse;                      

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    /**
     * Update the name of the specified group
     * 
     * @author Smshosting
     * 
     * @param id            Group ID. Required.
     * @param name          New name. Required.
     * @return              GroupResponse containing the created group.
     */
    public GroupResponse updateGroup(
            String id,
            String name) {
        try {
                      
            GroupResponse groupResponse = new GroupResponse();
            
            String error = ValidationUtils.validateUpdateGroup(id, name, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                groupResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                groupResponse.setMessage(error);
                return groupResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);                
            
            if (name != null)    requestBody.add("name",name);                        
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/group/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .put(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();

            groupResponse.setStatusCode(response.code());
            if (response.code() < 200 || response.code() > 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                groupResponse = objectMapper.readValue(responseBody.string(), GroupResponse.class);
            }
            return groupResponse;                                           

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Delete the specified group.
     * 
     * @author Smshosting
     * 
     * @param id                Group ID. Required.
     * @param deleteContacts    Specify if the contacts of the group must be deleted or not. Default value False
     * @return                  GenericResponse containing the result of the call.
     */
    public GenericResponse deleteGroup(
            String id,
            boolean deleteContacts) {
        try {
            
            GenericResponse result = new GenericResponse();

            String error = ValidationUtils.validateDeleteGroup(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                result.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                result.setMessage(error);
                return result;
            }             
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/group/" + id + "?delete_contacts=" + deleteContacts))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .delete()
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            result.setStatusCode(response.code());
            
            if (response.code() < 200 || response.code() > 299) {
                JsonNode jsonNode = new ObjectMapper().readTree(response.body().string());
                String errorMsg = jsonNode.get("errorMsg").asText();
                result.setMessage(errorMsg);
            }
            
            return result;            

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Search contacts. 
     * If no search parameter is set the call will return a paginated list of all contacts.
     * 
     * @author Smshosting
     * 
     * @param name          Name or surname of the contact
     * @param msisdn        Phone number of the contact.
     * @param fieldKey      Custom field Key.
     * @param fieldValue    Custom field value.
     * @param email         Email of the contact
     * @param offset        Offset for pagination
     * @param limit         Limit for pagination
     * @return              ContactSearchResponse containing the list of contacts that matches the search parameters.
     */
    public ContactSearchResponse searchContacts(
            String name,
            String msisdn,
            String fieldKey,
            String fieldValue,
            String email,
            Integer offset, 
            Integer limit) {
        try {
                      
            ContactSearchResponse contactSearchResponse = new ContactSearchResponse();
            
            String error = ValidationUtils.validateSearchContacts(msisdn, fieldKey, fieldValue, email, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                contactSearchResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                contactSearchResponse.setMessage(error);
                return contactSearchResponse;
            }             
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("phonebook/contact/search")).newBuilder();

            if (name != null)       urlBuilder.addQueryParameter("name",name);
            if (msisdn != null)     urlBuilder.addQueryParameter("msisdn",msisdn);
            if ((fieldKey != null) && (fieldValue != null)){
                urlBuilder.addQueryParameter("fieldKey",fieldKey);
                urlBuilder.addQueryParameter("fieldValue",fieldValue);
            }
            if (email != null)      urlBuilder.addQueryParameter("email",email);
            if (offset != null)     urlBuilder.addQueryParameter("offset",String.valueOf(offset));
            if (limit != null)      urlBuilder.addQueryParameter("limit",String.valueOf(limit));                                            

            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            contactSearchResponse.setStatusCode(response.code());

            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                ContactSearchResult contactSearchResult = new ContactSearchResult();
                contactSearchResult = objectMapper.readValue(responseBody.string(), ContactSearchResult.class);
                contactSearchResponse.setContactSearchResult(contactSearchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                contactSearchResponse = objectMapper.readValue(responseBody.string(), ContactSearchResponse.class);
            }

            return contactSearchResponse;                                           

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Retrieve the specified contact
     * 
     * @author Smshosting
     * 
     * @param id            Contact ID. Required.
     * @return              ContactResponse containing requested contact.
     */
    public ContactResponse getContact(
            String id) {
        try {
            ContactResponse contactResponse = new ContactResponse();
            
            String error = ValidationUtils.validateGetContact(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                contactResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                contactResponse.setMessage(error);
                return contactResponse;
            }             
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/contact/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            contactResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                Contact searchResult = new Contact();
                searchResult = objectMapper.readValue(responseBody.string(), Contact.class);
                contactResponse.setContact(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                contactResponse = objectMapper.readValue(responseBody.string(), ContactResponse.class);
            }

            return contactResponse;       

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
        
    /**
     * Create a new contact
     * 
     * @author Smshosting
     * 
     * @param msisdn                Phone number of the contact. 
     * @param name                  Name of the contact. Required
     * @param lastname              Last Name of the contact.
     * @param email                 Email of the contact.
     * @param groupsId              ID of groups in which the contact must be included.
     * @param customFields          Map of Key/Value of custom fields.
     * @param customFieldUniqueKey  Field to use as unique value to identify a duplicate contact. Possible values are msisdn, email, name, lastname or any of the custom fields key. Default value: msisdn
     * @return                      ContactResponse containing the created contact.
     */
    public ContactResponse addContact(String msisdn,
            String name,
            String lastname,
            String email,
            String groupsId,
            Map<String,String> customFields,
            String customFieldUniqueKey) {
        try {
                        
            ContactResponse contactResponse = new ContactResponse();
            
            String error = ValidationUtils.validateAddContact(name, groupsId, customFieldUniqueKey, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                contactResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                contactResponse.setMessage(error);
                return contactResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);                            
            
            if(msisdn != null)                  requestBody.add("msisdn",msisdn);
            if(name != null)                    requestBody.add("name",name);  
            if(lastname != null)                requestBody.add("lastname",lastname);  
            if(email != null)                   requestBody.add("email",email);  
            if(groupsId != null)                requestBody.add("groupsId",groupsId);  
            if(customFieldUniqueKey != null)    requestBody.add("customFieldUniqueKey",customFieldUniqueKey);  
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    requestBody.add(key, customFields.get(key));
                }
            }                                     
            
            Request request = new Request.Builder()
                    .url(buildURL("phonebook/contact"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
            Contact contactResult = new Contact();
            contactResponse.setStatusCode(response.code());
            contactResult = objectMapper.readValue(responseBody.string(), Contact.class);
            contactResponse.setContact(contactResult);
            } else {
                contactResponse = objectMapper.readValue(responseBody.string(), ContactResponse.class);                
            }
            return contactResponse;                                  

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Update the specified contact
     * 
     * @author Smshosting
     * 
     * @param id            ID of the contact to update. Required.
     * @param msisdn        New phone number.      
     * @param name          New name.
     * @param lastname      New Last Name.
     * @param email         New email.
     * @param groupsId      New groups ids in which the contact must be included.
     * @param customFields  New CustomField.
     * @return              ContactResponse containing the updated contact.
     */
    public ContactResponse updateContact(String id,
            String msisdn,
            String name,
            String lastname,
            String email,
            String groupsId,
            Map<String,String> customFields) {
        try {
                
            ContactResponse contactResponse = new ContactResponse();
            
            String error = ValidationUtils.validateUpdateContact(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                contactResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                contactResponse.setMessage(error);
                return contactResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);                            
                
            if(name != null)        requestBody.add("name",name);
            if(lastname != null)    requestBody.add("lastname",lastname);  
            if(email != null)       requestBody.add("email",email);  
            if(msisdn != null)      requestBody.add("msisdn",msisdn);  
            if(groupsId != null)    requestBody.add("groupsId",groupsId);  
            
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    requestBody.add(key, customFields.get(key));
                }
            }                                     

            Request request = new Request.Builder()
                    .url(buildURL("phonebook/contact/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .put(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                ObjectMapper objectMapper = new ObjectMapper();
                Contact searchResult = new Contact();
                searchResult = objectMapper.readValue(responseBody.string(), Contact.class);
                contactResponse.setContact(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                contactResponse = objectMapper.readValue(responseBody.string(), ContactResponse.class);
            }
            return contactResponse;                                                   

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Delete a contact.
     * 
     * @author Smshosting
     * 
     * @param id            Contact ID. Required.
     * @return              GenericResponse containing the result of the api call.
     */
    public GenericResponse deleteContact(
            String id) {
        try {
            
            GenericResponse result = new GenericResponse();

            String error = ValidationUtils.validateDeleteContact(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                result.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                result.setMessage(error);
                return result;
            }            

            Request request = new Request.Builder()
                    .url(buildURL("phonebook/contact/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .delete()
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            result.setStatusCode(response.code());

            if (response.code() < 200 || response.code() > 299) {
                JsonNode jsonNode = new ObjectMapper().readTree(response.body().string());
                String errorMsg = jsonNode.get("errorMsg").asText();
                result.setMessage(errorMsg);
            }

            return result;       

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    /**
     * Get enabled email senders. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @return              EmailSenderResponse containing the senders list.
     */
    public EmailSenderResponse getEmailSenderList() {
        try {
            
            EmailSenderResponse emailSenderResponse = new EmailSenderResponse();
            
            String error = ValidationUtils.validateGetEmailSenderList(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailSenderResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailSenderResponse.setMessage(error);
                return emailSenderResponse;
            }            
            
            Request request = new Request.Builder()
                    .url(buildURL("email/sender/list"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            emailSenderResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailSenderResponse.isError()) {
                TypeReference tr = new TypeReference<List<EmailSender>>() {
                };
                emailSenderResponse.setEmailSenderList((List<EmailSender>) new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.body().string(), tr));
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailSenderResponse = objectMapper.readValue(responseBody.string(), EmailSenderResponse.class);
            }

            return emailSenderResponse;       

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }
    
    /**
     * Get available email templates. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param offset        Offset value for pagination. 
     * @param limit         Limit value for pagination.
     * @return              EmailTemplatesListResponse containing the templates list.
     */
    public EmailTemplatesListResponse getEmailTemplatesList(
            Integer offset, 
            Integer limit) {
        try {
            
            EmailTemplatesListResponse emailTemplatesResponse = new EmailTemplatesListResponse();
            
            String error = ValidationUtils.validateGetEmailTemplatesList(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailTemplatesResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailTemplatesResponse.setMessage(error);
                return emailTemplatesResponse;
            }            
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("email/template/list")).newBuilder();

            if (offset != null)    urlBuilder.addQueryParameter("offset", String.valueOf(offset));
            if (limit != null)     urlBuilder.addQueryParameter("limit", String.valueOf(limit));                              

            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            emailTemplatesResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailTemplatesResponse.isError()) {
                ObjectMapper objectMapper = new ObjectMapper();
                EmailTemplatesResult emailTemplatesResult = new EmailTemplatesResult();
                emailTemplatesResult = objectMapper.readValue(responseBody.string(), EmailTemplatesResult.class);
                emailTemplatesResponse.setEmailTemplatesResult(emailTemplatesResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailTemplatesResponse = objectMapper.readValue(responseBody.string(), EmailTemplatesListResponse.class);
            }

            return emailTemplatesResponse;         

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }    
    
    /**
     * Get template details. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param id            Template ID
     * @return              EmailTemplateResponse containing the template details.
     */
    public EmailTemplateResponse getEmailTemplate(
            String id) {
        try {
            
            EmailTemplateResponse emailTemplateResponse = new EmailTemplateResponse();
            
            String error = ValidationUtils.validateGetEmailTemplate(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailTemplateResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailTemplateResponse.setMessage(error);
                return emailTemplateResponse;
            }            
            
            Request request = new Request.Builder()
                    .url(buildURL("email/template/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            emailTemplateResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailTemplateResponse.isError()) {
                ObjectMapper objectMapper = new ObjectMapper();
                EmailTemplate searchResult = new EmailTemplate();
                searchResult = objectMapper.readValue(responseBody.string(), EmailTemplate.class);
                emailTemplateResponse.setEmailTemplate(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailTemplateResponse = objectMapper.readValue(responseBody.string(), EmailTemplateResponse.class);
            }

            return emailTemplateResponse;          

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }   
    
    /**
     * Get email campaigns. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param offset        Offset value for pagination. 
     * @param limit         Limit value for pagination.
     * @return              EmailCampaignsListResponse containing the campaigns list.
     */
    public EmailCampaignsListResponse getEmailCampaignsList(
            Integer offset, 
            Integer limit) {
        try {
            
            EmailCampaignsListResponse emailCampaignsListResponse = new EmailCampaignsListResponse();
            
            String error = ValidationUtils.validateGetEmailCampaignsList(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailCampaignsListResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailCampaignsListResponse.setMessage(error);
                return emailCampaignsListResponse;
            }             
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(buildURL("email/campaign/list")).newBuilder();

            if (offset != null)    urlBuilder.addQueryParameter("offset", String.valueOf(offset));
            if (limit != null)     urlBuilder.addQueryParameter("limit", String.valueOf(limit));                                         

            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            emailCampaignsListResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailCampaignsListResponse.isError()) {
                ObjectMapper objectMapper = new ObjectMapper();
                EmailCampaignsResult emailCampaignsResult = new EmailCampaignsResult();
                emailCampaignsResult = objectMapper.readValue(responseBody.string(), EmailCampaignsResult.class);
                emailCampaignsListResponse.setEmailCampaignsResult(emailCampaignsResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailCampaignsListResponse = objectMapper.readValue(responseBody.string(), EmailCampaignsListResponse.class);
            }

            return emailCampaignsListResponse;           

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }     
    
    /**
     * Get Campaign details. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param id            Campaign ID
     * @return              EmailCampaignResponse containing the campaign details.
     */
    public EmailCampaignResponse getEmailCampaign(
            String id) {
        try {
            
            EmailCampaignResponse emailCampaignResponse = new EmailCampaignResponse();
            
            String error = ValidationUtils.validateGetEmailCampaign(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailCampaignResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailCampaignResponse.setMessage(error);
                return emailCampaignResponse;
            }             
            
            Request request = new Request.Builder()
              .url(buildURL("email/campaign/"+id))
              .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
              .get()      
              .build();                        

            Response response = clientOkHttp.newCall(request).execute();
            emailCampaignResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailCampaignResponse.isError()) {
                ObjectMapper objectMapper = new ObjectMapper();
                EmailCampaign searchResult = new EmailCampaign();
                searchResult = objectMapper.readValue(responseBody.string(), EmailCampaign.class);
                emailCampaignResponse.setEmailCampaign(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailCampaignResponse = objectMapper.readValue(responseBody.string(), EmailCampaignResponse.class);
            }

            return emailCampaignResponse;          

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }    
    
    /**
     * Get email details. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param id            Email ID
     * @return              EmailResponse containing the email details.
     */
    public EmailResponse getEmail(
            String id) {
        try {
            
            EmailResponse emailResponse = new EmailResponse();
            
            String error = ValidationUtils.validateGetEmail(id, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                emailResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                emailResponse.setMessage(error);
                return emailResponse;
            }            
            
            Request request = new Request.Builder()
                    .url(buildURL("email/" + id))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();

            Response response = clientOkHttp.newCall(request).execute();
            emailResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (!emailResponse.isError()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Email searchResult = new Email();
                searchResult = objectMapper.readValue(responseBody.string(), Email.class);
                emailResponse.setEmail(searchResult);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                emailResponse = objectMapper.readValue(responseBody.string(), EmailResponse.class);
            }

            return emailResponse;         

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }                
    }    
    
    /**
     * Send an email campaign. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param templateId            template ID. Required.
     * @param from                  email Sender. Must be verified in the web platform. Required.
     * @param fromName              sender Name.
     * @param group                 recipients group ID. Required.
     * @param subject               email Subject. Required.
     * @param subjectPreviewText    email Subject preview text.
     * @param campaignTitle         Campaign title. Required.
     * @param enableOpenTracking    enable / disable the open tracking.
     * @param enableLinkTracking    enable / disable the link tracking.
     * @param date                  Optional date for the schedule sending. The date format must be yyyy-MM-dd'T'HH:mm:ssZ
     * @param sandbox               Optional sandbox parameter to test the function without sending the email.
     * @return                      SendEmailCampaignResponse containing the sending result.
     */
    public SendEmailCampaignResponse sendEmailCampaign(
            String templateId,
            String from,
            String fromName,
            String group,
            String subject,
            String subjectPreviewText,
            String campaignTitle,
            Boolean enableOpenTracking,
            Boolean enableLinkTracking,
            String date,
            Boolean sandbox) {
        try {
            
            SendEmailCampaignResponse sendEmailCampaignResponse = new SendEmailCampaignResponse();
            
            String error = ValidationUtils.validateSendEmailCampaign(templateId, from, group, subject, campaignTitle, date, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                sendEmailCampaignResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                sendEmailCampaignResponse.setMessage(error);
                return sendEmailCampaignResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);             

            if(templateId != null)          requestBody.add("templateId",templateId);
            if(from != null)                requestBody.add("from",from);
            if(fromName != null)            requestBody.add("fromName",fromName);
            if(group != null)               requestBody.add("group",group);
            if(subject != null)             requestBody.add("subject",subject);
            if(subjectPreviewText != null)  requestBody.add("subjectPreviewText",subjectPreviewText);
            if(campaignTitle != null)       requestBody.add("campaignTitle",campaignTitle);
            if(enableOpenTracking != null)  requestBody.add("enableOpenTracking",enableOpenTracking.toString());
            if(enableLinkTracking != null)  requestBody.add("enableLinkTracking",enableLinkTracking.toString());
            if(date != null)                requestBody.add("date",date);
            if(sandbox != null)             requestBody.add("sandbox",sandbox.toString());                                   
            
            Request request = new Request.Builder()
                    .url(buildURL("email/campaign/send"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
                sendEmailCampaignResponse.setStatusCode(response.code());
                EmailResult emailResult = new EmailResult();
                emailResult = objectMapper.readValue(responseBody.string(), EmailResult.class);
                sendEmailCampaignResponse.setEmailResult(emailResult);
            } else {
                sendEmailCampaignResponse = objectMapper.readValue(responseBody.string(), SendEmailCampaignResponse.class);
            }

            return sendEmailCampaignResponse;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }        
    }
    
    /**
     * Send a single email. This is a Premium Plan feature and needs a special permission.
     * 
     * @author Smshosting
     * 
     * @param templateId            template ID. Required.
     * @param from                  email Sender. Must be verified in the web platform. Required.
     * @param fromName              sender Name.
     * @param to                    recipient email or json containing at most 50 emails. Json format: [{'to':'ms.green@fake.it','f_s_nome':'Ms Green'},{'to':'mr.yellow@fake.it','f_s_nome':'John Yellow'}]. Required.
     * @param subject               email Subject. Required.
     * @param subjectPreviewText    email Subject preview text.
     * @param enableOpenTracking    enable / disable the open tracking.
     * @param enableLinkTracking    enable / disable the link tracking.
     * @param date                  Optional date for the schedule sending. The date format must be yyyy-MM-dd'T'HH:mm:ssZ
     * @param sandbox               Optional sandbox parameter to test the function without sending the email.
     * @return                      SendEmailCampaignResponse containing the sending result.
     */
    public SendEmailCampaignResponse sendSingleEmail(
            String templateId,
            String from,
            String fromName,
            String to,
            String subject,
            String subjectPreviewText,
            Boolean enableOpenTracking,
            Boolean enableLinkTracking,
            String date,
            Boolean sandbox) {
        try {
            
            SendEmailCampaignResponse sendEmailCampaignResponse = new SendEmailCampaignResponse();
            
            String error = ValidationUtils.validateSendSingleEmail(templateId, from, to, subject, date, getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                sendEmailCampaignResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                sendEmailCampaignResponse.setMessage(error);
                return sendEmailCampaignResponse;
            }            
            
            FormBody.Builder requestBody = new FormBody.Builder(StandardCharsets.UTF_8);             

            if(templateId != null)          requestBody.add("templateId",templateId);
            if(from != null)                requestBody.add("from",from);
            if(fromName != null)            requestBody.add("fromName",fromName);
            if(to != null)                  requestBody.add("to",to);
            if(subject != null)             requestBody.add("subject",subject);
            if(subjectPreviewText != null)  requestBody.add("subjectPreviewText",subjectPreviewText);
            if(enableOpenTracking != null)  requestBody.add("enableOpenTracking",enableOpenTracking.toString());
            if(enableLinkTracking != null)  requestBody.add("enableLinkTracking",enableLinkTracking.toString());
            if(date != null)                requestBody.add("date",date);
            if(sandbox != null)             requestBody.add("sandbox",sandbox.toString());                                   

            Request request = new Request.Builder()
                    .url(buildURL("email/send"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .post(requestBody.build())
                    .build();
            Response response = clientOkHttp.newCall(request).execute();
            ResponseBody responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            if (response.code() >= 200 && response.code() <= 299) {
                sendEmailCampaignResponse.setStatusCode(response.code());
                EmailResult emailResult = new EmailResult();
                emailResult = objectMapper.readValue(responseBody.string(), EmailResult.class);
                sendEmailCampaignResponse.setEmailResult(emailResult);
            } else {
                sendEmailCampaignResponse = objectMapper.readValue(responseBody.string(), SendEmailCampaignResponse.class);
            }

            return sendEmailCampaignResponse;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }        
    }    
    
    /**
     * Retrieve the Smshosting user informations.
     * 
     * @author Smshosting
     * 
     * @return              UserResponse containing the user details.
     */
    public UserResponse getUser() {
        try {
            
            UserResponse userResponse = new UserResponse();
            
            String error = ValidationUtils.validateGetUser(getSMSH_API_KEY(), getSMSH_SECRET_KEY());
            if (error != null) {
                userResponse.setStatusCode(Errors.STATUS_CODE_BAD_REQUEST);
                userResponse.setMessage(error);
                return userResponse;
            }            
            
            Request request = new Request.Builder()
                    .url(buildURL("user"))
                    .addHeader("Authorization", Credentials.basic(getSMSH_API_KEY(), getSMSH_SECRET_KEY()))
                    .get()
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            Response response = clientOkHttp.newCall(request).execute();
            userResponse.setStatusCode(response.code());
            ResponseBody responseBody = response.body();
            if (response.code() >= 200 && response.code() <= 299) {
                User userDetail = new User();
                userDetail = objectMapper.readValue(responseBody.string(), User.class);
                userResponse.setUser(userDetail);
            } else {
                userResponse = objectMapper.readValue(responseBody.string(), UserResponse.class);
            }

            return userResponse;           
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    private String buildURL(String resource) {
        return getBaseUrl() + resource;
    }

    private String getSMSH_API_KEY() {
        return SMSH_API_KEY;
    }

    private void setSMSH_API_KEY(String SMSH_API_KEY) {
        this.SMSH_API_KEY = SMSH_API_KEY;
    }

    private String getSMSH_SECRET_KEY() {
        return SMSH_SECRET_KEY;
    }

    private void setSMSH_SECRET_KEY(String SMSH_SECRET_KEY) {
        this.SMSH_SECRET_KEY = SMSH_SECRET_KEY;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
