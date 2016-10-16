package com.ignite.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.ignite.server.entities.Client;

public class MyClientDAO {

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

	public static Vector<Client> getAllClients() {
		Vector<Client> res = null;
		String SQLQuery = "SELECT * FROM CLIENT;";
		Statement query = null;
		ResultSet rs = null;
		Client temp = null;

		if (openConnection()) {
			/* Ok let's get the settings */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Client>();
					}
					temp = new Client();
					temp.setID(rs.getInt("ID"));
					temp.setFIRST_NAME(rs.getString("FIRST_NAME"));
					temp.setLAST_NAME(rs.getString("LAST_NAME"));
					temp.setPID_NUMBER(rs.getString("PID_NUMBER"));
					temp.setID_PASSWORD(rs.getInt("ID_PASSWORD"));
					res.add(temp);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Clients!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static Vector<Client> getClients(Client c) {
		Vector<Client> res = null;
		String SQLQuery = "SELECT * FROM CLIENT WHERE";
		Statement query = null;
		ResultSet rs = null;
		Client temp = null;

		/* Query building Start */
		if (c.getFIRST_NAME() != null && c.getFIRST_NAME().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND FIRST_NAME='" + c.getFIRST_NAME() + "'";
			} else {
				SQLQuery += " FIRST_NAME='" + c.getFIRST_NAME() + "'";
			}
		}
		if (c.getLAST_NAME() != null && c.getLAST_NAME().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND LAST_NAME='" + c.getLAST_NAME() + "'";
			} else {
				SQLQuery += " LAST_NAME='" + c.getLAST_NAME() + "'";
			}
		}

		if (c.getPID_NUMBER() != null && c.getPID_NUMBER().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND PID_NUMBER='" + c.getPID_NUMBER() + "'";
			} else {
				SQLQuery += " PID_NUMBER='" + c.getPID_NUMBER() + "'";
			}
		}
		if (c.getID_PASSWORD() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID_PASSWORD='" + c.getID_PASSWORD() + "'";
			} else {
				SQLQuery += " ID_PASSWORD='" + c.getID_PASSWORD() + "'";
			}
		}
		if (c.getID() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID=" + c.getID() + "";
			} else {
				SQLQuery += " ID=" + c.getID() + "";
			}
		}

		if (SQLQuery.endsWith("WHERE")) {
			SQLQuery = "SELECT * FROM CLIENT";
		}

		SQLQuery += ";";
		System.out.println("\tgetClients.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's get the settings */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Client>();
					}
					temp = new Client();
					temp.setID(rs.getInt("ID"));
					temp.setFIRST_NAME(rs.getString("FIRST_NAME"));
					temp.setLAST_NAME(rs.getString("LAST_NAME"));
					temp.setPID_NUMBER(rs.getString("PID_NUMBER"));
					temp.setID_PASSWORD(rs.getInt("ID_PASSWORD"));
					res.add(temp);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Clients matching the given Client!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int modClient(Client c) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "UPDATE CLIENT SET FIRST_NAME = '" + c.getFIRST_NAME() + "',LAST_NAME = '" + c.getLAST_NAME()
				+ "',PID_NUMBER = '" + c.getPID_NUMBER() + "',ID_PASSWORD = " + c.getID_PASSWORD() + " WHERE ID = "
				+ c.getID();
		SQLQuery += ";";
		System.out.println("\nmodClient.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's get modify the client */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyClientDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot modify the given Client!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int addClient(Client c) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "INSERT INTO CLIENT (FIRST_NAME,LAST_NAME,PID_NUMBER,ID_PASSWORD) VALUES ('" + c.getFIRST_NAME()
				+ "','" + c.getLAST_NAME() + "','" + c.getPID_NUMBER() + "'," + c.getID_PASSWORD() + ")";
		SQLQuery += ";";
		System.out.println("\nADDClient.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's add the client */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyClientDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot add the given Client!", ex);
			}

		}

		closeConnection();

		return (res);
	}

}
