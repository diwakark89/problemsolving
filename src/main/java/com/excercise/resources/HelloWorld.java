/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excercise.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author dikushwa
 */
@Path("hello")
public class HelloWorld {
    
     @GET
    @Path("world")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelloWorld( ){
        
        return "Hello World";
    }

}
