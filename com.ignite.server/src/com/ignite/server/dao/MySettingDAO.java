package com.ignite.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.ignite.server.entities.Setting;

public class MySettingDAO {

	private static Connection myCon;

	private static boolean openConnection() {
		boolean res = true;

		if (myCon == null) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new IllegalStateException("Cannot find the driver in the classpath!", e);
			}

			/* Open Connection */
			try {
				myCon = DriverManager.getConnection(parameters.IgniteServerParameters.DBURL,
						parameters.IgniteServerParameters.DBUSER, parameters.IgniteServerParameters.DBPWD);
				myCon.setAutoCommit(false);
			} catch (SQLException e) {
				myCon = null;
				e.printStackTrace();
				throw new IllegalStateException("Cannot create the database connection!", e);
			}

		} else {
			/* Connection Already Open */
		}

		return res;
	}

	private static boolean closeConnection() {
		boolean res = true;

		try {
			myCon.close();
			myCon = null;
		} catch (Exception ex) {
			/* Log into Tomcat */
			ex.printStackTrace();
			res = false;
		}

		return res;
	}

	public static Vector<Setting> getAllSettings() {
		Vector<Setting> res = null;
		String SQLQuery = "SELECT * FROM SETTING;";
		Statement query = null;
		ResultSet rs = null;
		Setting tempS = null;

		if (openConnection()) {
			/* Ok let's get the settings */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Setting>();
					}
					tempS = new Setting();
					tempS.setID(rs.getInt("ID"));
					tempS.setID_CLIENT(rs.getInt("ID_CLIENT"));
					tempS.setSETTINGS(rs.getString("SETTINGS"));
					tempS.setVALUE(rs.getString("VALUE"));
					res.add(tempS);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Settings!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static Vector<Setting> getSettings(Setting c) {
		Vector<Setting> res = null;
		String SQLQuery = "SELECT * FROM SETTING WHERE";
		Statement query = null;
		ResultSet rs = null;
		Setting temp = null;

		/* Query building Start */
		if (c.getID() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID=" + c.getID() + "";
			} else {
				SQLQuery += " ID=" + c.getID() + "";
			}
		}
		if (c.getSETTINGS() != null && c.getSETTINGS().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND SETTINGS='" + c.getSETTINGS() + "'";
			} else {
				SQLQuery += " SETTINGS='" + c.getSETTINGS() + "'";
			}
		}
		if (c.getID_CLIENT() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID_CLIENT=" + c.getID_CLIENT() + "";
			} else {
				SQLQuery += " ID_CLIENT=" + c.getID_CLIENT() + "";
			}
		}
		if (c.getVALUE() != null && c.getVALUE().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND VALUE='" + c.getVALUE() + "'";
			} else {
				SQLQuery += " VALUE='" + c.getVALUE() + "'";
			}
		}
		if (SQLQuery.endsWith("WHERE")) {
			SQLQuery = "SELECT * FROM SETTING";
		}

		SQLQuery += ";";
		System.out.println("\tgetSettings.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's get the settings */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Setting>();
					}
					temp = new Setting();
					temp.setID(rs.getInt("ID"));
					temp.setSETTINGS(rs.getString("SETTINGS"));
					temp.setID_CLIENT(rs.getInt("ID_CLIENT"));
					temp.setVALUE(rs.getString("VALUE"));
					res.add(temp);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Settings matching the given Setting!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int modSetting(Setting s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "UPDATE SETTING SET SETTINGS = '" + s.getSETTINGS() + "',ID_CLIENT = " + s.getID_CLIENT()
				+ ",VALUE = '" + s.getVALUE() + "' WHERE ID = " + s.getID();
		SQLQuery += ";";
		System.out.println("\nmodSetting.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's modify the setting */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MySettingDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot modify the given Setting!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int addSetting(Setting s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "INSERT INTO SETTING (SETTINGS,ID_CLIENT,VALUE) VALUES ('" + s.getSETTINGS() + "',"
				+ s.getID_CLIENT() + ",'" + s.getVALUE() + "')";
		SQLQuery += ";";
		System.out.println("\nADDSetting.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's add the setting */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MySettingDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot add the given Setting!", ex);
			}

		}

		closeConnection();

		return (res);
	}

}
