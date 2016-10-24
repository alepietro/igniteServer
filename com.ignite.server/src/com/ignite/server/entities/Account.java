package com.ignite.server.entities;

import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ACCOUNT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

	@XmlElement
	private int ID;
	@XmlElement
	private String ACCOUNT_NUMBER;
	@XmlElement
	private String CREATION;
	@XmlElement
	private double AVAILABILITY;
	@XmlElement
	private int ID_CLIENT;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER.toUpperCase();
	}

	public void setACCOUNT_NUMBER(String aCCOUNT_NUMBER) {
		ACCOUNT_NUMBER = aCCOUNT_NUMBER.toUpperCase();
	}

	public String getCREATION() {
		return CREATION;
	}

	public void setCREATION(String cREATION) {
		CREATION = cREATION;
	}

	public double getAVAILABILITY() {
		return AVAILABILITY;
	}

	public void setAVAILABILITY(double aVAILABILITY) {
		AVAILABILITY = aVAILABILITY;
	}

	public int getID_CLIENT() {
		return ID_CLIENT;
	}

	public void setID_CLIENT(int iD_CLIENT) {
		ID_CLIENT = iD_CLIENT;
	}
	public String toXml() {
		String res = "";
		res = "<ACCOUNT>\n<ID>" + ID + "</ID>\n<AVAILABILITY>" + AVAILABILITY + "</AVAILABILITY>\n<ID_CLIENT>" + ID_CLIENT
				+ "</ID_CLIENT>\n<CREATION>" + CREATION + "</CREATION>\n<ACCOUNT_NUMBER>" + ACCOUNT_NUMBER
				+ "</ACCOUNT_NUMBER>\n</ACCOUNT>";

		return res;
	}
}
