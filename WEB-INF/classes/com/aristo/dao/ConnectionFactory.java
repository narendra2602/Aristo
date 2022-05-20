package com.aristo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionFactory {

	public static Connection getConnection(){
		Connection con = null;
		ResourceBundle rb=null;
		String host=null;
		String port=null;
		String dbname=null;
		String user=null;
		String password=null;
		try {
			rb = ResourceBundle.getBundle("Database");
			Class.forName("com.mysql.jdbc.Driver");
			host=rb.getString("host");
			port=rb.getString("port");
			dbname=rb.getString("dbname");
			user=rb.getString("user");
			password=rb.getString("password");
			
			con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname+"",""+user+"",""+password+"");			  
			//con = DriverManager.getConnection("jdbc:mysql://localhost/aristo","root","spk67890");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3406/aristo","root","654321");
			
			  
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return con;
	}
	
	
	public static Connection getNepalConnection(){
		Connection con = null;
		ResourceBundle rb=null;
		String host=null;
		String port=null;
		String dbname=null;
		String user=null;
		String password=null;
		try {
			rb = ResourceBundle.getBundle("Database");
			Class.forName("com.mysql.jdbc.Driver");
			host=rb.getString("host1");
			port=rb.getString("port1");
			dbname=rb.getString("dbname1");
			user=rb.getString("user1");
			password=rb.getString("password1");
			
			con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname+"",""+user+"",""+password+"");			  
			//con = DriverManager.getConnection("jdbc:mysql://localhost/aristo","root","spk67890");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3406/aristo","root","654321");
			
			  
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return con;
	}
	
	
}
 