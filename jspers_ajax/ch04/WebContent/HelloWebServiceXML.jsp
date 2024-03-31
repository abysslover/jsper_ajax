<%@ page import="com.javawide.book.blackbook1.ch04.webservice.WSDLGenerator" 
contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="xslt/HelloWebService.xsl" ?>
<%
	String level = request.getParameter("level");
	WSDLGenerator gen = new WSDLGenerator("com.javawide.book.blackbook1.ch04.webservice.GreetService");
	gen.setLevel(level);
%><%= gen %>