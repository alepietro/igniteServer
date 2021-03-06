package com.ignite.server.restws;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ignite.server.dao.MyAccountDAO;
import com.ignite.server.entities.Account;
import com.ignite.server.entities.Transfer;

@Path("/accounts")
public class TransferWS {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextAccount() {
		String res = "";
		Vector<Account> vs = MyAccountDAO.getAllAccounts();
		if (vs != null) {
			for (int i = 0; i < vs.size(); i++) {
				res += vs.elementAt(i) + "\n";
			}
		}
		else{
			res = "VUOTO!";
		}
		return res;
	}

	// This method is called if XML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_XML)
	public String getXmlAccounts() {
		String res = "<?xml version=\"1.0\"?>" + "\n" + "<TRANSFER>";

		Vector<Account> vs = MyAccountDAO.getAllAccounts();

		for (int i = 0; i < vs.size(); i++) {
			res += vs.elementAt(i).toXml();
		}
		res += "</TRANSFER>";
		return res;
	}

	// This method is called if HTML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String getHtmlAccounts() {
		String res = "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body>";

		Vector<Account> vs = MyAccountDAO.getAllAccounts();
		if ((vs == null) || (vs.size() == 0)) {
			res += "<h1>No TRANSFERS available</h1>\n";
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
	@Path("/getAccount")
	@Consumes(MediaType.APPLICATION_XML)
	public String getAccount(Account c) {
		String res = "<ACCOUNTS>" + "\n";

		Account temp = null;
		Vector<Account> vr = MyAccountDAO.getAccounts(c);
		// if (vr != null) {
		// for (int i = 0; i < vr.size(); i++) {
		// temp = vr.elementAt(i);
		// res += temp.toXml() + "\n";
		// }
		// }

		res += "</ACCOUNTS>";

		return res;
	}

	@POST
	@Path("/modAccount")
	@Consumes(MediaType.APPLICATION_XML)
	public String modAccount(Account s) {
		String res = "";

		res = "" + MyAccountDAO.modAccount(s);

		return res;
	}

	@POST
	@Path("/addAccount")
	@Consumes(MediaType.APPLICATION_XML)
	public String addAccount(Account s) {
		String res = "";

		res = "" + MyAccountDAO.addAccount(s);

		return res;
	}

}
