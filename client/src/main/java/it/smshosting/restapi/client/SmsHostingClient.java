/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import kong.unirest.HttpResponse;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.MultipartBody;
import it.smshosting.restapi.client.model.Alias;
import it.smshosting.restapi.client.model.AliasListResponse;
import it.smshosting.restapi.client.model.Contact;
import it.smshosting.restapi.client.model.ContactSearchResult;
import it.smshosting.restapi.client.model.DeleteAliasResult;
import it.smshosting.restapi.client.model.Estimate;
import it.smshosting.restapi.client.model.GenericResponse;
import it.smshosting.restapi.client.model.Group;
import it.smshosting.restapi.client.model.SendResult;
import it.smshosting.restapi.client.model.SmsInfo;
import it.smshosting.restapi.client.model.SmsReceivedSearchResult;
import it.smshosting.restapi.client.model.SmsReceivedSimResult;
import it.smshosting.restapi.client.model.SmsSearchResult;
import it.smshosting.restapi.client.model.User;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kong.unirest.GetRequest;
import kong.unirest.HttpRequestWithBody;

/**
 * Client Rest API Smshosting.it
 *
 * https://www.smshosting.it/it/docs/sms-rest-api/introduzione
 *
 */
public class SmsHostingClient {

    private static final Logger log = Logger.getLogger(SmsHostingClient.class.getName());        

    public static final String DEFAULT_ENDPOINT = "https://api.smshosting.it/rest/api/";    
    
    private static SmsHostingClient clientSmsh;
    
    private String authKey;
    private String authSecret;

    public static SmsHostingClient getInstance(String key,String secret){
        if (clientSmsh == null){ //if there is no instance available... create new one
            clientSmsh = new SmsHostingClient(key,secret);
        }

        return clientSmsh;
    }    
    
    
    
