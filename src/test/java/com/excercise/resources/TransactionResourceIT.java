/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import static com.excercise.resources.AbstractResourceTest.RESOURCE_PATH;
import com.excercise.service.ApplicationConfig;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author dikushwa
 */
public class TransactionResourceIT extends AbstractResourceTest{
     public final String ACCOUNT_ID = "A000004";
     

    @Override
    protected Application configure() {
        ApplicationConfig cfg = new ApplicationConfig();
        return cfg;
    }
   
    @Test
    public void testGetAll() {
        final Response response = target(RESOURCE_PATH + "/transaction").path("all").path(ACCOUNT_ID)
                .request().get(Response.class);
       
        String retVal = response.readEntity(String.class);
        System.out.println("Response: " + retVal);
        System.out.println("---------------------------");
        assertEquals(200, response.getStatus());

    }
    @Test
    public void testTransferFund() throws Exception {
        System.out.println("testCreateTask");

        String createPayLoad = "{\"srcAccount\": \"A000004\",\n"
                + "\"destAccount\": \"A000003\",\n"
                + "\"amount\": \"15000\"}";
        Response result = target(RESOURCE_PATH + "/transaction").path("fundtransafer")
                .request(APPLICATION_JSON)
                .post(Entity.entity(createPayLoad, APPLICATION_JSON));

        System.out.println(result.getStatus());
        String resultJson = result.readEntity(String.class);
        System.out.println("===================");
        System.out.println(resultJson);
        System.out.println("===================");
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        
    }
    
}
