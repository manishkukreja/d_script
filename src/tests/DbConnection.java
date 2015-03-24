package tests;

import java.sql.*;
import javax.sql.*;

public class DbConnection {
	
	 public static void main(String args[])
	 {
	 String user_name;
	 String dbUrl = "jdbc:mysql://10.20.20.15:3306/apponline";  //This URL is based on your IP address
	 String username="root"; //Default username is root
	 String password="diaspark"; //Default password is root
	 String dbClass = "com.mysql.jdbc.Driver";
	 String query = "Select * from apponline.user where user_id = 1;";

	 try 
	 {

	 Class.forName(dbClass);
	 Connection con = DriverManager.getConnection (dbUrl,username,password);
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);

	 while (rs.next()) 
	 {
		 user_name = rs.getString(2);
	 System.out.println(user_name);
	 } //end while

	 con.close();
	 } //end try

	 catch(ClassNotFoundException e) 
	 {
	 e.printStackTrace();
	 }

	 catch(SQLException e) 
	 {
	 e.printStackTrace();
	 }

	 }  //end main
}
