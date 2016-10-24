package com.ignite.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.ignite.server.entities.Transfer;

public class MyTransferDAO {

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

	public static Vector<Transfer> getAllTransfers() {
		Vector<Transfer> res = null;
		String SQLQuery = "SELECT * FROM TRANSFER;";
		Statement query = null;
		ResultSet rs = null;
		Transfer tempS = null;

		if (openConnection()) {
			/* Ok let's get the transfers */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Transfer>();
					}
					tempS = new Transfer();
					tempS.setID(rs.getInt("ID"));
					tempS.setAMOUNT(rs.getDouble("AMOUNT"));
					tempS.setID_ACCOUNT_FROM(rs.getInt("ID_ACCOUNT_FROM"));
					tempS.setID_ACCOUNT_TO(rs.getInt("ID_ACCOUNT_TO"));
					tempS.setDAY(rs.getString("DAY"));

					res.add(tempS);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the MoneyTransfer!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static Vector<Transfer> getTransfers(Transfer c) {
		Vector<Transfer> res = null;
		String SQLQuery = "SELECT * FROM TRANSFER WHERE";
		Statement query = null;
		ResultSet rs = null;
		Transfer temp = null;

		/* Query building Start */
		if (c.getID() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID=" + c.getID() + "";
			} else {
				SQLQuery += " ID=" + c.getID() + "";
			}
		}
		if (c.getID_ACCOUNT_FROM() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID_ACCOUNT_FROM=" + c.getID_ACCOUNT_FROM() + "";
			} else {
				SQLQuery += " ID_ACCOUNT_FROM=" + c.getID_ACCOUNT_FROM() + "";
			}
		}
		if (c.getID_ACCOUNT_TO() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID_ACCOUNT_TO=" + c.getID_ACCOUNT_TO() + "";
			} else {
				SQLQuery += " ID_ACCOUNT_TO=" + c.getID_ACCOUNT_TO() + "";
			}
		}
		if (c.getDAY() != null && c.getDAY().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND DAY='" + c.getDAY() + "'";
			} else {
				SQLQuery += " DAY='" + c.getDAY() + "'";
			}
		}
		if (SQLQuery.endsWith("WHERE")) {
			SQLQuery = "SELECT * FROM TRANSFER";
		}

		SQLQuery += ";";
		System.out.println("\tgetTransfers.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's get the transfers */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				GregorianCalendar gc = new GregorianCalendar();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Transfer>();
					}
					temp = new Transfer();
					temp.setID(rs.getInt("ID"));
					temp.setID_ACCOUNT_FROM(rs.getInt("ID_ACCOUNT_FROM"));
					temp.setID_ACCOUNT_TO(rs.getInt("ID_ACCOUNT_TO"));
					temp.setAMOUNT(rs.getDouble("AMOUNT"));
					temp.setDAY(rs.getString("DAY"));
					res.add(temp);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Transfers matching the given Transfer!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int modTransfer(Transfer s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "UPDATE TRANSFER SET AMOUNT = " + s.getAMOUNT() + ",ID_ACCOUNT_FROM = " + s.getID_ACCOUNT_FROM()
				+ ",ID_ACCOUNT_TO = " + s.getID_ACCOUNT_TO() + ",DAY = " + s.getDAY() + " WHERE ID = " + s.getID();
		SQLQuery += ";";
		System.out.println("\nmodtransfer.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's modify the transfer */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyTransferDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot modify the given Transfer!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int addTransfer(Transfer s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "INSERT INTO TRANSFER (AMOUNT,ID_ACCOUNT_FROM,ID_ACCOUNT_TO,DAY) VALUES (" + s.getAMOUNT() + ","
				+ s.getID_ACCOUNT_FROM() + "," + s.getID_ACCOUNT_TO() + ",'" + s.getDAY() + "')";
		SQLQuery += ";";
		System.out.println("\naddTransfer.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's add the transfer */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyTransferDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot add the given Transfer!", ex);
			}

		}

		closeConnection();

		return (res);
	}

}
