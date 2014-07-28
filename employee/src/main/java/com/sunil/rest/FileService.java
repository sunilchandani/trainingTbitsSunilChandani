package com.sunil.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/file")
public class FileService {
	private static final String FILE_PATH = "/home/sunil/Downloads/pool.jpg";
 
	@GET
	@Path("/get")
	@Produces("image/jpg")
	public Response getFile() {
 
		File file = new File(FILE_PATH);
 
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition",
			"attachment; filename=\"image.jpg\"");
		return response.build();
	}
}
