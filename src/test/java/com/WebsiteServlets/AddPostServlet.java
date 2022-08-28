package com.WebsiteServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

public class AddPostServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		ServletContext c=getServletContext();
		String url=c.getInitParameter("CONNECTION");
		String username=c.getInitParameter("UserName");
		String password=c.getInitParameter("DbPassword");
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try {
			Timestamp t=new Timestamp(System.currentTimeMillis());
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.prepareStatement(" INSERT INTO posts VALUES(?,?,?,?)");
			stmt.setString(1,req.getParameter("posttitle"));
			stmt.setString(2,req.getParameter("postcontent"));
			stmt.setTimestamp(3,t);
			stmt.setString(4,req.getParameter("posttag"));
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
		rep.getWriter().println("DATA IS SAVED SUCCESSFULLY");
	}
}
