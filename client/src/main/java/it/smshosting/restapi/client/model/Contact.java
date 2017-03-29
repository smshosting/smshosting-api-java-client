/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String msisdn;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private Integer postCode;
    private String province;
    private String country;
    private String email;
    private String homePhoneNumber;
    private List<String> pushApps;

    private List<Group> groups;

    private List<CustomField> customFields;

    /**
     * Creates a new instance of GadgetContact
     */
    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<Group>();
        }
        groups.add(group);
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public void addCustomField(CustomField customField) {
        if (customFields == null) {
            customFields = new ArrayList<CustomField>();
        }
        customFields.add(customField);
    }

    public List<String> getPushApps() {
        return pushApps;
    }

    public void setPushApps(List<String> pushApps) {
        this.pushApps = pushApps;
    }
}
