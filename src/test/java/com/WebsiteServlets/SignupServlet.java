package com.WebsiteServlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		rep.getWriter().println("INSIDE THE SIGNUP PAGE");
	}
}
