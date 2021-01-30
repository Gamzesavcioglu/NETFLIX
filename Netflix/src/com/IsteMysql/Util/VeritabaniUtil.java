package com.IsteMysql.Util;
import java.sql.*;

public class VeritabaniUtil {

	static Connection conn = null;
	
	public static Connection Baglan() {
		
		try {			
			conn = DriverManager.getConnection("//your sql connection");
		    return conn;	
		}
		catch(Exception e){
			System.out.println(e.getMessage().toString());
			return null;
		}	
	}	
}
