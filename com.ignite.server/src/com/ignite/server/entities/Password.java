package com.ignite.server.entities;

import java.util.GregorianCalendar;

public class Password {

	private int ID;
	private String PASSWORD;
	private GregorianCalendar WHEN;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public GregorianCalendar getWHEN() {
		return WHEN;
	}

	public void setWHEN(GregorianCalendar wHEN) {
		WHEN = wHEN;
	}

}
