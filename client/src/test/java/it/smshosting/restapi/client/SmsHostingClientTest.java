/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.smshosting.restapi.client;

import it.smshosting.restapi.client.model.Estimate;
import it.smshosting.restapi.client.model.SendResult;
import it.smshosting.restapi.client.model.SmsReceivedSearchResult;
import it.smshosting.restapi.client.model.SmsReceivedSimResult;
import it.smshosting.restapi.client.model.SmsSearchResult;
import it.smshosting.restapi.client.model.User;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author sviluppoimac
 */
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
        client = new SmsHostingClient("SMSH2WTG4E7BVAI6TF1YX", "5JZ7H16W18LJV0EV5887WKWYUR8ROS1E");
    }
    
    @After
    public void tearDown() {
        client = null;
    }

    /**
     * Test of getUser method, of class SmsHostingClient.
     */
    @Test
    public void testGetUser() {
        System.out.println("Test getUser");
        User result = client.getUser();
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of sendSms method, of class SmsHostingClient.
     */
    @Test
    public void testSendSms() {
        System.out.println("Test sendSms");
        String to = "393480000000";
        String text = "test";
        Boolean sandbox = true;
        SendResult result = client.sendSms(null, to, null, text, null, null, sandbox, null, null);
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of sendSmsBulk method, of class SmsHostingClient.
     */
    @Test
    public void testSendSmsBulk() {
        System.out.println("Test sendSmsBulk");
        String to = "393480000000";
        String text = "test";
        Boolean sandbox = true;
        SendResult result = client.sendSmsBulk(null, to, null, text, null, null, sandbox, null, null, null);
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of estimateSendSms method, of class SmsHostingClient.
     */
    @Test
    public void testEstimateSendSms() {
        System.out.println("Test estimateSendSms");
        String to = "393480000000";
        String text = "test";
        Estimate result = client.estimateSendSms(null, to, null, text, null);
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of cancelSms method, of class SmsHostingClient.
     */
    /*@Test
    public void testCancelSms() {
        System.out.println("cancelSms");
        String id = "";
        String transactionId = "";
        SmsHostingClient instance = null;
        boolean expResult = false;
        boolean result = instance.cancelSms(id, transactionId);
        assertEquals(expResult, result);
        System.out.println("OK");
    }*/

    /**
     * Test of searchSms method, of class SmsHostingClient.
     */
    @Test
    public void testSearchSms() {
        System.out.println("Test searchSms");
        String transactionId = "xxx";
        int offset = 0;
        int limit = 20;
        SmsSearchResult result = client.searchSms(null, transactionId, null, null, null, null, offset, limit);
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of searchSmsReceived method, of class SmsHostingClient.
     */
    @Test
    public void testSearchSmsReceived() {
        System.out.println("Test searchSmsReceived");
        String from = "393480000000";
        int offset = 0;
        int limit = 20;
        SmsReceivedSearchResult result = client.searchSmsReceived(from, null, null, null, offset, limit);
        assertNotNull(result);
        System.out.println("OK");
    }

    /**
     * Test of getSimForReceiveSmsList method, of class SmsHostingClient.
     */
    @Test
    public void testGetSimForReceiveSmsList() {
        System.out.println("Test getSimForReceiveSmsList");
        List<SmsReceivedSimResult> result = client.getSimForReceiveSmsList();
        assertNotNull(result);
        System.out.println("OK");
    }
    
    
    
}
