package com.excercise.resources;

import com.excercise.service.ApplicationConfig;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by dikushwa
 */
public class AccountResourceIT extends AbstractResourceTest {

  public final String TEST_ACCOUNT_ID = "A000010";
  public final String RESOURCE_BASE_URL = "account";

  @Override
  protected Application configure() {
    ApplicationConfig cfg = new ApplicationConfig();
    return cfg;
  }

  @Test
  public void testGetAmount() {
    final Response response = target(RESOURCE_PATH).path(RESOURCE_BASE_URL).path("balance").path(TEST_ACCOUNT_ID)
            .request().get(Response.class);

    String resultJson = response.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

  }

  @Test
  public void testUpdateAmount() {
    final Response response = target(RESOURCE_PATH).path(RESOURCE_BASE_URL)
            .path("update")
            .path(TEST_ACCOUNT_ID)
            .queryParam("amount", "50000")
            .request().post(null);

    String resultJson = response.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(200, response.getStatus());
  }
  
  @Test
  public void testIncorrectAccount() {
    final Response response = target(RESOURCE_PATH).path(RESOURCE_BASE_URL)
            .path("update")
            .path("A0001000")
            .queryParam("amount", "50000")
            .request().post(null);

    String resultJson = response.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(200, response.getStatus());
  }

  @Test
  public void testGetAllAccountDetails() {
    final Response response = target(RESOURCE_PATH).path(RESOURCE_BASE_URL).path("all")
            .request().get(Response.class);

    String resultJson = response.readEntity(String.class);
    System.out.println("========Response===========");
    System.out.println(resultJson);
    System.out.println("========Response Ends=========");
    assertEquals(200, response.getStatus());
  }

}
