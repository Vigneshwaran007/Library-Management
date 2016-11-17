package com.wipro.book.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getDBConnection(){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","KSR_0021313007","KSR_0021313007");
		return con;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
}
