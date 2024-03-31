<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Login</title>
</head>
<body>
	<jsp:useBean id="login" class="com.javawide.book.blackbook1.ch07.HelloLogin" scope="application" />
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String loginMessage = login.login(id, pw);
		session.setAttribute("loginMessage", loginMessage);
	%>
	<%= loginMessage %>
</body>
</html>