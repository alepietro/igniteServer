package com.ignite.server.entities;

public class Client {
	private int ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String PID_NUMBER;
	private int ID_PASSWORD;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getPID_NUMBER() {
		return PID_NUMBER;
	}

	public void setPID_NUMBER(String pID_NUMBER) {
		PID_NUMBER = pID_NUMBER;
	}

	public int getID_PASSWORD() {
		return ID_PASSWORD;
	}

	public void setID_PASSWORD(int iD_PASSWORD) {
		ID_PASSWORD = iD_PASSWORD;
	}
}
