<%@ page language="java" import="com.javawide.book.blackbook1.ch07.Greeting" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
	Greeting greeting = new Greeting();
	greeting.setName(request.getParameter("name"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World</title>
</head>
<body>
	<%= greeting %>!
</body>
</html>