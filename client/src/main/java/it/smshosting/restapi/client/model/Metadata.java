/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.smshosting.restapi.client.model;

public class Metadata {
    
    public static final int DEFAULT_LIMIT = 20;
    
    private int count;
    private int offset;
    private int limit;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
    

    
}
