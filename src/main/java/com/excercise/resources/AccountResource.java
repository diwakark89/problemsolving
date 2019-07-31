/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import com.excercise.repository.AccountRepository;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    @GET
    @Path("/balance/{accountId}")
    public Response getBalance(@PathParam("accountId") String accountId ){
        try {
         return Response.ok(accountRepository.getAccountDetails(accountId)).build();
        } catch (SQLException ex) {
            return Response.status(INTERNAL_SERVER_ERROR).entity(ex.getMessage()).type(TEXT_HTML).build();
        }
        
    }
}
