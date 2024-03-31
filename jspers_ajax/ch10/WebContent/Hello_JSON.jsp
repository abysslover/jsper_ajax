<%@ page import="com.javawide.book.blackbook1.ch10.HelloJSON" language="java" 
contentType="application/x-json; charset=UTF-8" pageEncoding="UTF-8"%><%
	HelloJSON json = new HelloJSON();
	json.setXML("<hello id=\"hello1\">" +
			"<to>magneto</to>" +
			"<from>tomcat</from>" +
			"<message>Nice to meet you!</message>" +
			"</hello>");
%><%= json %>