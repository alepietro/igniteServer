package com.ignite.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.ignite.server.entities.Client;
import com.ignite.server.entities.LoginInformation;
import com.ignite.server.entities.Setting;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySqlDao {

	private static Connection myCon;

	private static boolean openConnection() {
		boolean res = true;

		if (myCon == null) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("Cannot find the driver in the classpath!", e);
			}

			/* Open Connection */
			try {
				myCon = DriverManager.getConnection(parameters.IgniteServerParameters.DBURL,
						parameters.IgniteServerParameters.DBUSER, parameters.IgniteServerParameters.DBPWD);
				myCon.setAutoCommit(false);
			} catch (SQLException e) {
				myCon = null;
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
			res = false;
		}

		return res;
	}

	public static Vector<Setting> getAllSettings() {
		Vector<Setting> res = null;
		String SQLQuery = "SELECT * FROM SETTINGS;";
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
				throw new IllegalStateException("Cannot select the Settings!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static String checkLogin(LoginInformation lInfo) {
		String res = "";
		String SQLQuery = "";
		Statement ps = null;
		ResultSet rs = null;

		if (openConnection()) {
			/* Ok let's get the login */
			try {

				SQLQuery = "SELECT P.FIRST FROM PASSWORD P,CLIENT C WHERE P.ID = C.ID_PASSWORD AND C.ID = "
						+ lInfo.getID() + " AND P.PASSWORD = '" + lInfo.getPASSWORD() + "'";
				ps = myCon.createStatement();
				System.out.println("\n\n" + SQLQuery);
				ps.executeQuery(SQLQuery);
				rs = ps.getResultSet();
				if (rs.next()) {
					if (rs.getString(1).equalsIgnoreCase("Y")) {
						/* Not Found */
						res = "mustChange";
					} else {
						/* Found */
						res = "Logged";
					}
				} else {
					res = "Unknown";
				}

			} catch (SQLException ex) {
				throw new IllegalStateException("Cannot select the login!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static String checkStartPage(LoginInformation lInfo) {
		String res = "";
		String SQLQuery = "";
		Statement ps = null;
		ResultSet rs = null;

		if (openConnection()) {
			/* Ok let's get the login */
			try {

				SQLQuery = "SELECT S.VALUE FROM SETTINGS S WHERE S.SETTINGS='"+parameters.IgniteServerParameters.PROPERTY_NAME_START_PAGE+"' AND S.ID_CLIENT="
						+ lInfo.getID();
				ps = myCon.createStatement();
				System.out.println("\n\n" + SQLQuery);
				ps.executeQuery(SQLQuery);
				rs = ps.getResultSet();
				if (rs.next()) {
					res = rs.getString("VALUE");
				} else {
					res = parameters.IgniteServerParameters.DEFAULT_START_PAGE;
				}

			} catch (SQLException ex) {
				throw new IllegalStateException("Cannot select the login!", ex);
			}

		}

		closeConnection();

		return (res);
	}

}
