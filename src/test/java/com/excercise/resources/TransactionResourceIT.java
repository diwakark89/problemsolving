/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import static com.excercise.resources.AbstractResourceTest.RESOURCE_PATH;
import com.excercise.service.ApplicationConfig;
import com.excercise.service.SetupTestData;
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
public class TransactionResourceIT extends AbstractResourceTest {

  public final String ACCOUNT_ID = "A000005";

  @Override
  protected Application configure() {
    ApplicationConfig cfg = new ApplicationConfig();
    return cfg;
  }

  
  //Only run this method for first time
  @Test
  public void testSetupTestforFirstTime() {
    SetupTestData data = new SetupTestData();
    data.load();
  }
  
  @Test
  public void testGetAll() {
    final Response response = target(RESOURCE_PATH + "/transaction").path("all").path(ACCOUNT_ID)
            .request().get(Response.class);

    String resultJson = response.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(200, response.getStatus());

  }

  @Test
  public void testTransferFund() throws Exception {
    System.out.println("testCreateTask");

    String createPayLoad = "{\"srcAccount\": \"A000005\",\n"
            + "\"destAccount\": \"A000004\",\n"
            + "\"amount\": \"30000\"}";
    Response result = target(RESOURCE_PATH + "/transaction").path("fundtransafer")
            .request(APPLICATION_JSON)
            .post(Entity.entity(createPayLoad, APPLICATION_JSON));

    String resultJson = result.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

  }

  @Test
  public void testTransferFundWithIncorrectSrcAccount() throws Exception {
    System.out.println("testCreateTask");

    String createPayLoad = "{\"srcAccount\": \"A000010\",\n"
            + "\"destAccount\": \"A000004\",\n"
            + "\"amount\": \"30000\"}";
    Response result = target(RESOURCE_PATH + "/transaction").path("fundtransafer")
            .request(APPLICATION_JSON)
            .post(Entity.entity(createPayLoad, APPLICATION_JSON));

    String resultJson = result.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

  }

  @Test
  public void testTransferFundWithIncorrectDestAccount() throws Exception {
    System.out.println("testCreateTask");

    String createPayLoad = "{\"srcAccount\": \"A000005\",\n"
            + "\"destAccount\": \"A000100\",\n"
            + "\"amount\": \"30000\"}";
    Response result = target(RESOURCE_PATH + "/transaction").path("fundtransafer")
            .request(APPLICATION_JSON)
            .post(Entity.entity(createPayLoad, APPLICATION_JSON));

    String resultJson = result.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

  }

  @Test
  public void testTransferFundWithMorethenBalanceAmount() throws Exception {
    System.out.println("testCreateTask");

    String createPayLoad = "{\"srcAccount\": \"A000005\",\n"
            + "\"destAccount\": \"A000004\",\n"
            + "\"amount\": \"9000000\"}";
    Response result = target(RESOURCE_PATH + "/transaction").path("fundtransafer")
            .request(APPLICATION_JSON)
            .post(Entity.entity(createPayLoad, APPLICATION_JSON));

    String resultJson = result.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());

  }
}
