/*
 * Copyright (c) 2017 Smshosting.it
 * All rights reserved.
 */
package it.smshosting.restapi.client;

import it.smshosting.restapi.client.model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class SmsHostingClientTest {

    private SmsHostingClient client = null;

    public SmsHostingClientTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        //test credential
        client = new SmsHostingClient();
    }

    @After
    public void tearDown() {
        client = null;
    }
}
