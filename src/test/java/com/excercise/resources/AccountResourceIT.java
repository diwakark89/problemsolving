package com.excercise.resources;

import com.excercise.service.ApplicationConfig;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by dikushwa
 */
public class AccountResourceIT extends AbstractResourceTest {

    public final String TEST_ACCOUNT_ID = "A000002";
    public final String RESOURCE_BASE_URL = "account";

    @Override
    protected Application configure() {
        ApplicationConfig cfg = new ApplicationConfig();
        return cfg;
    }
   
    @Test
    public void testGetAll() {
        final Response response = target(RESOURCE_PATH ).path(RESOURCE_BASE_URL).path("balance").path(TEST_ACCOUNT_ID)
                //.queryParam("fields", "*")
                .request().get(Response.class);
       
        String retVal = response.readEntity(String.class);
        System.out.println("Response: " + retVal);
        System.out.println("---------------------------");
        assertEquals(200, response.getStatus());

    }

}
