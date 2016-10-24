package com.ignite.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Vector;

import com.ignite.server.entities.Account;
import com.ignite.server.entities.Transfer;

public class MyAccountDAO {
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

	public static Vector<Account> getAllAccounts() {
		Vector<Account> res = null;
		String SQLQuery = "SELECT * FROM ACCOUNT;";
		Statement query = null;
		ResultSet rs = null;
		Account tempS = null;

		if (openConnection()) {
			/* Ok let's get the transfers */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Account>();
					}
					tempS = new Account();
					tempS.setID(rs.getInt("ID"));
					tempS.setACCOUNT_NUMBER(rs.getString("ACCOUNT_NUMBER"));
					tempS.setAVAILABILITY(rs.getDouble("AVAILABILITY"));
					tempS.setID_CLIENT(rs.getInt("ID_CLIENT"));
					tempS.setCREATION(rs.getString("CREATION"));

					res.add(tempS);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Account!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static Vector<Account> getAccounts(Account c) {
		Vector<Account> res = null;
		String SQLQuery = "SELECT * FROM ACCOUNT WHERE";
		Statement query = null;
		ResultSet rs = null;
		Account temp = null;

		/* Query building Start */
		if (c.getID() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID=" + c.getID() + "";
			} else {
				SQLQuery += " ID=" + c.getID() + "";
			}
		}
		if (c.getACCOUNT_NUMBER() != null && c.getACCOUNT_NUMBER().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ACCOUNT_NUMBER='" + c.getACCOUNT_NUMBER() + "'";
			} else {
				SQLQuery += " ACCOUNT_NUMBER='" + c.getACCOUNT_NUMBER() + "'";
			}
		}
		if (c.getID_CLIENT() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND ID_CLIENT=" + c.getID_CLIENT() + "";
			} else {
				SQLQuery += " ID_CLIENT=" + c.getID_CLIENT() + "";
			}
		}
		if (c.getAVAILABILITY() != 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND AVAILABILITY=" + c.getAVAILABILITY() + "";
			} else {
				SQLQuery += " AVAILABILITY=" + c.getAVAILABILITY() + "";
			}
		}
		if (c.getCREATION() != null && c.getCREATION().length() > 0) {
			if (!SQLQuery.endsWith("WHERE")) {
				SQLQuery += " AND CREATION='" + c.getCREATION() + "'";
			} else {
				SQLQuery += " CREATION='" + c.getCREATION() + "'";
			}
		}
		if (SQLQuery.endsWith("WHERE")) {
			SQLQuery = "SELECT * FROM ACCOUNT";
		}

		SQLQuery += ";";
		System.out.println("\tgetAccounts.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's get the transfers */
			try {
				query = myCon.createStatement();
				query.executeQuery(SQLQuery);
				rs = query.getResultSet();
				while (rs.next()) {
					if (res == null) {
						res = new Vector<Account>();
					}
					temp = new Account();
					temp.setID(rs.getInt("ID"));
					temp.setID_CLIENT(rs.getInt("ID_CLIENT"));
					temp.setCREATION(rs.getString("CREATION"));
					temp.setACCOUNT_NUMBER(rs.getString("ACCOUNT_NUMBER"));
					temp.setAVAILABILITY(rs.getDouble("AVAILABILITY"));
					
					res.add(temp);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw new IllegalStateException("Cannot select the Accounts matching the given Transfer!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int modAccount(Account s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "UPDATE ACCOUNT SET AVAILABILITY = " + s.getAVAILABILITY() + ",ID_CLIENT = " + s.getID_CLIENT()
				+ ",ACCOUNT_NUMBER = '" + s.getACCOUNT_NUMBER() + "',CREATION = '" + s.getCREATION() + "' WHERE ID = " + s.getID();
		SQLQuery += ";";
		System.out.println("\nmodAccount.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's modify the transfer */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyAccountDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot modify the given Account!", ex);
			}

		}

		closeConnection();

		return (res);
	}

	public static int addAccount(Account s) {
		String SQLQuery = "";
		Statement query = null;
		int res = 0;

		/* Query building Start */
		SQLQuery = "INSERT INTO ACCOUNT (AVAILABILITY,ID_CLIENT,CREATION,ACCOUNT_NUMBER) VALUES (" + s.getAVAILABILITY() + ","
				+ s.getID_CLIENT() + ",'" + s.getCREATION() + "','" + s.getACCOUNT_NUMBER() + "')";
		SQLQuery += ";";
		System.out.println("\naddAccount.SQLQuery = **" + SQLQuery + "**\n\n");
		/* Query building End */

		if (openConnection()) {
			/* Ok let's add the account */
			try {
				query = myCon.createStatement();
				res = query.executeUpdate(SQLQuery);
				MyAccountDAO.myCon.commit();
			} catch (SQLException ex) {
				res = 0;
				ex.printStackTrace();
				throw new IllegalStateException("Cannot add the given Account!", ex);
			}

		}

		closeConnection();

		return (res);
	}
}