    public SmsHostingClient(String authKey, String authSecret) {
        this.authKey = authKey;
        this.authSecret = authSecret;

        //Unirest.setTimeouts(10000, 300000);
        
        Unirest.config()
                .connectTimeout(10000)
                .socketTimeout(300000);
                
        
        //mapper for unirest
        Unirest.config().setObjectMapper(new ObjectMapper() {
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
    
    
    
     public Alias createAlias(String alias,
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
             String encoding) {
        try {
                       
            HttpRequestWithBody request = Unirest.post(buildURL("alias"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(alias != null) params.put("alias",alias);
            if(businessname != null) params.put("businessname",businessname);
            if(address != null) params.put("address",address);
            if(city != null) params.put("city",city);
            if(postcode != null) params.put("postcode",postcode);
            if(province != null) params.put("province",province);
            if(country != null) params.put("country",country);
            if(vatnumber != null) params.put("vatnumber",vatnumber);
            if(email != null) params.put("email",email);
            if(phone != null) params.put("phone",phone);
            if(taxcode != null) params.put("taxcode",taxcode);
            if(encoding != null) params.put("encoding",encoding);
            
            HttpResponse<Alias> response = request
                    .fields(params)
                    .asObject(Alias.class); 
            
            return response.getBody();            


            // SMS send
            /*
            HttpResponse<Alias> response = Unirest.post(buildURL("alias"))
                    .basicAuth(authKey, authSecret)
                    .field("alias", alias)
                    .field("businessname", businessname)
                    .field("address", address)
                    .field("city", city)
                    .field("postcode", postcode)
                    .field("province", province)
                    .field("country", country)
                    .field("vatnumber", vatnumber)
                    .field("email", email)
                    .field("phone", phone)
                    .field("taxcode", taxcode)
                     .field("encoding", encoding)
                    .asObject(Alias.class);
            
            return response.getBody();
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
     
     public AliasListResponse getAlias() {
        try {
            AliasListResponse result = new AliasListResponse();
            // SMS send
            HttpResponse<String> response = Unirest.get(buildURL("alias/list"))
                    .basicAuth(authKey, authSecret)
                    .asString();
            
            if (response != null && response.getStatus() >= 200 && response.getStatus() <= 299) {
                TypeReference tr = new TypeReference<List<Alias>>() {};
                result.setAliasList((List<Alias>)new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.getBody(), tr));
            } else {
                result.setErrorCode(response.getStatusText());
            }            

            return result;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }     
    
     public GenericResponse deleteAlias(String id) {
        try {
            GenericResponse result = new GenericResponse();
            // SMS send
            HttpResponse<String> response = Unirest.delete(buildURL("alias/"+id))
                    .basicAuth(authKey, authSecret)
                    .asString();

            if (response != null && response.getStatus() >= 200 && response.getStatus() <= 299) {
                
            } else {
                TypeReference tr = new TypeReference<GenericResponse>() {};
                result = new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.getBody(), tr);                
            }   
            
            return result;

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }     

    ///////
    //sms requests
    //////
    public SendResult sendSms(String from,
            String to,
            String group,
            String text,
            String sendDate,
            String transactionId,
            Boolean sandbox,
            String statusCallback,
            String encoding) {
        try {
            // SMS send            
            HttpRequestWithBody request = Unirest.post(buildURL("sms/send"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(from != null) params.put("from",from);
            if(to != null) params.put("to",to);
            if(group != null) params.put("group",group);
            if(text != null) params.put("text",text);
            if(sendDate != null) params.put("date",sendDate);
            if(transactionId != null) params.put("transactionId",transactionId);
            if(sandbox != null) params.put("sandbox",sandbox.toString());
            if(statusCallback != null) params.put("statusCallback",statusCallback);
            if(encoding != null) params.put("encoding",encoding);
            
            HttpResponse<SendResult> response = request
                    .fields(params)
                    .asObject(SendResult.class); 
            
            return response.getBody();
            /*
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
            
            return response.getBody();*/

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public SendResult sendSmsBulk(String from,
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
            
            HttpRequestWithBody request = Unirest.post(buildURL("sms/sendbulk"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(from != null) params.put("from",from);
            if(to != null) params.put("to",to);
            if(group != null) params.put("group",group);  
            if(text != null) params.put("text",text);
            if(sendDate != null) params.put("date",sendDate);
            if(transactionId != null) params.put("transactionId",transactionId);  
            if(sandbox != null) params.put("sandbox",sandbox.toString());
            if(statusCallback != null) params.put("statusCallback",statusCallback);
            if(transactionCallback != null) params.put("transactionCallback",transactionCallback);            
            if(encoding != null) params.put("encoding",encoding);
            
            HttpResponse<SendResult> response = request
                    .fields(params)
                    .asObject(SendResult.class); 
            
            return response.getBody();             
            
            // SMS send
            /*
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
            */

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
            
            HttpRequestWithBody request = Unirest.post(buildURL("sms/estimate"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(from != null) params.put("from",from);
            if(to != null) params.put("to",to);
            if(group != null) params.put("group",group);
            if(text != null) params.put("text",text);            
            
            HttpResponse<Estimate> response = request
                    .fields(params)
                    .asObject(Estimate.class); 
            
            return response.getBody();
            
            // SMS send
            /*
            HttpResponse<Estimate> response = Unirest.post(buildURL("sms/estimate"))
                    .basicAuth(authKey, authSecret)
                    .field("from", from)
                    .field("to", to)
                    .field("group", group)
                    .field("text", text)
                    .asObject(Estimate.class);
            return response.getBody();
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public GenericResponse cancelSms(String id, String transactionId) {
        try {
                       
            
            HttpRequestWithBody request = Unirest.post(buildURL("sms/cancel"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(id != null) params.put("id",id);   
            if(transactionId != null) params.put("transactionId",transactionId);  
            
            HttpResponse<GenericResponse> response = request
                    .fields(params)
                    .asObject(GenericResponse.class); 
            
            return response.getBody();             
            
            // SMS send
            /*
            HttpResponse<GenericResponse> response = Unirest.post(buildURL("sms/cancel"))
                    .basicAuth(authKey, authSecret)
                    .field("id", id)
                    .field("transactionId", transactionId)
                    .asObject(GenericResponse.class);
            return response.getBody();
            */
            /*            
            if (response != null && response.getStatus() >= 200 && response.getStatus() <= 299) {
                TypeReference tr = new TypeReference<List<SmsInfo>>() {};
                return new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.getBody(), tr);
            } else {
                return null;
            }   
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
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
            
            GetRequest request = Unirest.get(buildURL("sms/search"))
                    .basicAuth(authKey, authSecret);    
            
            HashMap <String,Object> params = new HashMap<>();
            if(id != null) params.put("id",id);
            if(transactionId != null) params.put("transactionId",transactionId);
            if(msisdn != null) params.put("msisdn",msisdn); 
            if(fromDate != null) params.put("fromDate",fromDate);
            if(toDate != null) params.put("toDate",toDate);
            if(status != null) params.put("status",status);
            params.put("offset",offset);
            params.put("limit",limit);
            
            HttpResponse<SmsSearchResult> response = request
                    .queryString(params)
                    .asObject(SmsSearchResult.class); 
            
            return response.getBody();            

            /*
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
            */
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

/*
            HttpRequestWithBody request = Unirest.post(buildURL("sms/send"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(from != null) params.put("from",from);
            if(to != null) params.put("to",to);
            if(group != null) params.put("group",group);
            if(text != null) params.put("text",text);
            if(sendDate != null) params.put("date",sendDate);
            if(transactionId != null) params.put("transactionId",transactionId);
            if(sandbox != null) params.put("sandbox",sandbox.toString());
            if(statusCallback != null) params.put("statusCallback",statusCallback);
            if(encoding != null) params.put("encoding",encoding);
            
            HttpResponse<SendResult> response = request
                    .fields(params)
                    .asObject(SendResult.class); 
            
            return response.getBody();            
*/             
            GetRequest request = Unirest.get(buildURL("sms/received/search"))
                    .basicAuth(authKey, authSecret);            
            
            HashMap <String,Object> params = new HashMap<>();
            if(from != null) params.put("from",from);
            if(simIdRef != null) params.put("simIdRef",simIdRef);    
            if(fromDate != null) params.put("fromDate",fromDate);
            if(toDate != null) params.put("toDate",toDate);
            params.put("offset",offset);
            params.put("limit",limit);
            
            HttpResponse<SmsReceivedSearchResult> response = request
                    .queryString(params)
                    .asObject(SmsReceivedSearchResult.class); 
            
            return response.getBody();             
            
 /*           
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
*/

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
    //phonebook groups
    //////
    
    public List<Group> getGroupList() {
        try {

            HttpResponse<String> response = Unirest.get(buildURL("phonebook/group/list"))
                    .basicAuth(authKey, authSecret)
                    .asString();
            if (response != null) {
                TypeReference tr = new TypeReference<List<Group>>() {};
                return new com.fasterxml.jackson.databind.ObjectMapper().readValue(response.getBody(), tr);
            }
            return null;
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    public Group getGroup(Integer id) {
        try {
            HttpResponse<Group> response = Unirest.get(buildURL("phonebook/group/"+id))
                    .basicAuth(authKey, authSecret)
                    .asObject(Group.class);
            return response.getBody();
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
    
    public ContactSearchResult getGroupContacts(int id,int offset,int limit) {
        try {
            HttpResponse<ContactSearchResult> response = Unirest.get(buildURL("phonebook/group/"+id+"/contacts?offset="+offset+"&limit="+limit))
                    .basicAuth(authKey, authSecret)
                    .asObject(ContactSearchResult.class);
            return response.getBody();
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
    
    public Group addGroup(String name) {
        try {
                     
            HttpRequestWithBody request = Unirest.post(buildURL("phonebook/group"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(name != null) params.put("name",name);

            HttpResponse<Group> response = request
                    .fields(params)
                    .asObject(Group.class); 
            
            return response.getBody();
            
            // SMS send
            /*
            HttpResponse<Group> response = Unirest.post(buildURL("phonebook/group"))
                    .basicAuth(authKey, authSecret)
                    .field("name", name)
                    .asObject(Group.class);
            return response.getBody();
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }

    public Group updateGroup(int id,String name) {
        try {
                       
            HttpRequestWithBody request = Unirest.put(buildURL("phonebook/group/"+id))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(name != null) params.put("name",name);
            
            HttpResponse<Group> response = request
                    .fields(params)
                    .asObject(Group.class); 
            
            return response.getBody();            
            
            
            // SMS send
            /*
            HttpResponse<Group> response = Unirest.put(buildURL("phonebook/group/"+id))
                    .basicAuth(authKey, authSecret)
                    .field("name", name)
                    .asObject(Group.class);
            return response.getBody();
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    public boolean deleteGroup(int id,boolean deleteContacts) {
        try {
            // SMS send
            HttpResponse<String> response = Unirest.delete(buildURL("phonebook/group/"+id+"?delete_contacts="+deleteContacts))
                    .basicAuth(authKey, authSecret)
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
    
    ///////
    //phonebook contacts
    //////
    
    public ContactSearchResult searchContacts(String name, int offset, int limit) {
        try {
                        
            GetRequest request = Unirest.get(buildURL("phonebook/contact/search"))
                    .basicAuth(authKey, authSecret);
            
            HashMap <String,Object> params = new HashMap<>();
            if(name != null) params.put("name",name);
            params.put("offset",offset);
            params.put("limit",limit);
            
            HttpResponse<ContactSearchResult> response = request
                    .queryString(params)
                    .asObject(ContactSearchResult.class); 
            
            return response.getBody();            

/*
            HttpResponse<ContactSearchResult> response = Unirest.get(buildURL("phonebook/contact/search"))
                    .basicAuth(authKey, authSecret)
                    .queryString("name", name)
                    .queryString("offset", offset)
                    .queryString("limit", limit)
                    .asObject(ContactSearchResult.class);

            return response.getBody();
*/

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    public Contact getContact(String msisdn) {
        try {
            HttpResponse<Contact> response = Unirest.get(buildURL("phonebook/contact/"+msisdn))
                    .basicAuth(authKey, authSecret)
                    .asObject(Contact.class);
            return response.getBody();
        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }
    }
        
    public Contact addContact(String msisdn,
            String name,
            String lastname,
            String email,
            String groupsId,
            Map<String,String> customFields) {
        try {
                        
            HttpRequestWithBody request = Unirest.post(buildURL("phonebook/contact"))
                    .basicAuth(authKey, authSecret);

            HashMap <String,Object> params = new HashMap<>();
            if(msisdn != null) params.put("msisdn",msisdn);
            if(name != null) params.put("name",name);  
            if(lastname != null) params.put("lastname",lastname);  
            if(email != null) params.put("email",email);  
            if(groupsId != null) params.put("groupsId",groupsId);  
            
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    params.put(key, customFields.get(key));
                }
            } 
            
            HttpResponse<Contact> response = request
                    .fields(params)
                    .asObject(Contact.class); 
            
            return response.getBody(); 
            
/*            
            MultipartBody body = Unirest.post(buildURL("phonebook/contact"))
                    .basicAuth(authKey, authSecret)
                    .field("msisdn", msisdn)
                    .field("name", name)
                    .field("lastname", lastname)
                    .field("email", email)
                    .field("groupsId", groupsId);
            
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    body.field(key, customFields.get(key));
                }
            }
            
            HttpResponse<Contact> response = body.asObject(Contact.class);
            return response.getBody();
*/

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    public Contact updateContact(String msisdn,
            String name,
            String lastname,
            String email,
            String groupsId,
            Map<String,String> customFields) {
        try {
                        
            HttpRequestWithBody request = Unirest.put(buildURL("phonebook/contact/"+msisdn))
                    .basicAuth(authKey, authSecret);
            HashMap <String,Object> params = new HashMap<>();
            if(name != null) params.put("name",name);
            if(lastname != null) params.put("lastname",lastname);  
            if(email != null) params.put("email",email);  
            if(groupsId != null) params.put("groupsId",groupsId);  
            
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    params.put(key, customFields.get(key));
                }
            }            
            
            HttpResponse<Contact> response = request
                    .fields(params)
                    .asObject(Contact.class); 
            
            return response.getBody();            
            
            /*
            MultipartBody body = Unirest.put(buildURL("phonebook/contact/"+msisdn))
                    .basicAuth(authKey, authSecret)
                    .field("name", name)
                    .field("lastname", lastname)
                    .field("email", email)
                    .field("groupsId", groupsId);
            
            if(customFields != null) {
                for(String key : customFields.keySet()) {
                    body.field(key, customFields.get(key));
                }
            }
            
            HttpResponse<Contact> response = body.asObject(Contact.class);
            return response.getBody();
            */

        } catch (Exception e) {
            log.log(Level.SEVERE, "client error", e);
            return null;
        }

    }
    
    public boolean deleteContact(String msisdn) {
        try {
            // SMS send
            HttpResponse<String> response = Unirest.delete(buildURL("phonebook/contact/"+msisdn))
                    .basicAuth(authKey, authSecret)
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
