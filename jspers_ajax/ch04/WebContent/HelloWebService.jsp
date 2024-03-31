<%@ page import="com.javawide.book.blackbook1.ch04.webservice.WSDLGenerator" 
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Service Operations</title>
</head>
<body>
	<%
		String level = request.getParameter("level");
		WSDLGenerator gen = new WSDLGenerator("com.javawide.book.blackbook1.ch04.webservice.GreetService");
		gen.setLevel(level);
	%><%= gen %>
</body>
</html>