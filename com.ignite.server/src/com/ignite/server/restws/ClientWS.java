package com.ignite.server.restws;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ignite.server.dao.MySqlDao;
import com.ignite.server.entities.Client;
import com.ignite.server.entities.LoginInformation;
import com.ignite.server.entities.Setting;

@Path("/clients")
public class ClientWS {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextSettings() {
		String res = "";

		return res;
	}

	// This method is called if XML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_XML)
	public String getXmlSettings() {
		String res = "<?xml version=\"1.0\"?>" + "\n" + "<SETTINGS>";

		return res;
	}

	// This method is called if HTML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlSettings() {
		String res = "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body>";

		return res;

	}

	@POST
	@Path("/checkLogin")
	@Consumes(MediaType.APPLICATION_XML)
	/*@Produces(MediaType.TEXT_PLAIN)*/
	public String checkLogin(LoginInformation l) {
		String res = "";
		res = MySqlDao.checkLogin(l);
		return (res);
	}
	
	@POST
	@Path("/checkStartPage")
	@Consumes(MediaType.APPLICATION_XML)
	/*@Produces(MediaType.TEXT_PLAIN)*/
	public String checkStartPage(LoginInformation l) {
		String res = "";
		res = MySqlDao.checkStartPage(l);
		return (res);
	}

}
