package com.ignite.server.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CLIENT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
	@XmlElement
	private int ID;
	@XmlElement
	private String FIRST_NAME;
	@XmlElement
	private String LAST_NAME;
	@XmlElement
	private String PID_NUMBER;
	@XmlElement
	private int ID_PASSWORD;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME.toUpperCase();
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME.toUpperCase();
	}

	public String getLAST_NAME() {
		return LAST_NAME.toUpperCase();
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME.toUpperCase();
	}

	public String getPID_NUMBER() {
		return PID_NUMBER.toUpperCase();
	}

	public void setPID_NUMBER(String pID_NUMBER) {
		PID_NUMBER = pID_NUMBER.toUpperCase();
	}

	public int getID_PASSWORD() {
		return ID_PASSWORD;
	}

	public void setID_PASSWORD(int iD_PASSWORD) {
		ID_PASSWORD = iD_PASSWORD;
	}
	
	public String toXml(){
		return("<CLIENT>\n<ID>"+ID+"</ID>\n<FIRST_NAME>"+FIRST_NAME+"</FIRST_NAME>\n<LAST_NAME>"+LAST_NAME+"</LAST_NAME>\n<ID_PASSWORD>"+ID_PASSWORD+"</ID_PASSWORD>\n<PID_NUMBER>"+PID_NUMBER+"</PID_NUMBER>\n</CLIENT>");
	}
}
