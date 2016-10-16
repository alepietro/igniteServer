package com.ignite.server.entities;

import java.util.GregorianCalendar;

public class LogEvent {
	private int ID;
	private GregorianCalendar WHEN;
	private String MESSAGE;

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

	public String getMESSAGE() {
		return MESSAGE.toUpperCase();
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE.toUpperCase();
	}
}
