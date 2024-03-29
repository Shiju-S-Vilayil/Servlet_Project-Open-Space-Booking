package com.bos.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection 
{
	public static Connection con=null;
	public static Connection connection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");  
			 con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bos","root","root");  
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return con;
	}

}
