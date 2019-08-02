/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import com.excercise.repository.AccountRepository;
import com.excercise.util.EntityMapper;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.*;

/**
 *
 * @author dikushwa
 */
@Path("/account")
public class AccountResource {

  @Inject
  private AccountRepository accountRepository;

  EntityMapper entityMapper=new EntityMapper();

  @GET
  @Path("/balance/{accountId}")
  public Response getBalance(@PathParam("accountId") String accountId) {
    try {
      return Response.ok(accountRepository.getAccountBalance(accountId)).build();
    } catch (SQLException ex) {
      return Response.status(NOT_FOUND).entity(ex.getMessage()).type(TEXT_HTML).build();
    }

  }

  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllAccounts(@PathParam("accountId") String accountId) {
    try {
      return Response.ok(entityMapper.generateString(accountRepository.getAllAccountDetails())).build();
    } catch (Exception ex) {
      return Response.status(INTERNAL_SERVER_ERROR).entity(ex.getMessage()).type(TEXT_HTML).build();
    }

  }
  
  @POST
  @Path("/update/{accountId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateAccounts(@PathParam("accountId") String accountId, @QueryParam("amount") String amount) {
    try {
      if (Float.parseFloat(amount) > 0) {
        accountRepository.updateAccountDetails(accountId, Float.parseFloat(amount));
        return Response.ok(entityMapper.generateString(accountRepository.getAccountBalance(accountId))).build();
      } else {
        return Response.ok("Please add more then 0 amount").build();
      }

    } catch (Exception ex) {
      return Response.status(INTERNAL_SERVER_ERROR).entity(ex.getMessage()).type(TEXT_HTML).build();
    }

  }
}
