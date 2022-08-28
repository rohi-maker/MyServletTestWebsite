package com.WebsiteServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignupServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		ServletContext connection=getServletContext();
		String url=connection.getInitParameter("CONNECTION");
		String username=connection.getInitParameter("UserName");
		String password=connection.getInitParameter("DbPassword");
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn=DriverManager.getConnection(url, username, password);
			 stmt=conn.prepareStatement("INSERT INTO userinfo VALUES (?,?,?,?,?)");	
			 stmt.setString(1,req.getParameter("inputname"));
			 stmt.setString(2,req.getParameter("inputemail"));
			 stmt.setString(3,req.getParameter("inputpassword"));
			 stmt.setString(4,req.getParameter("inputphone"));
			 stmt.setBoolean(5, false);
			 stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		HttpSession session=req.getSession();
		session.setAttribute("inuser", req.getParameter("inputname"));
		session.setAttribute("inpassword", req.getParameter("inputpassword"));
		session.setAttribute("inemail", req.getParameter("inputemail"));
		session.setAttribute("inphone", req.getParameter("inputphone"));
		rep.sendRedirect("HomePage.jsp");
	}
}
