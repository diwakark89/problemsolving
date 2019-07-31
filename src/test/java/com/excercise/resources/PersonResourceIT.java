/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import static com.excercise.resources.AbstractResourceTest.RESOURCE_PATH;
import com.excercise.service.ApplicationConfig;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author dikushwa
 */
public class PersonResourceIT extends AbstractResourceTest{
     public final String TEST_USER_ID = "1000001";
     

    @Override
    protected Application configure() {
        ApplicationConfig cfg = new ApplicationConfig();
        return cfg;
    }
   
    @Test
    public void testGetAll() {
        final Response response = target(RESOURCE_PATH + "/person/"+TEST_USER_ID)
                //.queryParam("fields", "*")
                
                .request().get(Response.class);
       
        String retVal = response.readEntity(String.class);
        System.out.println("Response: " + retVal);
        System.out.println("---------------------------");
        assertEquals(200, response.getStatus());

    }
    
}
