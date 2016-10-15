package com.ignite.server.entities;

import java.util.GregorianCalendar;

public class Account {

	private int ID;
	private String ACCOUNT_NUMBER;
	private GregorianCalendar CREATION;
	private double AVAILABILITY;
	private int ID_CLIENT;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER;
	}

	public void setACCOUNT_NUMBER(String aCCOUNT_NUMBER) {
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
	}

	public GregorianCalendar getCREATION() {
		return CREATION;
	}

	public void setCREATION(GregorianCalendar cREATION) {
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

}
