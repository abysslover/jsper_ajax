package com.javawide.book.blackbook1.ch07.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javawide.book.blackbook1.ch07.Greeting;

public class HelloServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1298315368612605894L;
	private Greeting greeting = new Greeting();
	public HelloServlet() {
		super();
	}   	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		greeting.setName(request.getParameter("name"));
		response.getWriter().print(greeting);
		response.getWriter().print(" + ");
		response.getWriter().print(getInitParameter("HelloParam"));
	}   	  	    
}