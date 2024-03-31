<%@ page import="com.javawide.book.blackbook1.ch07.Greeting" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="hellotags" prefix="helloTag" %><%
	Greeting greeting = new Greeting();
	greeting.setName(request.getParameter("name"));
	greeting.decorateName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Tag</title>
</head>
<body>
	<helloTag:hello user="<%= greeting.toString() + (1 + 3)  %>" />
</body>
</html>