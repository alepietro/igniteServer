package com.ignite.server.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SETTING")
@XmlAccessorType(XmlAccessType.FIELD)
public class Setting {
	@XmlElement(required = true)
	private int ID;
	@XmlElement(required = true)
	private String SETTINGS;
	@XmlElement(required = true)
	private int ID_CLIENT;
	@XmlElement(required = true)
	private String VALUE;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSETTINGS() {
		return SETTINGS.toUpperCase();
	}

	public void setSETTINGS(String sETTINGS) {
		SETTINGS = sETTINGS.toUpperCase();
	}

	public int getID_CLIENT() {
		return ID_CLIENT;
	}

	public void setID_CLIENT(int iD_CLIENT) {
		ID_CLIENT = iD_CLIENT;
	}

	public String getVALUE() {
		return VALUE.toUpperCase();
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE.toUpperCase();
	}

	public String toString() {
		return ("ID=*" + ID + "*,ID_CLIENT=*" + ID_CLIENT + "*,SETTINGS=*" + SETTINGS + "*,VALUE=*" + VALUE + "*");
	}

	public String toXml() {
		return ("<SETTING><ID>" + ID + "</ID><ID_CLIENT>" + ID_CLIENT + "</ID_CLIENT><SETTINGS>" + SETTINGS
				+ "</SETTINGS><VALUE>" + VALUE + "</VALUE></SETTING>");
	}

}
