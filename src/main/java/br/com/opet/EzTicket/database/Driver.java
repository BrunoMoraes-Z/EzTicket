package br.com.opet.EzTicket.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

	private static String jdbc = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "";

//    public Driver(String jdbc, String url, String user, String password) {
//        this.jdbc = jdbc;
//        this.url = url;
//        this.user = user;
//        this.password = password;
//        try {
//            Class.forName(this.jdbc);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

	static {
//    	"com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/test?useTimezone=true&serverTimezone=UTC", "root", ""
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
