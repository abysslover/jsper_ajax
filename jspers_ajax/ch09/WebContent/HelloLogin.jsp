<%@ page import="com.javawide.book.blackbook1.ch09.HelloAJAXLogin, 
com.javawide.book.blackbook1.ch09.HelloSessionListener" language="java" 
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	String userID = request.getParameter("userID");
	String userPW = request.getParameter("userPW");
	HelloAJAXLogin login = new HelloAJAXLogin();
	String logonMessage = login.login(userID, userPW);
	if("로그인 성공".equals(logonMessage)) HelloSessionListener.getUserList().addUser(session.getId(), userID);
%><%= logonMessage %>