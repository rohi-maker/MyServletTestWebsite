package com.WebsiteServlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		if(username.equals("sanwalrohit333@gmail.com") && password.equals("nanaji"))
			rep.getWriter().println("CORRECT PASSWORD");
		else
			rep.getWriter().println("INNCORRECT PASSWORD");
	}
}
