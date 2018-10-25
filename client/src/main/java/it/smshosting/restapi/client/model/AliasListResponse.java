/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.smshosting.restapi.client.model;

import java.util.List;

/**
 *
 * @author gianluca
 */
public class AliasListResponse extends GenericResponse {
    
    private List<Alias> aliasList;
    
    /**
     * @return the aliasList
     */
    public List<Alias> getAliasList() {
        return aliasList;
    }

    /**
     * @param aliasList the aliasList to set
     */
    public void setAliasList(List<Alias> aliasList) {
        this.aliasList = aliasList;
    }    
    
}
