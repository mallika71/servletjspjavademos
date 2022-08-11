package com.samples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/adduserServlet") 
public class AddUserServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "harsha" ,"harsha"); 
					Statement statement=connection.createStatement();) {
			
			String query = "insert into user values('" +firstname +"','" + lastname +"','" + email +"','" + password +"')";
			System.out.println("Query being executed: " + query);
			int rowsInserted =statement.executeUpdate(query);
			System.out.println("Query being executed: " + rowsInserted);
			
			PrintWriter pw= response.getWriter();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}