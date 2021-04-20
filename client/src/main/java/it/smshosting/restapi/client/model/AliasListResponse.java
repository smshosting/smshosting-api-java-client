/*
 * Copyright (c) 2021 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client.model;

import it.smshosting.restapi.client.entities.Alias;
import java.util.List;

public class AliasListResponse extends GenericResponse {

    private List<Alias> aliasList;

    public List<Alias> getAliasList() {
        return aliasList;
    }

    public void setAliasList(List<Alias> aliasList) {
        this.aliasList = aliasList;
    }

}
