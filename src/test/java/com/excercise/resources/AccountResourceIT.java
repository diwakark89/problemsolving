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

    public final String TEST_USER_ID = "TEST_USER@ORACLE.COM";

    @Override
    protected Application configure() {
        ApplicationConfig cfg = new ApplicationConfig();
        return cfg;
    }

    @Test
    public void testGetAll() {
        final Response response = target(RESOURCE_PATH + "/hello/world")
                //.queryParam("fields", "*")
                .request().get(Response.class);
       
        String retVal = response.readEntity(String.class);
        System.out.println("Response: " + retVal);
        System.out.println("---------------------------");
        assertEquals(200, response.getStatus());

    }

}
