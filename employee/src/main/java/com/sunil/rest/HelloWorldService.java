package com.sunil.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg, @Context HttpHeaders httpHeader) {
 
		String output = "Jersey say : " + msg +"\n";
		//output = output + httpHeader.getRequestHeader("user-agent").toString();
		output = output + httpHeader.getRequestHeaders().toString();
		return Response.status(200).entity(output).build();
 
	} 
}
