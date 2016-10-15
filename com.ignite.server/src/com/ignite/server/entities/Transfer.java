package com.ignite.server.entities;

import java.util.GregorianCalendar;

public class Transfer {
	private int ID;
	private GregorianCalendar WHEN;
	private double AMOUNT;
	private int ID_ACCOUNT_FROM;
	private int ID_ACCOUNT_TO;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public GregorianCalendar getWHEN() {
		return WHEN;
	}

	public void setWHEN(GregorianCalendar wHEN) {
		WHEN = wHEN;
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
}
