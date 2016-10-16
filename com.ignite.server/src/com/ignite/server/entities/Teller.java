package com.ignite.server.entities;

public class Teller {
	private int ID;
	private String NAME;
	private String USERNAME;
	private String PASSWORD;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME.toUpperCase();
	}

	public void setNAME(String nAME) {
		NAME = nAME.toUpperCase();
	}

	public String getUSERNAME() {
		return USERNAME.toUpperCase();
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME.toUpperCase();
	}

	public String getPASSWORD() {
		return PASSWORD.toUpperCase();
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD.toUpperCase();
	}
}
