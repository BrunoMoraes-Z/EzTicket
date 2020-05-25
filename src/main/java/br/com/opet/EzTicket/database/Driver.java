package br.com.opet.EzTicket.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

	// MySQL
//	private static String jdbc = "com.mysql.cj.jdbc.Driver";
//	private static String url = "jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=America/Sao_Paulo";
//	private static String user = "root";
//	private static String password = "";
	
	// Oracle
	private static String jdbc = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@//localhost:1521/XE";
	private static String user = "zeuss";
	private static String password = "zeuss";

	static {
		try {
			Class.forName(jdbc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DriverConnection getStatement(String query) {
		Connection con = getConnection();
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.setAutoCommit(false);
					return new DriverConnection(con, con.prepareStatement(query));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

}
