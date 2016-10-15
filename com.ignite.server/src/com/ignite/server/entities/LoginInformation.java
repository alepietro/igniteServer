package com.ignite.server.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LOGIN")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginInformation {
	
	@XmlElement(required=true)
	private int ID;
	@XmlElement(required=false)
	private String PASSWORD;
	
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
	
	public String toString(){
		return("UserId = *" + ID + "*,Password = *" + PASSWORD + "*");
	}
}
