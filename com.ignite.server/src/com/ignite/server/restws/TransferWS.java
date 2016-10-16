package com.ignite.server.restws;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ignite.server.dao.MyTransferDAO;
import com.ignite.server.entities.Transfer;

@Path("/transfers")
public class TransferWS {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPlainTextTransfer() {
		String res = "";
		Vector<Transfer> vs = MyTransferDAO.getAllTransfers();

		for (int i = 0; i < vs.size(); i++) {
			res += vs.elementAt(i) + "\n";
		}
		return res;
	}

	// This method is called if XML is request
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_XML)
	public String getXmlTransfers() {
		String res = "<?xml version=\"1.0\"?>" + "\n" + "<TRANSFER>";

		Vector<Transfer> vs = MyTransferDAO.getAllTransfers();

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
	public String getHtmlTransfers() {
		String res = "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body>";

		Vector<Transfer> vs = MyTransferDAO.getAllTransfers();
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
	@Path("/getTransfer")
	@Consumes(MediaType.APPLICATION_XML)
	public String getTransfer(Transfer c) {
		String res = "<TRANSFERS>" + "\n";

		Transfer temp = null;
		Vector<Transfer> vr = MyTransferDAO.getTransfers(c);
		// if (vr != null) {
		// for (int i = 0; i < vr.size(); i++) {
		// temp = vr.elementAt(i);
		// res += temp.toXml() + "\n";
		// }
		// }

		res += "</TRANSFERS>";

		return res;
	}

	@POST
	@Path("/modTransfer")
	@Consumes(MediaType.APPLICATION_XML)
	public String modTransfer(Transfer s) {
		String res = "";

		res = "" + MyTransferDAO.modTransfer(s);

		return res;
	}

	@POST
	@Path("/addTransfer")
	@Consumes(MediaType.APPLICATION_XML)
	public String addTransfer(Transfer s) {
		String res = "";

		res = "" + MyTransferDAO.addTransfer(s);

		return res;
	}

}
