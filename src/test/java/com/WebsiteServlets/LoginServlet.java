package com.WebsiteServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException, ServletException {
		ServletContext connection=getServletContext();
		String url=connection.getInitParameter("CONNECTION");
		String username=connection.getInitParameter("UserName");
		String password=connection.getInitParameter("DbPassword");
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.prepareStatement("SELECT * FROM userinfo WHERE email=? AND password=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1,req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			rs=stmt.executeQuery();
			if(rs.next()) { // if there any record found
				HttpSession session=req.getSession();
				session.setAttribute("username",rs.getString("name"));
				session.setAttribute("mail", rs.getString("email"));
				session.setAttribute("mno", rs.getString("mobile"));
				if(rs.getBoolean("role")) { // IF USER IS AN ADMIN
					rep.sendRedirect("AdminPage.jsp");
				}
				else {  // IF USER IS NOT AN ADMIN
					rep.sendRedirect("HomePage.jsp");
				}
			}
			else { // if there is no such record is found in database
				rep.getWriter().println("<a href="+"\""+"Signupform.html"+"\""+">"+"YOU DONT'T HAVE ANY ACCOUNT CLICK  HERE TO SIGNUP "+"</a>");
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
