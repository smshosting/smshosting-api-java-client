/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

public class Estimate {
    
    private double cost;
    private double userCredit;
    private int smsCount;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(double userCredit) {
        this.userCredit = userCredit;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }
    
    
}
