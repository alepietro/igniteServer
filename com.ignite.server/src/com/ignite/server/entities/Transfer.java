package com.ignite.server.entities;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TRANSFER")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transfer {
	@XmlElement
	private int ID;
	@XmlElement
	private String DAY;
	@XmlElement
	private double AMOUNT;
	@XmlElement
	private int ID_ACCOUNT_FROM;
	@XmlElement
	private int ID_ACCOUNT_TO;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDAY() {
		return DAY;
	}

	public void setDAY(String dAY) {
		DAY = dAY;
	}

	public double getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(double aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public int getID_ACCOUNT_FROM() {
		return ID_ACCOUNT_FROM;
	}

	public void setID_ACCOUNT_FROM(int iD_ACCOUNT_FROM) {
		ID_ACCOUNT_FROM = iD_ACCOUNT_FROM;
	}

	public int getID_ACCOUNT_TO() {
		return ID_ACCOUNT_TO;
	}

	public void setID_ACCOUNT_TO(int iD_ACCOUNT_TO) {
		ID_ACCOUNT_TO = iD_ACCOUNT_TO;
	}

	public String toXml() {
		String res = "";
		res = "<TRANSFER>\n<ID>" + ID + "</ID>\n<AMOUNT>" + AMOUNT + "</AMOUNT>\n<ID_ACCOUNT_FROM>" + ID_ACCOUNT_FROM
				+ "</ID_ACCOUNT_FROM>\n<ID_ACCOUNT_TO>" + ID_ACCOUNT_TO + "</ID_ACCOUNT_TO>\n<DAY>" + DAY
				+ "</WHEN>\n</TRANSFER>";

		return res;
	}
}
