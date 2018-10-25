/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;


public class Group {

    private Integer id;
    private String name;    
    private Integer contactCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  

    public Integer getContactCount() {
        return contactCount;
    }

    public void setContactCount(Integer contactNumber) {
        this.contactCount = contactNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
