package com.javawide.book.blackbook1.ch07.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javawide.book.blackbook1.ch07.HelloLogin;

public class HelloLoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 5062194787037680986L;
	private HelloLogin login = new HelloLogin();
	public HelloLoginServlet() {
		super();
	}   	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginMessage = login.login(request.getParameter("id"), request.getParameter("pw"));
		request.getSession().setAttribute("loginMessage", loginMessage);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(loginMessage);
	}   	  	    
}