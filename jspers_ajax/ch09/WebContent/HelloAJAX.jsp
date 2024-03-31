<%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%><%
	String name = request.getParameter("name");
	name = (null == name)? "Guest" : name;
%><?xml version="1.0" encoding="UTF-8"?>
<hello>
	<greetMessage>안녕하세요 <%= name %> 님</greetMessage>
	<greetMessage to="hello">안녕하세요</greetMessage>
</hello>