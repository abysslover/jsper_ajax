<%@ page import="com.javawide.book.blackbook1.ch10.HelloJSON, 
com.javawide.book.blackbook1.ch11.files.HelloFileReader" language="java" 
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	HelloJSON json = new HelloJSON();
	json.setXML(HelloFileReader.read(application.getRealPath(".")+"/HelloCategory.xml"));
%><%= json %>