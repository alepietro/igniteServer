package com.ignite.server.restws;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ignite.server.dao.MySqlDao;
import com.ignite.server.entities.Setting;

@Path("/settings")
public class SettingWS {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextSettings() {
		String res = "";
		Vector<Setting> vs = MySqlDao.getAllSettings();

		for (int i = 0; i < vs.size(); i++) {
			res += vs.elementAt(i) + "\n";
		}
		return res;
	}

	// This method is called if XML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_XML)
	public String getXmlSettings() {
		String res = "<?xml version=\"1.0\"?>" + "\n" + "<SETTINGS>";

		Vector<Setting> vs = MySqlDao.getAllSettings();

		for (int i = 0; i < vs.size(); i++) {
			res += "<SETTING>" + vs.elementAt(i) + "</SETTING>\n";
		}
		res += "</SETTINGS>";
		return res;
	}

	// This method is called if HTML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlSettings() {
		String res = "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body>";

		Vector<Setting> vs = MySqlDao.getAllSettings();
		if ((vs == null) || (vs.size() == 0)) {
			res += "<h1>No SETTINGS available</h1>\n";
		} else {
			for (int i = 0; i < vs.size(); i++) {
				res += "<h1>" + vs.elementAt(i) + "</h1>\n";
			}
		}
		res += "</body>";
		res += "</html>";
		return res;

	}
	
	
	  @POST
	  @Path("/check")
	  @Consumes(MediaType.APPLICATION_XML)
	  public void loadObject(Setting s) {
	    System.out.println("====================");
	    System.out.println("Setting:*" + s.toXml() + "*");
	  }

}
