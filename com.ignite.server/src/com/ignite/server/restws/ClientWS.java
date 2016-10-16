package com.ignite.server.restws;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ignite.server.dao.MyClientDAO;
import com.ignite.server.dao.MySqlDao;
import com.ignite.server.entities.Client;
import com.ignite.server.entities.LoginInformation;

@Path("/clients")
public class ClientWS {

	// // This method is called if TEXT_PLAIN is request
	// @GET
	// @Path("/getAll")
	// @Produces(MediaType.TEXT_PLAIN)
	// public String getAllClients() {
	// String res = "PLAIN";
	//
	// return res;
	// }

	// This method is called if XML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_XML)
	public String getAllClients() {
		String res = "<CLIENTS>" + "\n";

		Client temp = null;
		Vector<Client> vr = MyClientDAO.getAllClients();
		if (vr != null) {
			for (int i = 0; i < vr.size(); i++) {
				temp = vr.elementAt(i);
				res += temp.toXml() + "\n";
			}
		}

		res += "</CLIENTS>";

		return res;
	}

	// // This method is called if HTML is request
	// @GET
	// @Path("/getAll")
	// @Produces(MediaType.TEXT_HTML)
	// public String getAllClients() {
	// String res = "<html> " + "<title>" + "Hello Jersey" + "</title>" +
	// "<body>";
	// res += "<h1>No SETTINGS available</h1>\n";
	// res += "</body>";
	// res += "</html>";
	// return res;
	//
	// }

	@POST
	@Path("/checkLogin")
	@Consumes(MediaType.APPLICATION_XML)
	public String checkLogin(LoginInformation l) {
		String res = "";
		res = MySqlDao.checkLogin(l);
		return (res);
	}

	@POST
	@Path("/checkStartPage")
	@Consumes(MediaType.APPLICATION_XML)
	public String checkStartPage(LoginInformation l) {
		String res = "";
		res = MySqlDao.checkStartPage(l);
		return (res);
	}

	@POST
	@Path("/getClient")
	@Consumes(MediaType.APPLICATION_XML)
	public String getClient(Client c) {
		String res = "<CLIENTS>" + "\n";

		Client temp = null;
		Vector<Client> vr = MyClientDAO.getClients(c);
		if (vr != null) {
			for (int i = 0; i < vr.size(); i++) {
				temp = vr.elementAt(i);
				res += temp.toXml() + "\n";
			}
		}

		res += "</CLIENTS>";

		return res;
	}

	@POST
	@Path("/modClient")
	@Consumes(MediaType.APPLICATION_XML)
	public String modClient(Client c) {
		String res = "";

		res = "" + MyClientDAO.modClient(c);

		return res;
	}

	@POST
	@Path("/addClient")
	@Consumes(MediaType.APPLICATION_XML)
	public String addClient(Client c) {
		String res = "";

		res = "" + MyClientDAO.addClient(c);

		return res;
	}

}
