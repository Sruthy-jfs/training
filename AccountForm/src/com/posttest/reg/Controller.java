package com.posttest.reg;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet("/Controller")
public class Controller extends HttpServlet {
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String FirstName = request.getParameter("First Name");
	String LastName= request.getParameter("Last Name");
	String City= request.getParameter("City");
	String Gender= request.getParameter("Gender");
		try{Class.forName("oracle.jdbc.driver.OracleDriver");
		//System.out.println("Driver loaded successfully!");
		//Get the connection
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
		//System.out.println("Connection Established!");
		Statement statement = connection.createStatement();
		//Execute the query
		String query="insert into fb values('" + FirstName + "','" + LastName + "',' "+ City + "',' "+ Gender +"')";
		System.out.println(query);
		int noOfRowsInserted = statement.executeUpdate(query);
		if(noOfRowsInserted ==1) {
			System.out.println("NO OF ROWS INSERTED : " + noOfRowsInserted);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Success.html");
			requestDispatcher.forward(request, response);
		}
		
		else {
			System.out.println("No rows inserted!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.html");
			requestDispatcher.forward(request, response);
		}
 }catch (ClassNotFoundException | SQLException e) {
		System.out.println(e);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.html");
		requestDispatcher.forward(request, response);
		
	}	
}
 
}