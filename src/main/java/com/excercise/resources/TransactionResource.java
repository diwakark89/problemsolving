/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import com.excercise.dto.TransactionDTO;
import com.excercise.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author dikushwa
 */
@Path("/transaction")
public class TransactionResource {

  @Inject
  private TransactionRepository transactionRepository;

  
  ObjectMapper mapper = null;

  @POST
  @Path("/fundtransafer")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response fundTransfer(String transactionStr) {
    mapper = new ObjectMapper();
    try {

      TransactionDTO transactionDto = mapper.readValue(transactionStr, TransactionDTO.class);
      return Response.ok(transactionRepository.createTransaction(transactionDto)).build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_XML_TYPE).build();
    }
  }

  @GET
  @Path("/all/{accountId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllTransaction(@PathParam("accountId") String accountId) {
    try {
      List<String> transactionList= transactionRepository.getAllTransactionsMesasge(accountId);
      if(transactionList!=null && transactionList.size()>0)
      return Response.ok(transactionList).build();
      else
        return Response.ok("There is no transactions made against your account").build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_XML_TYPE).build();
    }
  }
}
