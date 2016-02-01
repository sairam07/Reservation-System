package io.reservation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/restaurant_db";
	private static final String user= "root";
	private static final String password ="oracle";
	
	static {
//		System.out.println("Works!");
		try {
			System.out.println("started");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver initialised");
		} catch (ClassNotFoundException e) {
			System.err.println("Error at load of JDBC Driver");
			e.printStackTrace();
		}
	}
	
	
	
	public static Connection connect(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("Connection Error");
			e.printStackTrace();
		}
		
		return con;
	}

}
