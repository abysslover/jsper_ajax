<%@ page import="com.javawide.book.blackbook1.ch11.HelloChatter" language="java"  
contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><%
	String id = com.javawide.book.blackbook1.ch09.HelloSessionListener.getUserList().getUserId(session.getId());
	String message = request.getParameter("message");
	String chatterName = request.getParameter("chattername");
	HelloChatter chatter = HelloChatter.getChatter(chatterName);
	chatter.add(String.format("%s : %s", id, message));
%><%= chatter %>